package com.ricky.terrariamod.entity.monster.flying_type.demon_eye;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ricky.terrariamod.Constants;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class DemonEyeModel<T extends Entity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MOD_ID, "demon_eye"), "main");
    private final ModelPart demon_eye;

    public DemonEyeModel(ModelPart root) {
        this.demon_eye = root.getChild("demon_eye");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition demon_eye = partdefinition.addOrReplaceChild("demon_eye", CubeListBuilder.create().texOffs(0, 42).addBox(-3.5F, -3.5F, -3.5F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 17.5F, 0.5F));

        PartDefinition bone = demon_eye.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(32, 14).addBox(-2.025F, -20.015F, -4.945F, 5.0F, 3.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(32, 28).addBox(-3.035F, -21.025F, -3.955F, 7.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 18.5F, -0.5F));

        PartDefinition cube_r1 = bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(32, 14).addBox(-2.5F, -1.5F, -5.5F, 5.0F, 3.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(32, 28).addBox(-3.5F, -2.5F, -4.5F, 7.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -18.5F, 0.5F, 0.0F, 0.0F, 1.5708F));

        PartDefinition bone2 = demon_eye.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 0).addBox(-2.51F, -1.505F, -5.47F, 5.0F, 3.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(0, 14).addBox(-3.52F, -2.515F, -4.48F, 7.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.015F, -0.01F, 0.025F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r2 = bone2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(32, 0).addBox(-2.5F, -1.5F, -5.5F, 5.0F, 3.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(0, 28).addBox(-3.5F, -2.5F, -4.5F, 7.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.015F, 0.01F, -0.025F, 0.0F, 0.0F, 1.5708F));

        PartDefinition bone3 = demon_eye.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 0).addBox(-2.51F, -1.505F, -5.47F, 5.0F, 3.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(0, 14).addBox(-3.52F, -2.515F, -4.48F, 7.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.015F, -0.01F, 0.025F, -1.5708F, -1.5708F, 0.0F));

        PartDefinition cube_r3 = bone3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(32, 0).addBox(-2.5F, -1.5F, -5.5F, 5.0F, 3.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(0, 28).addBox(-3.5F, -2.5F, -4.5F, 7.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.015F, 0.01F, -0.025F, 0.0F, 0.0F, 1.5708F));

        PartDefinition tail = demon_eye.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(-0.5F, 6.5F, -0.5F));

        PartDefinition cube_r4 = tail.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(28, 42).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -4.5F, 12.0F, -0.2618F, 0.3491F, 0.0F));

        PartDefinition cube_r5 = tail.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(28, 42).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -4.5F, 9.0F, -0.2618F, 0.3491F, 0.0F));

        PartDefinition cube_r6 = tail.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(28, 42).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -6.5F, 6.0F, -0.2618F, 0.3491F, 0.0F));

        PartDefinition cube_r7 = tail.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(28, 42).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -6.5F, 7.0F, -0.2618F, 0.3491F, 0.0F));

        PartDefinition cube_r8 = tail.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(28, 42).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8138F, -4.6771F, 7.6158F, -0.1745F, -0.3491F, -0.1309F));

        PartDefinition cube_r9 = tail.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(28, 42).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.2816F, -7.7776F, 9.6158F, 0.3054F, 0.2182F, 0.0F));

        PartDefinition cube_r10 = tail.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(28, 42).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -5.0F, 7.0F, 0.3054F, -0.3054F, 0.0F));

        PartDefinition cube_r11 = tail.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(28, 42).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 11.0F, 0.3054F, -0.3054F, 0.0F));

        PartDefinition cube_r12 = tail.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(28, 42).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 10.0F, 0.3054F, -0.3054F, 0.0F));

        PartDefinition cube_r13 = tail.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(28, 42).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2816F, -8.7776F, 5.6158F, 0.3054F, 0.3491F, 0.0F));

        PartDefinition cube_r14 = tail.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(28, 42).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2816F, -8.7776F, 6.6158F, 0.3054F, -0.3054F, 0.0F));

        PartDefinition cube_r15 = tail.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(28, 42).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, 5.0F, 0.3054F, -0.3054F, 0.0F));

        PartDefinition cube_r16 = tail.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(28, 42).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.7184F, -5.7776F, 5.6158F, -0.2618F, -0.3491F, 0.0F));

        PartDefinition cube_r17 = tail.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(28, 42).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -7.0F, 5.0F, 0.3054F, -0.3054F, 0.0F));

        PartDefinition cube_r18 = tail.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(28, 42).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 7.0F, 0.3054F, -0.3054F, 0.0F));

        PartDefinition cube_r19 = tail.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(28, 42).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7184F, -5.7776F, 12.6158F, -0.3054F, -0.4363F, 0.0F));

        PartDefinition cube_r20 = tail.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(28, 42).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7184F, -3.7776F, 10.6158F, -0.3054F, -0.4363F, 0.0F));

        PartDefinition cube_r21 = tail.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(28, 42).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -6.5F, 11.0F, -0.1745F, 0.0436F, 0.0F));

        PartDefinition cube_r22 = tail.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(28, 42).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4981F, -5.5F, 10.9128F, -0.2618F, 0.2618F, 0.0F));

        PartDefinition cube_r23 = tail.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(28, 42).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -6.5F, 8.0F, -0.1745F, 0.0436F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(@NotNull Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        demon_eye.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
