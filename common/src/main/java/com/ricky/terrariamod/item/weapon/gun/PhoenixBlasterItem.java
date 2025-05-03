package com.ricky.terrariamod.item.weapon.gun;

import com.ricky.terrariamod.entity.projectile.ammo.musket_ball.MusketBallEntity;
import com.ricky.terrariamod.item.ModItems;
import com.ricky.terrariamod.item.weapon.AttackableItem;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;

public class PhoenixBlasterItem extends SwordItem implements AttackableItem {
    public PhoenixBlasterItem(Properties properties) {
        super(Tiers.IRON, -1, -2.4f, properties);
    }

    @Override
    public void attack(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            ItemStack ammo = findMusketBall(player);
            if (player.getAbilities().instabuild || ammo != null) {
                if (!player.getAbilities().instabuild) {
                    ammo.shrink(1);
                }
                shoot(level, player, hand);
            }
        }
    }

    private static Projectile createBullet(Level level, LivingEntity shooter) {
        MusketBallEntity bullet = new MusketBallEntity(level, shooter, 4);

        bullet.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot(), 0.0F, 4.0F, 1.0F);
        bullet.setCritArrow(false);
        bullet.setPierceLevel((byte) 0);
        bullet.pickup = net.minecraft.world.entity.projectile.AbstractArrow.Pickup.DISALLOWED;

        return bullet;
    }

    private static void shoot(Level level, LivingEntity shooter, InteractionHand hand) {
        Projectile bullet = createBullet(level, shooter);
        if (shooter instanceof ServerPlayer player) {
            ItemStack gun = player.getItemInHand(hand);

            // 耐久値を1減らす
            gun.hurt(1, player.getRandom(), player);
        }

        level.addFreshEntity(bullet);
        level.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(),
                SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 0.7F, 1.0F);
    }

    private static ItemStack findMusketBall(Player player) {
        for (ItemStack stack : player.getInventory().items) {
            if (stack.is(ModItems.MUSKET_BALL.get())) {
                return stack;
            }
        }
        return null;
    }
}
