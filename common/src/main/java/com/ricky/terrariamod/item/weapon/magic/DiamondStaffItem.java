package com.ricky.terrariamod.item.weapon.magic;

import com.ricky.terrariamod.entity.projectile.magic.MagicBallEntity;
import com.ricky.terrariamod.item.weapon.AttackableItem;
import com.ricky.terrariamod.util.ManaUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;

public class DiamondStaffItem extends SwordItem implements AttackableItem {

    public DiamondStaffItem(Properties properties) {
        super(Tiers.WOOD, 1, -2.4f, properties);
    }

    @Override
    public void attack(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            if (ManaUtil.useMana(player, 40)) {
                ItemStack stack = player.getItemInHand(hand);
                stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));

                MagicBallEntity ball = new MagicBallEntity(level, player, stack, 2, 0, 6, 0.63f, 0.99f, 0.9f);
                ball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 0F);

                if (player.getAbilities().instabuild) {
                    ball.pickup = AbstractArrow.Pickup.DISALLOWED;
                }

                level.addFreshEntity(ball);
            }
        }
    }
}
