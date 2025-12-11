package com.ricky.terrariamod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SpikeBlock extends Block {
    private static final float BASE_DAMAGE = 2.0f;
    private static final float VELOCITY_MULTIPLIER = 5.0f;
    private static final VoxelShape SHAPE = Block.box(1, 0, 1, 15, 14, 15);

    public SpikeBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!level.isClientSide && entity instanceof LivingEntity livingEntity) {
            // Calculate damage based on entity's velocity
            Vec3 velocity = entity.getDeltaMovement();
            double speed = velocity.length();

            // Calculate damage: base damage + velocity-based damage
            float damage = BASE_DAMAGE + (float)(speed * VELOCITY_MULTIPLIER);

            // Apply minimum damage
            damage = Math.max(damage, BASE_DAMAGE);

            // Hurt the entity with generic damage
            livingEntity.hurt(level.damageSources().generic(), damage);
        }
        super.entityInside(state, level, pos, entity);
    }
}
