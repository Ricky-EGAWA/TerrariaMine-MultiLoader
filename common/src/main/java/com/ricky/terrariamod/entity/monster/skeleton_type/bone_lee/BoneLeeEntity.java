package com.ricky.terrariamod.entity.monster.skeleton_type.bone_lee;

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
 * Bone Lee — 骨リー（困難モード地下城）
 * Terraria原作：ヌンチャクを持つ高速な骷髅武道家
 *   - 極めて高い移動速度で近接攻撃
 *   - 「ダッシュ突進」で一気に距離を詰める
 *   - Terraria最速クラスの移動速度を持つ雑魚敵
 * HP: 1000 / ダメージ: 85 / 防御: 22
 * MC換算: HP=100, 近接攻撃=11, 防御=5
 */
public class BoneLeeEntity extends Skeleton {
    /** ダッシュクールダウン（Tick）*/
    private int dashCooldown = 0;
    private static final int DASH_COOLDOWN_MAX = 80; // 4秒に1回ダッシュ
    /** ダッシュ中フラグ */
    private boolean isDashing = false;
    private int dashTicks = 0;
    private static final int DASH_DURATION = 8; // 0.4秒ダッシュ
    private boolean hasInitializedEquipment = false;

    public BoneLeeEntity(EntityType<? extends Skeleton> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                // Terraria最速クラス: 速度極めて高い
                .add(Attributes.MOVEMENT_SPEED, 0.45D)
                // ダメージ85 → MC換算約11
                .add(Attributes.ATTACK_DAMAGE, 11.0D)
                // 防御22 → MC換算約5
                .add(Attributes.ARMOR, 5.0D)
                // HP1000 → MC換算約100
                .add(Attributes.MAX_HEALTH, 100.0D)
                // 高い攻撃ノックバック（蹴り感）
                .add(Attributes.ATTACK_KNOCKBACK, 2.5D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new DashAttackGoal(this));   // ダッシュ突進
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.5D, false)); // 通常近接
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide && !hasInitializedEquipment) {
            // 素手近接（弓を外す）
            this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            this.setDropChance(EquipmentSlot.MAINHAND, 0.0F);
            hasInitializedEquipment = true;
        }
        if (dashCooldown > 0) dashCooldown--;

        // ダッシュ中の処理
        if (isDashing) {
            dashTicks++;
            if (dashTicks >= DASH_DURATION) {
                isDashing = false;
                dashTicks = 0;
            }
        }
    }

    @Override
    protected boolean isSunBurnTick() { return false; }

    @Override
    public void die(DamageSource damageSource) {
        if (!this.level().isClientSide) {
            this.spawnAtLocation(Items.BONE, 2 + this.random.nextInt(3));
            // Nunchakuのドロップ（5%確率）: MCには対応品がないためSTRINGで代替
            if (this.random.nextFloat() < 0.05F) {
                this.spawnAtLocation(Items.STRING, 2);
            }
        }
        super.die(damageSource);
    }

    /**
     * ダッシュ突進を実行する
     * ターゲットに向かって一気に加速、接触時に高ダメージ
     */
    public void performDash(LivingEntity target) {
        if (dashCooldown > 0 || this.level().isClientSide) return;

        Vec3 direction = target.position().subtract(this.position()).normalize();
        // ダッシュ速度: 通常の約4倍
        this.setDeltaMovement(direction.x * 2.2, this.getDeltaMovement().y, direction.z * 2.2);

        isDashing    = true;
        dashTicks    = 0;
        dashCooldown = DASH_COOLDOWN_MAX;

        this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP, 1.0F, 1.5F);
    }

    /**
     * ダッシュ突進 AI Goal
     * Terraria原作の特性を再現:
     *   - 一定距離が開いたときにダッシュで一気に詰める
     *   - ダッシュ中は攻撃判定あり
     */
    static class DashAttackGoal extends Goal {
        private final BoneLeeEntity boneLee;
        private LivingEntity target;

        private static final double DASH_TRIGGER_DIST_MIN = 4.0;  // これ以上離れるとダッシュ
        private static final double DASH_TRIGGER_DIST_MAX = 16.0; // この距離以内のみ

        public DashAttackGoal(BoneLeeEntity boneLee) {
            this.boneLee = boneLee;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            LivingEntity t = this.boneLee.getTarget();
            if (t == null || !t.isAlive()) return false;
            if (this.boneLee.dashCooldown > 0) return false;
            double dist = this.boneLee.distanceToSqr(t);
            double min  = DASH_TRIGGER_DIST_MIN * DASH_TRIGGER_DIST_MIN;
            double max  = DASH_TRIGGER_DIST_MAX * DASH_TRIGGER_DIST_MAX;
            if (dist < min || dist > max) return false;
            this.target = t;
            return true;
        }

        @Override
        public boolean canContinueToUse() {
            return this.boneLee.isDashing && this.target != null && this.target.isAlive();
        }

        @Override
        public void start() {
            this.boneLee.performDash(this.target);
        }

        @Override
        public void tick() {
            if (this.target == null) return;
            // ダッシュ中に接触したらダメージ
            double dist = this.boneLee.distanceToSqr(this.target);
            if (dist < 3.0D) {
                this.boneLee.doHurtTarget(this.target);
                this.boneLee.isDashing = false; // ヒットでダッシュ終了
            }
        }

        @Override
        public boolean requiresUpdateEveryTick() { return true; }
    }
}
