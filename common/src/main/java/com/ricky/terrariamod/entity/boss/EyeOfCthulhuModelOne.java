package com.ricky.terrariamod.entity.boss;

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

public class EyeOfCthulhuModelOne<T extends EyeOfCthulhuEntity> extends EyeOfCthulhuModelBase<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MOD_ID, "eyeofcthulhu"), "main");
	private final ModelPart eye_of_cthulhu;
	private final ModelPart bone;
	private final ModelPart bone2;
	private final ModelPart bone3;
	private final ModelPart tail;
	private final ModelPart bone4;
	private final ModelPart bone5;
	private final ModelPart bone6;
	private final ModelPart bone7;
	private final ModelPart bone8;
	private final ModelPart bone9;
	private final ModelPart bone10;
	private final ModelPart bone11;
	private final ModelPart bone12;
	private final ModelPart bone13;

	public EyeOfCthulhuModelOne(ModelPart root) {
		this.eye_of_cthulhu = root.getChild("eye_of_cthulhu");
		this.bone = this.eye_of_cthulhu.getChild("bone");
		this.bone2 = this.eye_of_cthulhu.getChild("bone2");
		this.bone3 = this.eye_of_cthulhu.getChild("bone3");
		this.tail = this.eye_of_cthulhu.getChild("tail");
		this.bone4 = this.tail.getChild("bone4");
		this.bone5 = this.tail.getChild("bone5");
		this.bone6 = this.tail.getChild("bone6");
		this.bone7 = this.tail.getChild("bone7");
		this.bone8 = this.tail.getChild("bone8");
		this.bone9 = this.tail.getChild("bone9");
		this.bone10 = this.tail.getChild("bone10");
		this.bone11 = this.tail.getChild("bone11");
		this.bone12 = this.tail.getChild("bone12");
		this.bone13 = this.tail.getChild("bone13");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition eye_of_cthulhu = partdefinition.addOrReplaceChild("eye_of_cthulhu", CubeListBuilder.create(), PartPose.offset(-4.0F, -8.0F, 0.0F));

		PartDefinition bone = eye_of_cthulhu.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(368, 312).addBox(-34.2275F, -6.0125F, -9.9525F, 68.0F, 12.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(190, 144).addBox(-29.8875F, -9.9525F, -18.0525F, 60.0F, 20.0F, 36.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-25.8875F, -13.9525F, -22.0525F, 52.0F, 28.0F, 44.0F, new CubeDeformation(0.0F))
		.texOffs(192, 0).addBox(-26.1475F, -18.1325F, -17.8725F, 52.0F, 36.0F, 36.0F, new CubeDeformation(0.0F))
		.texOffs(0, 216).addBox(-30.1875F, -14.0925F, -13.9125F, 60.0F, 28.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(352, 416).addBox(-33.8875F, -9.9525F, -6.0525F, 68.0F, 20.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(368, 256).addBox(-30.5875F, -17.7725F, -10.1525F, 61.0F, 36.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(0, 328).addBox(-25.8875F, -21.9525F, -14.0525F, 52.0F, 44.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offset(3.8875F, -2.0475F, -0.9475F));

		PartDefinition bone2 = eye_of_cthulhu.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 400).addBox(-34.2275F, -6.0125F, -9.9525F, 68.0F, 12.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(192, 72).addBox(-29.8875F, -9.9525F, -18.0525F, 60.0F, 20.0F, 36.0F, new CubeDeformation(0.0F))
		.texOffs(0, 144).addBox(-25.1481F, -14.0925F, -21.8308F, 51.0F, 28.0F, 44.0F, new CubeDeformation(0.0F))
		.texOffs(190, 200).addBox(-26.1475F, -18.1325F, -17.8725F, 52.0F, 36.0F, 36.0F, new CubeDeformation(0.0F))
		.texOffs(160, 344).addBox(-30.1875F, -14.0925F, -13.9125F, 60.0F, 28.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(0, 432).addBox(-33.8875F, -9.9525F, -6.0525F, 68.0F, 20.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(382, 128).addBox(-29.8875F, -17.9525F, -10.0525F, 60.0F, 36.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(336, 344).addBox(-25.8875F, -21.9525F, -14.0525F, 52.0F, 44.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.8875F, -2.0475F, -0.9475F, 0.0F, -1.5708F, 0.0F));

		PartDefinition bone3 = eye_of_cthulhu.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(176, 416).addBox(-34.2275F, -6.0125F, -9.9525F, 68.0F, 12.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(0, 272).addBox(-29.8875F, -9.9525F, -18.0525F, 60.0F, 20.0F, 36.0F, new CubeDeformation(0.0F))
		.texOffs(0, 72).addBox(-25.8875F, -13.9525F, -22.0525F, 52.0F, 28.0F, 44.0F, new CubeDeformation(0.0F))
		.texOffs(192, 272).addBox(-26.1475F, -18.1325F, -17.8725F, 52.0F, 36.0F, 36.0F, new CubeDeformation(0.0F))
		.texOffs(366, 200).addBox(-30.1875F, -14.0925F, -13.9125F, 60.0F, 28.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(160, 448).addBox(-33.8875F, -9.9525F, -6.0525F, 68.0F, 20.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(384, 72).addBox(-29.1891F, -18.1319F, -9.9525F, 59.0F, 36.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(368, 0).addBox(-25.1491F, -22.1718F, -13.9125F, 51.0F, 44.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.8875F, -2.0475F, -0.9475F, 0.0F, 0.0F, 1.5708F));

		PartDefinition tail = eye_of_cthulhu.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(-2.0F, 44.0F, -7.0F));

		PartDefinition bone4 = tail.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offset(-2.0F, -38.0F, 38.0F));

		PartDefinition cube_r1 = bone4.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(64, 464).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.0F, -12.0F, -4.0F, 0.2182F, 0.3491F, 0.0F));

		PartDefinition cube_r2 = bone4.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(96, 464).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, -4.0F, -0.1745F, -0.3927F, 0.0F));

		PartDefinition cube_r3 = bone4.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(32, 464).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -4.0F, 0.0F, -0.3491F, -0.3927F, 0.0F));

		PartDefinition cube_r4 = bone4.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(192, 128).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		PartDefinition cube_r5 = bone4.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(224, 128).addBox(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 6.0F, 10.0F, -0.0873F, -0.48F, 0.0F));

		PartDefinition bone5 = tail.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offsetAndRotation(18.0F, -62.0F, 30.0F, 0.6473F, -0.4878F, 0.6674F));

		PartDefinition cube_r6 = bone5.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(288, 128).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.48F, 0.0F));

		PartDefinition cube_r7 = bone5.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(256, 128).addBox(-2.0F, -0.8F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 4.0F, 8.0F, -0.0873F, 0.48F, 0.0F));

		PartDefinition bone6 = tail.addOrReplaceChild("bone6", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.6579F, -64.4511F, 42.5857F, 0.6473F, -0.4878F, 0.6674F));

		PartDefinition cube_r8 = bone6.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(160, 328).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1945F, -0.6368F, -10.7038F, -0.3927F, -0.48F, -0.9599F));

		PartDefinition cube_r9 = bone6.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(320, 128).addBox(-3.8524F, -1.0879F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6579F, 3.6511F, -2.7038F, -0.0436F, -0.48F, -0.9599F));

		PartDefinition bone7 = tail.addOrReplaceChild("bone7", CubeListBuilder.create(), PartPose.offsetAndRotation(18.0F, -38.0F, 38.0F, 0.0F, 0.0F, -1.309F));

		PartDefinition cube_r10 = bone7.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(176, 400).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		PartDefinition cube_r11 = bone7.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(382, 184).addBox(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 6.0F, 10.0F, -0.8636F, -0.6627F, 0.2237F));

		PartDefinition bone8 = tail.addOrReplaceChild("bone8", CubeListBuilder.create(), PartPose.offset(-10.0F, -50.0F, 30.0F));

		PartDefinition cube_r12 = bone8.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(240, 400).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		PartDefinition cube_r13 = bone8.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(208, 400).addBox(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 6.0F, 10.0F, -0.1603F, -0.2648F, -0.039F));

		PartDefinition bone9 = tail.addOrReplaceChild("bone9", CubeListBuilder.create(), PartPose.offsetAndRotation(26.0F, -50.0F, 30.0F, 0.0F, 0.0F, -1.7017F));

		PartDefinition cube_r14 = bone9.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(304, 400).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		PartDefinition cube_r15 = bone9.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(272, 400).addBox(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 6.0F, 10.0F, -0.3054F, -0.48F, 0.0F));

		PartDefinition bone10 = tail.addOrReplaceChild("bone10", CubeListBuilder.create(), PartPose.offsetAndRotation(6.0F, -66.0F, 30.0F, 0.3054F, 0.0F, 2.5744F));

		PartDefinition cube_r16 = bone10.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(446, 184).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		PartDefinition cube_r17 = bone10.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(414, 184).addBox(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 6.0F, 10.0F, -0.829F, -0.48F, 0.0F));

		PartDefinition bone11 = tail.addOrReplaceChild("bone11", CubeListBuilder.create(), PartPose.offsetAndRotation(10.0F, -30.0F, 34.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition cube_r18 = bone11.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(352, 448).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		PartDefinition cube_r19 = bone11.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(320, 448).addBox(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 6.0F, 10.0F, -0.0873F, -0.48F, 0.0F));

		PartDefinition bone12 = tail.addOrReplaceChild("bone12", CubeListBuilder.create(), PartPose.offsetAndRotation(10.0F, -50.0F, 34.0F, 0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r20 = bone12.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(416, 448).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		PartDefinition cube_r21 = bone12.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(384, 448).addBox(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 6.0F, 10.0F, -0.0873F, -0.48F, 0.0F));

		PartDefinition bone13 = tail.addOrReplaceChild("bone13", CubeListBuilder.create(), PartPose.offsetAndRotation(14.0F, -46.0F, 34.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r22 = bone13.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 464).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		PartDefinition cube_r23 = bone13.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(448, 448).addBox(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 6.0F, 10.0F, -0.2435F, -0.3109F, -0.0472F));

		return LayerDefinition.create(meshdefinition, 1024, 1024);
	}

	@Override
	public void setupAnim(@NotNull EyeOfCthulhuEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		eye_of_cthulhu.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return eye_of_cthulhu;
	}
}