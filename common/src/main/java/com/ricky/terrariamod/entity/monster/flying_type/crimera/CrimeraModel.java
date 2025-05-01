package com.ricky.terrariamod.entity.monster.flying_type.crimera;// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


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

public class CrimeraModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MOD_ID, "crimera"), "main");
	private final ModelPart crimera;

	public CrimeraModel(ModelPart root) {
		this.crimera = root.getChild("crimera");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition crimera = partdefinition.addOrReplaceChild("crimera", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 20.0F, 0.0F, -1.2654F, 0.0F, 0.0F));

		PartDefinition body = crimera.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 38).addBox(0.0F, -35.0F, -1.5F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(30, 0).addBox(-1.0F, -30.0F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(3, 21).addBox(-2.0F, -25.0F, -2.5F, 5.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 20).addBox(-3.0F, -22.0F, -3.0F, 7.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-4.0F, -19.0F, -3.5F, 9.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 12).addBox(-3.0F, -13.0F, -3.5F, 7.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(24, 20).addBox(-2.0F, -11.0F, -2.5F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, 0.0F));

		PartDefinition bone = body.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 28).addBox(5.0F, -19.0F, -2.5F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 33).addBox(5.0F, -18.0F, -2.5F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(14, 33).addBox(5.0F, -17.0F, -2.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bone2 = body.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 28).addBox(5.0F, -19.0F, -2.5F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 33).addBox(5.0F, -18.0F, -2.5F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(14, 33).addBox(5.0F, -17.0F, -2.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, -1.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition bone3 = body.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(30, 8).addBox(3.0F, -23.0F, -2.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(34, 35).addBox(4.0F, -22.0F, -2.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(16, 28).addBox(4.0F, -21.0F, -2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bone4 = body.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(30, 8).addBox(-2.3333F, -1.5F, -1.5F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(34, 35).addBox(-1.3333F, -0.5F, -1.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(16, 28).addBox(-1.3333F, 0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.1667F, -21.5F, -0.5F, 0.0F, 3.1416F, 0.0F));

		PartDefinition bone5 = body.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(38, 27).addBox(4.0F, -28.0F, -1.5F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(6, 38).addBox(2.0F, -27.0F, -1.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bone6 = body.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(38, 27).addBox(4.0F, -28.0F, -1.5F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(6, 38).addBox(2.0F, -27.0F, -1.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, -1.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition bone7 = body.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(26, 17).addBox(-3.0F, -31.0F, -1.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(38, 39).addBox(-4.0F, -32.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(38, 39).addBox(2.0F, -32.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.0F, 0.0F));

		PartDefinition bone8 = body.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(38, 17).addBox(-2.0F, -31.0F, -1.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 40).addBox(-3.0F, -32.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(26, 40).addBox(1.0F, -32.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(34, 39).addBox(-1.0F, -35.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(34, 39).addBox(0.0F, -37.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -4.0F, 0.0F));

		PartDefinition eye = crimera.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(24, 27).addBox(4.0F, -13.0F, -2.5F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(24, 35).addBox(5.5F, -9.0F, -2.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(38, 31).addBox(5.0F, -5.0F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(38, 33).addBox(6.5F, -4.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(38, 33).addBox(-6.5F, -4.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(38, 31).addBox(-6.0F, -5.0F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(14, 38).addBox(4.5F, -7.0F, -1.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(14, 38).addBox(-5.5F, -7.0F, -1.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 35).addBox(-6.5F, -9.0F, -2.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(24, 27).addBox(-6.0F, -13.0F, -2.5F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		crimera.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}