package com.ricky.terrariamod.item.weapon;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public interface AttackableItem {
    void attack(Level world, Player player, InteractionHand hand);
}