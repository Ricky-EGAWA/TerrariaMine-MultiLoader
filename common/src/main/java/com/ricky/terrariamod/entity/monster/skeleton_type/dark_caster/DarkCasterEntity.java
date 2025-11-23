package com.ricky.terrariamod.entity.monster.skeleton_type.dark_caster;

import com.ricky.terrariamod.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class DarkCasterEntity extends Skeleton {
    private boolean hasInitializedEquipment = false;

    public DarkCasterEntity(EntityType<? extends Skeleton> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.01D) // テレポートメインだが0だと問題が起きるため低速に設定
                .add(Attributes.ATTACK_DAMAGE, 1.0D) // 魔法メインだが0だと問題が起きるため低ダメージに設定
                .add(Attributes.ARMOR, 2.0D)
                .add(Attributes.MAX_HEALTH, 20D) // ハート10個分
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.3D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TeleportAndShootGoal(this));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
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
        // 青色の皮防具（チェストプレートとレギンスのみ）
        int blueColor = 0x3F76E4; // 青色

        // チェストプレート
        ItemStack chestplate = new ItemStack(Items.LEATHER_CHESTPLATE);
        net.minecraft.nbt.CompoundTag chestTag = chestplate.getOrCreateTag();
        net.minecraft.nbt.CompoundTag chestDisplay = chestTag.getCompound("display");
        chestDisplay.putInt("color", blueColor);
        chestTag.put("display", chestDisplay);
        this.setItemSlot(EquipmentSlot.CHEST, chestplate);

        // レギンス
        ItemStack leggings = new ItemStack(Items.LEATHER_LEGGINGS);
        net.minecraft.nbt.CompoundTag legsTag = leggings.getOrCreateTag();
        net.minecraft.nbt.CompoundTag legsDisplay = legsTag.getCompound("display");
        legsDisplay.putInt("color", blueColor);
        legsTag.put("display", legsDisplay);
        this.setItemSlot(EquipmentSlot.LEGS, leggings);

        // ドロップ率を100%に
        this.setDropChance(EquipmentSlot.CHEST, 2.0F);
        this.setDropChance(EquipmentSlot.LEGS, 2.0F);
    }

    @Override
    public void die(DamageSource damageSource) {
        // 骨を1~3個ドロップ
        if (!this.level().isClientSide) {
            int boneCount = 1 + this.random.nextInt(3);
            this.spawnAtLocation(Items.BONE, boneCount);
        }
        super.die(damageSource);
    }

    // 魔法弾を発射
    public void shootMagicBall(LivingEntity target) {
        if (this.level().isClientSide) {
            return;
        }

        Vec3 casterPos = new Vec3(this.getX(), this.getEyeY() - 0.1, this.getZ());
        Vec3 targetPos = new Vec3(target.getX(), target.getEyeY(), target.getZ());
        Vec3 direction = targetPos.subtract(casterPos).normalize();

        // 壁貫通版のカスタム弾を作成
        DarkCasterBallEntity magicBall = new DarkCasterBallEntity(this.level(), this, 10.0F);

        magicBall.setPos(casterPos.x, casterPos.y, casterPos.z);
        magicBall.setDeltaMovement(direction.scale(0.8));

        this.level().addFreshEntity(magicBall);
        this.playSound(SoundEvents.EVOKER_CAST_SPELL, 1.0F, 1.0F);
    }

    // テレポート
    public boolean teleportTowards(LivingEntity target) {
        if (this.level().isClientSide) {
            return false;
        }

        // ターゲットの周囲にテレポート（5～10ブロック離れた位置）
        for (int i = 0; i < 10; i++) {
            double distance = 5.0 + this.random.nextDouble() * 5.0;
            double angle = this.random.nextDouble() * 2 * Math.PI;

            double x = target.getX() + Math.cos(angle) * distance;
            double y = target.getY();
            double z = target.getZ() + Math.sin(angle) * distance;

            BlockPos targetPos = new BlockPos((int) x, (int) y, (int) z);

            // 適切な地面を探す（上下5ブロック）
            for (int yOffset = -5; yOffset <= 5; yOffset++) {
                BlockPos checkPos = targetPos.offset(0, yOffset, 0);
                if (this.level().getBlockState(checkPos.below()).isSolid() &&
                    !this.level().getBlockState(checkPos).isSolid() &&
                    !this.level().getBlockState(checkPos.above()).isSolid()) {

                    // テレポート実行
                    this.teleportTo(checkPos.getX() + 0.5, checkPos.getY(), checkPos.getZ() + 0.5);
                    this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
                    return true;
                }
            }
        }
        return false;
    }

    // テレポートと射撃を繰り返すAIゴール
    static class TeleportAndShootGoal extends Goal {
        private final DarkCasterEntity caster;
        private LivingEntity target;
        private int actionTimer;
        private int shotsFired;
        private static final int SHOTS_PER_ATTACK = 3;
        private static final int TICKS_BETWEEN_SHOTS = 15; // 0.75秒
        private static final int COOLDOWN_AFTER_ATTACK = 40; // 2秒

        public TeleportAndShootGoal(DarkCasterEntity caster) {
            this.caster = caster;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity target = this.caster.getTarget();
            if (target == null || !target.isAlive()) {
                return false;
            }
            this.target = target;
            return true;
        }

        @Override
        public boolean canContinueToUse() {
            return this.target != null && this.target.isAlive();
        }

        @Override
        public void start() {
            this.actionTimer = 0;
            this.shotsFired = 0;
            // 開始時にテレポート
            this.caster.teleportTowards(this.target);
        }

        @Override
        public void tick() {
            if (this.target == null) {
                return;
            }

            this.caster.getLookControl().setLookAt(this.target, 30.0F, 30.0F);

            this.actionTimer++;

            // 魔法を3発発射
            if (this.shotsFired < SHOTS_PER_ATTACK) {
                if (this.actionTimer >= TICKS_BETWEEN_SHOTS) {
                    this.caster.shootMagicBall(this.target);
                    this.shotsFired++;
                    this.actionTimer = 0;
                }
            }
            // 3発撃ち終わったらクールダウン後にテレポート
            else if (this.actionTimer >= COOLDOWN_AFTER_ATTACK) {
                this.caster.teleportTowards(this.target);
                this.shotsFired = 0;
                this.actionTimer = 0;
            }
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }
    }
}
