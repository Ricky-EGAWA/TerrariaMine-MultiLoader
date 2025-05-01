package com.ricky.terrariamod.entity.monster.flying_type.eater_of_soul;

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

public class EaterOfSoulModel<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MOD_ID, "eater_of_soul"), "main");
	private final ModelPart eater;

	public EaterOfSoulModel(ModelPart root) {
		this.eater = root.getChild("eater");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition eater = partdefinition.addOrReplaceChild("eater", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 21.0F, 0.0F, -1.1781F, 0.0F, 0.0F));

		PartDefinition body = eater.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -15.0F, -2.0F, 8.0F, 8.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(14, 20).addBox(4.0F, -14.0F, -1.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(24, 20).addBox(-6.0F, -14.0F, -1.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 13).addBox(-3.0F, -19.0F, -1.0F, 6.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(20, 29).addBox(-2.0F, -22.0F, 0.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(30, 29).addBox(-1.0F, -11.0F, -3.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 32).addBox(-1.0F, -25.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(18, 13).addBox(-3.0F, -14.0F, 3.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 20).addBox(-3.0F, -14.0F, -3.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(34, 13).addBox(1.0F, -7.0F, 0.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(34, 13).addBox(-1.0F, -7.0F, 0.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, 0.0F));

		PartDefinition horn = eater.addOrReplaceChild("horn", CubeListBuilder.create().texOffs(26, 0).addBox(2.0F, -9.0F, -1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(26, 5).addBox(3.0F, -7.0F, -1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(26, 10).addBox(3.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(34, 24).addBox(3.0F, -3.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(34, 33).addBox(2.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 0.0F));

		PartDefinition horn2 = eater.addOrReplaceChild("horn2", CubeListBuilder.create().texOffs(0, 27).addBox(2.0F, -9.0F, -1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(10, 29).addBox(1.0F, -7.0F, -1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(32, 10).addBox(1.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(24, 35).addBox(2.0F, -3.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 36).addBox(3.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 17.0F, 0.0F));

		PartDefinition bone = eater.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(6, 32).addBox(7.0F, -16.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 33).addBox(7.0F, -23.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 34).addBox(-8.0F, -23.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(36, 0).addBox(6.0F, -20.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 36).addBox(-7.0F, -20.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(36, 3).addBox(4.0F, -23.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(12, 36).addBox(-5.0F, -23.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 36).addBox(5.0F, -25.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(36, 16).addBox(-6.0F, -25.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(30, 33).addBox(3.0F, -28.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(34, 20).addBox(-4.0F, -28.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(36, 6).addBox(0.0F, -31.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(32, 16).addBox(-8.0F, -16.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(10, 27).addBox(6.0F, -13.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(34, 36).addBox(3.0F, -21.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(38, 11).addBox(-4.0F, -21.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 37).addBox(2.0F, -25.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(38, 13).addBox(-3.0F, -25.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(28, 37).addBox(0.0F, -29.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(38, 9).addBox(-1.0F, -29.5F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(24, 33).addBox(4.0F, -18.0F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(10, 34).addBox(-6.0F, -18.0F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(34, 27).addBox(-7.0F, -13.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(@NotNull Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		eater.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}