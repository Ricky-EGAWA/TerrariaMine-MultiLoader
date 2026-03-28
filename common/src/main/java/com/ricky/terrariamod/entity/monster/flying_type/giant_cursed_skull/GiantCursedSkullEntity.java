package com.ricky.terrariamod.entity.monster.flying_type.giant_cursed_skull;

import com.ricky.terrariamod.entity.monster.flying_type.FlyMoveControl;
import com.ricky.terrariamod.entity.monster.flying_type.FlyRandomlyGoal;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

/**
 * Giant Cursed Skull — 巨大呪われた骸骨（困難モード地下城）
 * Terraria原作：Cursed Skullの巨大版
 *   - 壁をすり抜けて突進する
 *   - 通常版より大きく遅いが、ダメージと防御が高い
 * HP: 4000 / ダメージ: 90 / 防御: 30
 * MC換算: HP=80, 攻撃=11, 防御=8
 */
public class GiantCursedSkullEntity extends FlyingMob {

    public GiantCursedSkullEntity(EntityType<? extends FlyingMob> type, Level level) {
        super(type, level);
        this.moveControl = new FlyMoveControl(this);
        this.noPhysics = true; // 壁をすり抜ける
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 80.0D)      // HP4000 → MC換算約80
                .add(Attributes.MOVEMENT_SPEED, 0.10D)  // 通常版(0.15)より遅い
                .add(Attributes.ATTACK_DAMAGE, 11.0D)   // ダメージ90 → MC換算約11
                .add(Attributes.ARMOR, 8.0D)            // 防御30 → MC換算約8
                .add(Attributes.FOLLOW_RANGE, 30.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.8D); // 大きいのでノックバック耐性高め
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new ChargeAttackGoal(this));
        this.goalSelector.addGoal(5, new FlyRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        this.noPhysics = true;
    }

    @Override
    public boolean isNoGravity() { return true; }

    @Override
    public void checkDespawn() {
        // デスポーンしない
    }

    @Override
    public void die(DamageSource damageSource) {
        if (!this.level().isClientSide) {
            // 巨大版はより多くのボーンをドロップ
            this.spawnAtLocation(Items.BONE, 3 + this.random.nextInt(4));
        }
        super.die(damageSource);
    }

    /**
     * 突進攻撃 AI（CursedSkullと同様だが、体が大きいので接触範囲が広い）
     */
    static class ChargeAttackGoal extends Goal {
        private final GiantCursedSkullEntity skull;

        public ChargeAttackGoal(GiantCursedSkullEntity skull) {
            this.skull = skull;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            return this.skull.getTarget() != null && this.skull.getTarget().isAlive();
        }

        @Override
        public boolean canContinueToUse() { return this.canUse(); }

        @Override
        public void tick() {
            LivingEntity target = this.skull.getTarget();
            if (target == null) return;

            Vec3 targetPos = new Vec3(target.getX(), target.getY() + target.getEyeHeight() * 0.5, target.getZ());
            Vec3 skullPos  = this.skull.position();
            double distance = this.skull.distanceToSqr(target);
            Vec3 direction  = targetPos.subtract(skullPos).normalize();

            // 巨大版は通常版より少し遅め
            double speed;
            if (distance < 16.0D) {
                speed = 0.15D; // 近距離突進
            } else if (distance < 36.0D) {
                speed = 0.25D; // 中距離
            } else {
                speed = 0.45D; // 遠距離接近
            }

            this.skull.setDeltaMovement(direction.scale(speed));
            this.skull.lookAt(target, 30.0F, 30.0F);

            // 接触判定: 体が大きいので当たり範囲を広めに（半径 1.5 ブロック）
            if (distance < 5.0D && this.skull.hasLineOfSight(target)) {
                this.skull.doHurtTarget(target);
            }
        }

        @Override
        public boolean requiresUpdateEveryTick() { return true; }
    }
}
