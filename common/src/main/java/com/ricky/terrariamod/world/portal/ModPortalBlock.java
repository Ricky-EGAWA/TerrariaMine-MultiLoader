package com.ricky.terrariamod.world.portal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ModPortalBlock extends NetherPortalBlock {
    public ModPortalBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!level.isClientSide && entity instanceof ServerPlayer player) {
            // プレイヤーがディメンション間でクールダウン中でなければ
            if (!player.isPassenger() && !player.isVehicle() && player.canChangeDimensions()) {
                player.setPortalCooldown(); // クールダウンを設定
                ModTeleportation.teleportPlayer(player);
            }
        }
    }
}
