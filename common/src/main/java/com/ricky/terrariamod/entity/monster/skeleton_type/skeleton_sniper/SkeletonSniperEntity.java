package com.ricky.terrariamod.entity.monster.skeleton_type.skeleton_sniper;

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
 * Skeleton Sniper — 骷髅スナイパー（困難モード地下城）
 * Terraria原作：遠距離から超高ダメージの狙撃を行う
 *   - 射程が極めて長い（原作: 画面外から撃てる）
 *   - ダメージが非常に高い（170）
 *   - プレイヤーから距離を保つように後退する
 *   - 発射前に少し「チャージ」する（間を置く）
 * HP: 700 / ダメージ: 170 / 防御: 22
 */
public class SkeletonSniperEntity extends Skeleton {
    private boolean hasInitializedEquipment = false;

    public SkeletonSniperEntity(EntityType<? extends Skeleton> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 64.0D)   // 極めて長い追跡距離
                .add(Attributes.MOVEMENT_SPEED, 0.20D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D)   // 近接は弱い（狙撃メイン）
                .add(Attributes.ARMOR, 5.0D)            // 防御22 → MC換算約5
                .add(Attributes.MAX_HEALTH, 70.0D)      // HP700 → MC換算約70
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.4D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SniperShootGoal(this));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
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
        // 狙撃手スタイル: クロスボウを持つ（スナイパーライフルの代用）
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.CROSSBOW));
        this.setDropChance(EquipmentSlot.MAINHAND, 0.0F);

        // 暗いカーキ色の装備
        int sniperColor = 0x4A4A2A;
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
            display.putInt("color", sniperColor);
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
     * 狙撃弾を発射する
     * MusketBallEntity を高ダメージで使用（17.0F ≒ Terraria 170 ダメージを MC換算）
     * 弾速を高くして一直線に飛ばす（狙撃らしさ）
     */
    public void fireSniperShot(LivingEntity target) {
        if (this.level().isClientSide) return;

        Vec3 shooterPos = new Vec3(this.getX(), this.getEyeY() - 0.1, this.getZ());
        Vec3 targetPos  = new Vec3(target.getX(), target.getEyeY(), target.getZ());
        Vec3 direction  = targetPos.subtract(shooterPos).normalize();

        // 高ダメージの狙撃弾（MusketBallEntityを流用）
        MusketBallEntity bullet = new MusketBallEntity(this.level(), this, 17.0F);
        bullet.setPos(shooterPos.x, shooterPos.y, shooterPos.z);
        // 狙撃弾は高速で直線に飛ぶ、重力を無効化
        bullet.setDeltaMovement(direction.scale(3.5));
        bullet.setNoGravity(true);
        // 命中精度を上げるため精確に狙う
        bullet.setOwner(this);

        this.level().addFreshEntity(bullet);
        this.playSound(SoundEvents.CROSSBOW_SHOOT, 1.0F, 0.5F); // 低い音でスナイパーライフル感
        this.playSound(SoundEvents.ARROW_SHOOT, 0.5F, 0.4F);
    }

    /**
     * スナイパーAI Goal
     * Terraria原作の挙動を再現:
     *   1. 長距離を保ちながらプレイヤーを追跡
     *   2. 近づきすぎたら後退する
     *   3. 「チャージ」時間（40tick = 2秒）後に発射
     *   4. 発射後クールダウン（100tick = 5秒）
     */
    static class SniperShootGoal extends Goal {
        private final SkeletonSniperEntity sniper;
        private LivingEntity target;
        private int chargeTimer;     // 発射前のチャージ時間
        private int cooldownTimer;   // 発射後のクールダウン

        // 理想射程: 16〜48ブロック
        private static final double IDEAL_RANGE_MIN = 16.0;
        private static final double IDEAL_RANGE_MAX = 48.0;
        // 最大射程
        private static final double MAX_RANGE        = 64.0;
        private static final int    CHARGE_TIME       = 40; // 2秒チャージ
        private static final int    COOLDOWN_TIME     = 100; // 5秒クールダウン

        public SniperShootGoal(SkeletonSniperEntity sniper) {
            this.sniper = sniper;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity t = this.sniper.getTarget();
            if (t == null || !t.isAlive()) return false;
            if (cooldownTimer > 0) {
                cooldownTimer--;
                return false;
            }
            this.target = t;
            return true;
        }

        @Override
        public boolean canContinueToUse() {
            return this.target != null && this.target.isAlive()
                    && this.sniper.distanceToSqr(this.target) < MAX_RANGE * MAX_RANGE;
        }

        @Override
        public void start() {
            this.chargeTimer = 0;
        }

        @Override
        public void stop() {
            this.target = null;
            this.chargeTimer = 0;
        }

        @Override
        public void tick() {
            if (this.target == null) return;

            double distSq = this.sniper.distanceToSqr(this.target);
            double dist   = Math.sqrt(distSq);

            // 常にターゲットを見る
            this.sniper.getLookControl().setLookAt(this.target, 30.0F, 30.0F);

            // 距離管理: 理想射程を維持
            if (dist < IDEAL_RANGE_MIN) {
                // 近すぎる → 後退（Terraria原作の特性）
                Vec3 awayDir = this.sniper.position().subtract(this.target.position()).normalize();
                this.sniper.getNavigation().moveTo(
                        this.sniper.getX() + awayDir.x * 8,
                        this.sniper.getY(),
                        this.sniper.getZ() + awayDir.z * 8,
                        1.2D);
                this.chargeTimer = 0; // 後退中はチャージリセット
            } else if (dist > IDEAL_RANGE_MAX) {
                // 遠すぎる → 近づく
                this.sniper.getNavigation().moveTo(this.target, 1.0D);
                this.chargeTimer = 0;
            } else {
                // 理想射程内 → 停止してチャージ
                this.sniper.getNavigation().stop();
                this.chargeTimer++;

                // チャージ完了 → 発射
                if (this.chargeTimer >= CHARGE_TIME) {
                    this.sniper.fireSniperShot(this.target);
                    this.chargeTimer = 0;
                    this.cooldownTimer = COOLDOWN_TIME;
                    this.stop();
                }
            }
        }

        @Override
        public boolean requiresUpdateEveryTick() { return true; }
    }
}
