package com.ricky.terrariamod.entity.monster.flying_type.dungeon_spirit;

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
 * Dungeon Spirit — ダンジョンの幽霊（困難モード地下城）
 * Terraria原作：困難モードの骷髅が死亡した際に放出される幽霊
 *   - 壁をすり抜けてプレイヤーを追跡する
 *   - CursedSkullよりも軽量・高速だがHPが低い
 *   - 半透明の青白い幽霊の見た目
 * HP: 200 / ダメージ: 50 / 防御: 0
 * MC換算: HP=20, 攻撃=6, 防御=0
 */
public class DungeonSpiritEntity extends FlyingMob {

    public DungeonSpiritEntity(EntityType<? extends FlyingMob> type, Level level) {
        super(type, level);
        this.moveControl = new FlyMoveControl(this);
        this.noPhysics = true; // 壁をすり抜ける
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)       // HP200 → MC換算約20
                .add(Attributes.MOVEMENT_SPEED, 0.22D)   // CursedSkull(0.15)より速い
                .add(Attributes.ATTACK_DAMAGE, 6.0D)     // ダメージ50 → MC換算約6
                .add(Attributes.ARMOR, 0.0D)             // 防御0
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.1D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PersistentChaseGoal(this));
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
        // 幽霊なので何もドロップしない
        super.die(damageSource);
    }

    /**
     * 執拗追跡 AI Goal
     * Terraria原作: Dungeon Spiritはプレイヤーから離れず、
     *   常に正面から突進し続ける。CursedSkullより素早い。
     */
    static class PersistentChaseGoal extends Goal {
        private final DungeonSpiritEntity spirit;

        public PersistentChaseGoal(DungeonSpiritEntity spirit) {
            this.spirit = spirit;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            return this.spirit.getTarget() != null && this.spirit.getTarget().isAlive();
        }

        @Override
        public boolean canContinueToUse() { return this.canUse(); }

        @Override
        public void tick() {
            LivingEntity target = this.spirit.getTarget();
            if (target == null) return;

            Vec3 targetPos = new Vec3(target.getX(), target.getY() + target.getEyeHeight() * 0.5, target.getZ());
            Vec3 spiritPos = this.spirit.position();
            double distance = this.spirit.distanceToSqr(target);
            Vec3 direction  = targetPos.subtract(spiritPos).normalize();

            // 幽霊は常に一定速度でターゲットに向かう（CursedSkullより速め）
            double speed;
            if (distance < 4.0D) {
                speed = 0.15D;
            } else if (distance < 16.0D) {
                speed = 0.35D;
            } else {
                speed = 0.55D; // 遠距離は高速で追いつく
            }

            this.spirit.setDeltaMovement(direction.scale(speed));
            this.spirit.lookAt(target, 30.0F, 30.0F);

            // 接触ダメージ
            if (distance < 2.0D && this.spirit.hasLineOfSight(target)) {
                this.spirit.doHurtTarget(target);
            }
        }

        @Override
        public boolean requiresUpdateEveryTick() { return true; }
    }
}
