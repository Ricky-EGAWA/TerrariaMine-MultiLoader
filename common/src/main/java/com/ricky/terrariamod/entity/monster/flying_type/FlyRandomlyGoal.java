package com.ricky.terrariamod.entity.monster.flying_type;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class FlyRandomlyGoal extends Goal {
    private final FlyingMob fly_mob;

    public FlyRandomlyGoal(FlyingMob fly_mob) {
        this.fly_mob = fly_mob;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return this.fly_mob.getTarget() == null && !this.fly_mob.getMoveControl().hasWanted();
    }

    @Override
    public void start() {
        RandomSource random = this.fly_mob.getRandom();
        double x, y, z;

        for (int i = 0; i < 10; i++) {
            y = this.fly_mob.getY() + (random.nextDouble() * 2 - 1) * 2;
            x = this.fly_mob.getX() + (random.nextDouble() * 32 - 16);
            z = this.fly_mob.getZ() + (random.nextDouble() * 32 - 16);

            y = Mth.clamp(y, this.fly_mob.level().getMinBuildHeight() + 5, this.fly_mob.level().getMaxBuildHeight() - 5);

            if (this.fly_mob.level().noCollision(this.fly_mob, this.fly_mob.getBoundingBox().move(x - this.fly_mob.getX(), y - this.fly_mob.getY(), z - this.fly_mob.getZ()))) {
                this.fly_mob.getMoveControl().setWantedPosition(x, y, z, 1.0);

                double dx = x - this.fly_mob.getX();
                double dy = y - this.fly_mob.getY();
                double dz = z - this.fly_mob.getZ();

                double horizontalDist = Math.sqrt(dx * dx + dz * dz);
                float yaw = (float)(Mth.atan2(dz, dx) * (180F / Math.PI)) - 90F;
                float pitch = (float)(-(Mth.atan2(dy, horizontalDist) * (180F / Math.PI)));

                this.fly_mob.setYRot(yaw);
                this.fly_mob.setYBodyRot(yaw);
                this.fly_mob.setXRot(pitch);
                break;
            }
        }
    }
}
