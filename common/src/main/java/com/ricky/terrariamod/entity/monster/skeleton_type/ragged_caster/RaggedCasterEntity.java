package com.ricky.terrariamod.entity.monster.skeleton_type.ragged_caster;

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
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

/**
 * Ragged Caster — ボロ魔法師（困難モード地下城）
 * Terraria原作：破れたローブを着た骷髅魔法師
 *   テレポートしながらShadow Boltを3連射する
 * HP: 400 / ダメージ: 75 / 防御: 20
 */
public class RaggedCasterEntity extends Skeleton {
    private boolean hasInitializedEquipment = false;

    public RaggedCasterEntity(EntityType<? extends Skeleton> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 38.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.01D)
                .add(Attributes.ATTACK_DAMAGE, 1.0D)
                .add(Attributes.ARMOR, 4.0D)           // 防御20 → MC換算約4
                .add(Attributes.MAX_HEALTH, 40.0D)     // HP400 → MC換算約40
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
        // 破れたローブ — 褪せた茶褐色
        int raggedColor = 0x5C3D1E; // 暗い茶色

        ItemStack chestplate = new ItemStack(Items.LEATHER_CHESTPLATE);
        CompoundTag chestTag = chestplate.getOrCreateTag();
        CompoundTag chestDisplay = chestTag.getCompound("display");
        chestDisplay.putInt("color", raggedColor);
        chestTag.put("display", chestDisplay);
        this.setItemSlot(EquipmentSlot.CHEST, chestplate);
        this.setDropChance(EquipmentSlot.CHEST, 0.0F);

        ItemStack leggings = new ItemStack(Items.LEATHER_LEGGINGS);
        CompoundTag legsTag = leggings.getOrCreateTag();
        CompoundTag legsDisplay = legsTag.getCompound("display");
        legsDisplay.putInt("color", raggedColor);
        legsTag.put("display", legsDisplay);
        this.setItemSlot(EquipmentSlot.LEGS, leggings);
        this.setDropChance(EquipmentSlot.LEGS, 0.0F);

        this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
    }

    @Override
    protected boolean isSunBurnTick() {
        return false;
    }

    @Override
    public void die(DamageSource damageSource) {
        if (!this.level().isClientSide) {
            this.spawnAtLocation(Items.BONE, 1 + this.random.nextInt(2));
        }
        super.die(damageSource);
    }

    /**
     * Shadow Bolt を発射する
     * Terraria原作: Necromancerより少し遅めの弾速
     */
    public void shootShadowBolt(LivingEntity target) {
        if (this.level().isClientSide) return;

        Vec3 casterPos = new Vec3(this.getX(), this.getEyeY() - 0.1, this.getZ());
        Vec3 targetPos  = new Vec3(target.getX(), target.getEyeY(), target.getZ());
        Vec3 direction  = targetPos.subtract(casterPos).normalize();

        com.ricky.terrariamod.entity.monster.skeleton_type.dark_caster.DarkCasterBallEntity bolt =
                new com.ricky.terrariamod.entity.monster.skeleton_type.dark_caster.DarkCasterBallEntity(
                        this.level(), this, 9.0F); // ダメージ: Necromancerより低め

        bolt.setPos(casterPos.x, casterPos.y, casterPos.z);
        bolt.setDeltaMovement(direction.scale(1.0)); // 弾速: Necromancerより遅め

        this.level().addFreshEntity(bolt);
        this.playSound(SoundEvents.EVOKER_CAST_SPELL, 0.9F, 1.3F);
    }

    public boolean teleportTowards(LivingEntity target) {
        if (this.level().isClientSide) return false;

        for (int i = 0; i < 10; i++) {
            double distance = 5.0 + this.random.nextDouble() * 5.0;
            double angle    = this.random.nextDouble() * 2 * Math.PI;
            double x = target.getX() + Math.cos(angle) * distance;
            double y = target.getY();
            double z = target.getZ() + Math.sin(angle) * distance;
            BlockPos targetPos = new BlockPos((int) x, (int) y, (int) z);

            for (int yOffset = -5; yOffset <= 5; yOffset++) {
                BlockPos checkPos = targetPos.offset(0, yOffset, 0);
                if (this.level().getBlockState(checkPos.below()).isSolid()
                        && !this.level().getBlockState(checkPos).isSolid()
                        && !this.level().getBlockState(checkPos.above()).isSolid()) {
                    this.teleportTo(checkPos.getX() + 0.5, checkPos.getY(), checkPos.getZ() + 0.5);
                    this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
                    return true;
                }
            }
        }
        return false;
    }

    /** テレポート → 3連射 → クールダウン → 繰り返し */
    static class TeleportAndShootGoal extends Goal {
        private final RaggedCasterEntity caster;
        private LivingEntity target;
        private int actionTimer;
        private int shotsFired;

        private static final int SHOTS_PER_BURST     = 3;
        private static final int TICKS_BETWEEN_SHOTS = 14;  // 0.7秒（Necromancerより少し遅め）
        private static final int COOLDOWN_AFTER_BURST = 45; // 2.25秒

        public TeleportAndShootGoal(RaggedCasterEntity caster) {
            this.caster = caster;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity t = this.caster.getTarget();
            if (t == null || !t.isAlive()) return false;
            this.target = t;
            return true;
        }

        @Override
        public boolean canContinueToUse() {
            return this.target != null && this.target.isAlive();
        }

        @Override
        public void start() {
            this.actionTimer = 0;
            this.shotsFired  = 0;
            this.caster.teleportTowards(this.target);
        }

        @Override
        public void tick() {
            if (this.target == null) return;
            this.caster.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
            this.actionTimer++;

            if (this.shotsFired < SHOTS_PER_BURST) {
                if (this.actionTimer >= TICKS_BETWEEN_SHOTS) {
                    this.caster.shootShadowBolt(this.target);
                    this.shotsFired++;
                    this.actionTimer = 0;
                }
            } else if (this.actionTimer >= COOLDOWN_AFTER_BURST) {
                this.caster.teleportTowards(this.target);
                this.shotsFired  = 0;
                this.actionTimer = 0;
            }
        }

        @Override
        public boolean requiresUpdateEveryTick() { return true; }
    }
}
