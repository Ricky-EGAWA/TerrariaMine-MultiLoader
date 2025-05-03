package com.ricky.terrariamod.item.weapon.gun;

import com.ricky.terrariamod.entity.projectile.ammo.rocket.RocketEntity;
import com.ricky.terrariamod.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class RocketLauncherItem extends BowItem {
    private final int explosionPower;
    private final double speedMultiplier;

    public RocketLauncherItem(Properties properties, int explosionPower, double speedMultiplier) {
        super(properties);
        this.explosionPower = explosionPower;
        this.speedMultiplier = speedMultiplier;
    }

    @Override
    public void releaseUsing(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity user, int remainingUseTicks) {
        if (user instanceof Player player) {
            boolean isCreativeOrInfinity = player.getAbilities().instabuild ||
                    EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0;

            ItemStack rocketStack = findRocket(player);

            if (rocketStack != null || isCreativeOrInfinity) {
                int useTime = this.getUseDuration(stack) - remainingUseTicks;
                float pullProgress = customGetPullProgress(useTime);

                if (pullProgress >= 0.1F) {
                    Vec3 look = user.getLookAngle();

                    double speedX = look.x * pullProgress * this.speedMultiplier;
                    double speedY = look.y * pullProgress * this.speedMultiplier;
                    double speedZ = look.z * pullProgress * this.speedMultiplier;

                    RocketEntity rocket = new RocketEntity(level, user, this.explosionPower);

                    rocket.setPos(
                            user.getX() + look.x * 1.5,
                            user.getEyeY() - 0.1 + look.y * 1.5,
                            user.getZ() + look.z * 1.5
                    );
                    rocket.setDeltaMovement(speedX, speedY, speedZ);

                    level.addFreshEntity(rocket);

                    stack.hurtAndBreak(1, user, (e) -> e.broadcastBreakEvent(user.getUsedItemHand()));

                    if (!isCreativeOrInfinity && rocketStack != null) {
                        rocketStack.shrink(1);
                        if (rocketStack.isEmpty()) {
                            player.getInventory().removeItem(rocketStack);
                        }
                    }

                    level.playSound(null, user.getX(), user.getY(), user.getZ(),
                            SoundEvents.GHAST_SHOOT,
                            SoundSource.PLAYERS,
                            1.0F,
                            1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + pullProgress * 0.5F
                    );

                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        ItemStack rocketStack = findRocket(player);
        boolean hasRocket = rocketStack != null && !rocketStack.isEmpty();

        if (!player.getAbilities().instabuild && !hasRocket) {
            return InteractionResultHolder.fail(itemStack);
        } else {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(itemStack);
        }
    }

    public float customGetPullProgress(int useTicks) {
        float progress = (float) useTicks / 15.0F;
        progress = (progress * progress + progress * 2.0F) / 3.0F;
        return Mth.clamp(progress, 0.0F, 1.0F);
    }

    private ItemStack findRocket(Player player) {
        for (ItemStack stack : player.getInventory().items) {
            if (stack.is(ModItems.ROCKET.get())) {
                return stack;
            }
        }
        return null;
    }
}
