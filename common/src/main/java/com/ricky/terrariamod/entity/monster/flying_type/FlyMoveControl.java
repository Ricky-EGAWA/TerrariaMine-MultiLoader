package com.ricky.terrariamod.entity.monster.flying_type;

import com.ricky.terrariamod.entity.monster.flying_type.demon_eye.DemonEyeEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.phys.Vec3;

public class FlyMoveControl extends MoveControl {
    private final DemonEyeEntity demon;

    public FlyMoveControl(DemonEyeEntity demon) {
        super(demon);
        this.demon = demon;
    }

    @Override
    public void tick() {
        if (this.operation == Operation.MOVE_TO) {
            Vec3 vec3 = new Vec3(this.wantedX - demon.getX(), this.wantedY - demon.getY(), this.wantedZ - demon.getZ());
            double distance = vec3.length();

            if (distance < demon.getBoundingBox().getSize()) {
                this.operation = Operation.WAIT;
            } else {
                double speed = this.speedModifier * demon.getAttributeValue(Attributes.MOVEMENT_SPEED);
                demon.setDeltaMovement(vec3.normalize().scale(speed));

                // 向きの調整（進行方向に合わせる）
                float yaw = (float)(Mth.atan2(vec3.z, vec3.x) * (180F / Math.PI)) - 90F;  // 水平方向
                float pitch = (float)(-(Mth.atan2(vec3.y, Math.sqrt(vec3.x * vec3.x + vec3.z * vec3.z)) * (180F / Math.PI))); // 垂直方向

                demon.setYRot(yaw);   // 横方向の回転
                demon.setYBodyRot(yaw); // 体の回転
                demon.setXRot(pitch);  // 縦方向の回転

                demon.getLookControl().setLookAt(new Vec3(this.wantedX, this.wantedY, this.wantedZ));  // 目標を見つめる
            }
        }
    }
}
