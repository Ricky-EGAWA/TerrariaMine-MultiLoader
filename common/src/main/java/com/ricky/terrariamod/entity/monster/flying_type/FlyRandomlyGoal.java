package com.ricky.terrariamod.entity.monster.flying_type;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class FlyRandomlyGoal extends Goal {
    private final FlyingMob flyMob;
    private double targetX, targetY, targetZ;

    public FlyRandomlyGoal(FlyingMob flyMob) {
        this.flyMob = flyMob;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return flyMob.getTarget() == null && !flyMob.getMoveControl().hasWanted();
    }

    @Override
    public boolean canContinueToUse() {
        return !flyMob.getNavigation().isDone()
                && flyMob.distanceToSqr(targetX, targetY, targetZ) > 4;
    }

    @Override
    public void start() {
        RandomSource random = flyMob.getRandom();

        for (int i = 0; i < 10; i++) {
            double x = flyMob.getX() + (random.nextDouble() * 32 - 16);
            double y = flyMob.getY() + (random.nextDouble() * 8 - 4);
            double z = flyMob.getZ() + (random.nextDouble() * 32 - 16);

            y = Mth.clamp(y,
                    flyMob.level().getMinBuildHeight() + 5,
                    flyMob.level().getMaxBuildHeight() - 5);

            if (flyMob.level().noCollision(flyMob,
                    flyMob.getBoundingBox().move(x - flyMob.getX(), y - flyMob.getY(), z - flyMob.getZ()))) {

                this.targetX = x;
                this.targetY = y;
                this.targetZ = z;

                flyMob.getMoveControl().setWantedPosition(x, y, z, 1.0);
                break;
            }
        }
    }
}
