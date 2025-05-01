package com.ricky.terrariamod.entity.monster.flying_type;

import com.ricky.terrariamod.entity.monster.flying_type.demon_eye.DemonEyeEntity;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class FlyRandomlyGoal extends Goal {
    private final DemonEyeEntity demon;

    public FlyRandomlyGoal(DemonEyeEntity demon) {
        this.demon = demon;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return this.demon.getTarget() == null && !this.demon.getMoveControl().hasWanted();
    }

    @Override
    public void start() {
        RandomSource random = this.demon.getRandom();
        double x, y, z;

        // 最大10回までランダム位置を試す
        for (int i = 0; i < 10; i++) {
            y = this.demon.getY() + (random.nextDouble() * 2 - 1) * 2;
            x = this.demon.getX() + (random.nextDouble() * 32 - 16);
            z = this.demon.getZ() + (random.nextDouble() * 32 - 16);

            y = Mth.clamp(y, this.demon.level().getMinBuildHeight() + 5, this.demon.level().getMaxBuildHeight() - 5);

            // 移動先のAABB（BoundingBox）で衝突判定
            if (this.demon.level().noCollision(this.demon, this.demon.getBoundingBox().move(x - this.demon.getX(), y - this.demon.getY(), z - this.demon.getZ()))) {
                this.demon.getMoveControl().setWantedPosition(x, y, z, 1.0);

                // 向きを設定
                double dx = x - this.demon.getX();
                double dy = y - this.demon.getY();
                double dz = z - this.demon.getZ();

                double horizontalDist = Math.sqrt(dx * dx + dz * dz);
                float yaw = (float)(Mth.atan2(dz, dx) * (180F / Math.PI)) - 90F;
                float pitch = (float)(-(Mth.atan2(dy, horizontalDist) * (180F / Math.PI)));

                this.demon.setYRot(yaw);
                this.demon.setYBodyRot(yaw);
                this.demon.setXRot(pitch);
                break;
            }
        }
    }
}
