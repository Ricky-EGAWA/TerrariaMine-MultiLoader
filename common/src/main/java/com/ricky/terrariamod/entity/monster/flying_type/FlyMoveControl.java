package com.ricky.terrariamod.entity.monster.flying_type;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.phys.Vec3;

public class FlyMoveControl extends MoveControl {
    private final FlyingMob fly_mob;

    public FlyMoveControl(FlyingMob fly_mob) {
        super(fly_mob);
        this.fly_mob = fly_mob;
    }

    @Override
    public void tick() {
        if (this.operation == Operation.MOVE_TO) {
            Vec3 vec3 = new Vec3(this.wantedX - fly_mob.getX(), this.wantedY - fly_mob.getY(), this.wantedZ - fly_mob.getZ());
            double distance = vec3.length();

            if (distance < fly_mob.getBoundingBox().getSize()) {
                this.operation = Operation.WAIT;
            } else {
                double speed = this.speedModifier * fly_mob.getAttributeValue(Attributes.MOVEMENT_SPEED);
                fly_mob.setDeltaMovement(vec3.normalize().scale(speed));

                float yaw = (float)(Mth.atan2(vec3.z, vec3.x) * (180F / Math.PI)) - 90F;
                float pitch = (float)(-(Mth.atan2(vec3.y, Math.sqrt(vec3.x * vec3.x + vec3.z * vec3.z)) * (180F / Math.PI)));

                fly_mob.setYRot(yaw);
                fly_mob.setYBodyRot(yaw);
                fly_mob.setXRot(pitch);

                fly_mob.getLookControl().setLookAt(new Vec3(this.wantedX, this.wantedY, this.wantedZ));
            }
        }
    }
}
