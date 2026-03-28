package com.ricky.terrariamod.entity.monster.skeleton_type.tactical_skeleton;

import com.ricky.terrariamod.entity.projectile.ammo.musket_ball.MusketBallEntity;
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
 * Tactical Skeleton — 戦術骷髅（困難モード地下城）
 * Terraria原作：マシンガンで弾を連射する
 *   - 中距離を維持しながら素早く移動する
 *   - バースト射撃: 8発連射 → クールダウン
 *   - Sniperより近距離で戦う
 * HP: 600 / ダメージ: 80（1発あたり） / 防御: 22
 */
public class TacticalSkeletonEntity extends Skeleton {
    private boolean hasInitializedEquipment = false;

    public TacticalSkeletonEntity(EntityType<? extends Skeleton> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 48.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.28D)  // Sniperより素早く動く
                .add(Attributes.ATTACK_DAMAGE, 2.0D)
                .add(Attributes.ARMOR, 5.0D)             // 防御22 → MC換算約5
                .add(Attributes.MAX_HEALTH, 60.0D)       // HP600 → MC換算約60
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.4D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BurstFireGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));
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
    }

    private void setupEquipment() {
        // マシンガンを持つ（弩で代用）
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.CROSSBOW));
        this.setDropChance(EquipmentSlot.MAINHAND, 0.0F);

        // 迷彩系の暗い緑系装備
        int tacColor = 0x2D4A2D;
        for (EquipmentSlot slot : new EquipmentSlot[]{
                EquipmentSlot.HEAD, EquipmentSlot.CHEST,
                EquipmentSlot.LEGS, EquipmentSlot.FEET}) {
            ItemStack armor = new ItemStack(switch (slot) {
                case HEAD  -> Items.LEATHER_HELMET;
                case CHEST -> Items.LEATHER_CHESTPLATE;
                case LEGS  -> Items.LEATHER_LEGGINGS;
                case FEET  -> Items.LEATHER_BOOTS;
                default    -> Items.AIR;
            });
            CompoundTag tag = armor.getOrCreateTag();
            CompoundTag display = tag.getCompound("display");
            display.putInt("color", tacColor);
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
     * 1発ずつ弾を発射する（BurstFireGoalから繰り返し呼ばれる）
     * MusketBallEntityを使用、連射のためダメージは低め
     * Terraria: 1発あたり80ダメージ → MC換算 8.0F
     */
    public void fireBullet(LivingEntity target) {
        if (this.level().isClientSide) return;

        Vec3 shooterPos = new Vec3(this.getX(), this.getEyeY() - 0.1, this.getZ());
        Vec3 targetPos  = new Vec3(target.getX(), target.getEyeY(), target.getZ());
        Vec3 direction  = targetPos.subtract(shooterPos).normalize();

        // 連射のため少しランダムな広がりを加える（マシンガンらしさ）
        double spread = 0.06;
        Vec3 spreadDir = new Vec3(
                direction.x + (this.random.nextDouble() - 0.5) * spread,
                direction.y + (this.random.nextDouble() - 0.5) * spread,
                direction.z + (this.random.nextDouble() - 0.5) * spread
        ).normalize();

        MusketBallEntity bullet = new MusketBallEntity(this.level(), this, 8.0F);
        bullet.setPos(shooterPos.x, shooterPos.y, shooterPos.z);
        bullet.setDeltaMovement(spreadDir.scale(2.5)); // 中速弾
        bullet.setNoGravity(true);
        bullet.setOwner(this);

        this.level().addFreshEntity(bullet);
        // 連射音（短め）
        this.playSound(SoundEvents.CROSSBOW_SHOOT, 0.6F, 1.4F + this.random.nextFloat() * 0.2F);
    }

    /**
     * バースト射撃 AI Goal
     * Terraria原作の挙動を再現:
     *   1. 中距離(8〜24格)を維持しながら横移動
     *   2. 8発連射（各4tick間隔 = 0.2秒/発）
     *   3. 射撃後30tickクールダウン
     *   4. 再び接近 or 横移動して繰り返す
     */
    static class BurstFireGoal extends Goal {
        private final TacticalSkeletonEntity tactical;
        private LivingEntity target;
        private int shotsFired;
        private int burstTimer;      // 連射間隔カウンター
        private int cooldownTimer;   // バースト後クールダウン
        private boolean inBurst;     // 連射中フラグ

        private static final double RANGE_MIN     = 8.0;
        private static final double RANGE_MAX     = 24.0;
        private static final double MAX_RANGE     = 48.0;
        private static final int    SHOTS_PER_BURST = 8;   // 8発連射
        private static final int    TICKS_PER_SHOT  = 4;   // 0.2秒/発
        private static final int    BURST_COOLDOWN  = 30;  // 1.5秒クールダウン

        public BurstFireGoal(TacticalSkeletonEntity tactical) {
            this.tactical = tactical;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity t = this.tactical.getTarget();
            if (t == null || !t.isAlive()) return false;
            if (cooldownTimer > 0) {
                cooldownTimer--;
                return false;
            }
            double dist = this.tactical.distanceToSqr(t);
            if (dist > MAX_RANGE * MAX_RANGE) return false;
            this.target   = t;
            return true;
        }

        @Override
        public boolean canContinueToUse() {
            return this.target != null && this.target.isAlive()
                    && this.tactical.distanceToSqr(this.target) < MAX_RANGE * MAX_RANGE;
        }

        @Override
        public void start() {
            this.shotsFired = 0;
            this.burstTimer = 0;
            this.inBurst    = false;
        }

        @Override
        public void stop() {
            this.target  = null;
            this.inBurst = false;
        }

        @Override
        public void tick() {
            if (this.target == null) return;

            double dist = Math.sqrt(this.tactical.distanceToSqr(this.target));
            this.tactical.getLookControl().setLookAt(this.target, 30.0F, 30.0F);

            // 距離管理
            if (dist < RANGE_MIN) {
                // 近すぎ → 後退
                Vec3 away = this.tactical.position().subtract(this.target.position()).normalize();
                this.tactical.getNavigation().moveTo(
                        this.tactical.getX() + away.x * 5,
                        this.tactical.getY(),
                        this.tactical.getZ() + away.z * 5,
                        1.3D);
            } else if (dist > RANGE_MAX) {
                // 遠すぎ → 接近
                this.tactical.getNavigation().moveTo(this.target, 1.2D);
            } else if (!inBurst) {
                // 射程内かつ連射前 → 少し横移動（戦術的な動き）
                if (this.tactical.tickCount % 20 == 0) {
                    double sideAngle = this.tactical.tickCount * 0.3;
                    this.tactical.getNavigation().moveTo(
                            this.tactical.getX() + Math.cos(sideAngle) * 3,
                            this.tactical.getY(),
                            this.tactical.getZ() + Math.sin(sideAngle) * 3,
                            1.1D);
                }
            }

            // 射程内なら連射開始
            if (dist >= RANGE_MIN && dist <= RANGE_MAX) {
                inBurst = true;
            }

            // 連射処理
            if (inBurst) {
                burstTimer++;
                if (shotsFired < SHOTS_PER_BURST) {
                    if (burstTimer >= TICKS_PER_SHOT) {
                        this.tactical.fireBullet(this.target);
                        shotsFired++;
                        burstTimer = 0;
                    }
                } else {
                    // 連射完了 → クールダウン
                    cooldownTimer = BURST_COOLDOWN;
                    shotsFired    = 0;
                    inBurst       = false;
                    burstTimer    = 0;
                    this.stop();
                }
            }
        }

        @Override
        public boolean requiresUpdateEveryTick() { return true; }
    }
}
