package com.ricky.terrariamod.entity.monster.skeleton_type.skeleton_commando;

import com.ricky.terrariamod.entity.projectile.ammo.rocket.RocketEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

/**
 * Skeleton Commando — 骷髅突击队員（困難モード地下城）
 * Terraria原作：ロケットランチャーを持ち、爆発する火箭弾を発射する
 * HP: 600 / ダメージ: 80（ロケット） / 防御: 22
 * 射程距離に入ると火箭弾を撃ち、近接では肉弾攻撃も行う
 */
public class SkeletonCommandoEntity extends Skeleton {
    private boolean hasInitializedEquipment = false;

    /** 発射クールダウン（Tick）*/
    private int rocketCooldown = 0;
    private static final int ROCKET_COOLDOWN_MAX = 60; // 3秒に1発

    public SkeletonCommandoEntity(EntityType<? extends Skeleton> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                // Terraria: 速度普通
                .add(Attributes.MOVEMENT_SPEED, 0.22D)
                // 近接攻撃ダメージ（火箭はonTickで処理）
                .add(Attributes.ATTACK_DAMAGE, 6.0D)
                // 防御22 → MC換算約5
                .add(Attributes.ARMOR, 5.0D)
                // HP600 → MC換算約60
                .add(Attributes.MAX_HEALTH, 60.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.4D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new FireRocketGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide && !hasInitializedEquipment) {
            setupEquipment();
            hasInitializedEquipment = true;
        }
        if (rocketCooldown > 0) {
            rocketCooldown--;
        }
    }

    private void setupEquipment() {
        // ロケットランチャーを持つ（外見上）
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.CROSSBOW));
        this.setDropChance(EquipmentSlot.MAINHAND, 0.0F);

        // 暗緑色のメタルアーマー風（染め革）
        int armorColor = 0x3A5A3A;
        for (EquipmentSlot slot : new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET}) {
            ItemStack armor = new ItemStack(switch (slot) {
                case HEAD  -> Items.LEATHER_HELMET;
                case CHEST -> Items.LEATHER_CHESTPLATE;
                case LEGS  -> Items.LEATHER_LEGGINGS;
                case FEET  -> Items.LEATHER_BOOTS;
                default    -> Items.AIR;
            });
            CompoundTag tag = armor.getOrCreateTag();
            CompoundTag display = tag.getCompound("display");
            display.putInt("color", armorColor);
            tag.put("display", display);
            this.setItemSlot(slot, armor);
            this.setDropChance(slot, 0.0F);
        }
    }

    @Override
    protected boolean isSunBurnTick() { return false; }

    @Override
    public void die(DamageSource damageSource) {
        if (!this.level().isClientSide) {
            this.spawnAtLocation(Items.BONE, 2 + this.random.nextInt(3));
        }
        super.die(damageSource);
    }

    /**
     * ロケット弾を発射する
     * 既存の RocketEntity を流用（爆発力 3）
     */
    public void fireRocket(LivingEntity target) {
        if (this.level().isClientSide || rocketCooldown > 0) return;

        Vec3 shooterPos = new Vec3(this.getX(), this.getEyeY() - 0.1, this.getZ());
        Vec3 targetPos  = new Vec3(target.getX(), target.getEyeY(), target.getZ());
        Vec3 direction  = targetPos.subtract(shooterPos).normalize();

        RocketEntity rocket = new RocketEntity(this.level(), this, 3);
        rocket.setPos(shooterPos.x, shooterPos.y, shooterPos.z);
        // ロケットは直進、重力の影響を少し受ける
        rocket.setDeltaMovement(direction.scale(1.5));
        rocket.setNoGravity(false);

        this.level().addFreshEntity(rocket);
        this.playSound(SoundEvents.CROSSBOW_SHOOT, 1.0F, 0.8F);

        rocketCooldown = ROCKET_COOLDOWN_MAX;
    }

    /**
     * 射程内でロケットを発射するGoal
     * 中〜遠距離はロケット、近距離は近接攻撃にフォールバック
     */
    static class FireRocketGoal extends Goal {
        private final SkeletonCommandoEntity commando;
        private LivingEntity target;
        private int ticksExecuting;

        private static final double ROCKET_RANGE_MIN = 4.0;  // これ以上近いと発射しない（爆発巻き込み防止）
        private static final double ROCKET_RANGE_MAX = 32.0; // 最大射程

        public FireRocketGoal(SkeletonCommandoEntity commando) {
            this.commando = commando;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity t = this.commando.getTarget();
            if (t == null || !t.isAlive()) return false;
            double dist = this.commando.distanceToSqr(t);
            // 射程内かつ近すぎない場合のみ発動
            if (dist < ROCKET_RANGE_MIN * ROCKET_RANGE_MIN || dist > ROCKET_RANGE_MAX * ROCKET_RANGE_MAX) return false;
            this.target = t;
            return true;
        }

        @Override
        public boolean canContinueToUse() {
            if (this.target == null || !this.target.isAlive()) return false;
            double dist = this.commando.distanceToSqr(this.target);
            return dist < ROCKET_RANGE_MAX * ROCKET_RANGE_MAX;
        }

        @Override
        public void start() {
            this.ticksExecuting = 0;
        }

        @Override
        public void stop() {
            this.target = null;
        }

        @Override
        public void tick() {
            if (this.target == null) return;
            this.ticksExecuting++;

            double dist = this.commando.distanceToSqr(this.target);

            // 適切な距離を保ちながら近づく
            if (dist > 16 * 16) {
                this.commando.getNavigation().moveTo(this.target, 1.0D);
            } else {
                this.commando.getNavigation().stop();
            }

            this.commando.getLookControl().setLookAt(this.target, 30.0F, 30.0F);

            // 20tick毎にロケット発射を試みる（クールダウンは fireRocket 内で管理）
            if (this.ticksExecuting % 20 == 0) {
                double minDist = ROCKET_RANGE_MIN * ROCKET_RANGE_MIN;
                if (dist > minDist) {
                    this.commando.fireRocket(this.target);
                }
            }
        }

        @Override
        public boolean requiresUpdateEveryTick() { return true; }
    }
}
