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

public class EyeOfCthulhuModelTwo<T extends EyeOfCthulhuEntity> extends EyeOfCthulhuModelBase<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MOD_ID, "eyeofcthulhu2"), "main");
	private final ModelPart eye_of_cthulhu;
	private final ModelPart bone;
	private final ModelPart bone2;
	private final ModelPart bone3;
	private final ModelPart mouth_up;
	private final ModelPart up;
	private final ModelPart teeth;
	private final ModelPart mouth_up2;
	private final ModelPart down;
	private final ModelPart teeth2;
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

	public EyeOfCthulhuModelTwo(ModelPart root) {
		this.eye_of_cthulhu = root.getChild("eye_of_cthulhu");
		this.bone = this.eye_of_cthulhu.getChild("bone");
		this.bone2 = this.eye_of_cthulhu.getChild("bone2");
		this.bone3 = this.eye_of_cthulhu.getChild("bone3");
		this.mouth_up = this.eye_of_cthulhu.getChild("mouth_up");
		this.up = this.mouth_up.getChild("up");
		this.teeth = this.mouth_up.getChild("teeth");
		this.mouth_up2 = this.eye_of_cthulhu.getChild("mouth_up2");
		this.down = this.mouth_up2.getChild("down");
		this.teeth2 = this.mouth_up2.getChild("teeth2");
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

		PartDefinition eye_of_cthulhu = partdefinition.addOrReplaceChild("eye_of_cthulhu", CubeListBuilder.create(), PartPose.offset(-2.0F, 36.0F, -7.0F));

		PartDefinition bone = eye_of_cthulhu.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(138, 251).addBox(-34.2275F, -6.0125F, 0.0475F, 68.0F, 12.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 49).addBox(-29.8875F, -9.9525F, -0.0525F, 60.0F, 20.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-25.8875F, -13.9525F, 0.9475F, 52.0F, 28.0F, 21.0F, new CubeDeformation(0.0F))
		.texOffs(0, 87).addBox(-26.1475F, -18.1325F, 0.1275F, 52.0F, 36.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(156, 42).addBox(-30.1875F, -14.0925F, 2.0875F, 60.0F, 28.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(282, 273).addBox(-33.8875F, -9.9525F, -0.0525F, 68.0F, 20.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 193).addBox(-30.5875F, -17.7725F, -0.1525F, 61.0F, 36.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(140, 135).addBox(-25.8875F, -21.9525F, -0.0525F, 52.0F, 44.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(5.8875F, -46.0475F, 6.0525F));

		PartDefinition bone2 = eye_of_cthulhu.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(294, 229).addBox(12.7725F, -6.0125F, -9.9525F, 21.0F, 12.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(282, 299).addBox(12.1125F, -9.9525F, -18.0525F, 18.0F, 20.0F, 36.0F, new CubeDeformation(0.0F))
		.texOffs(272, 135).addBox(16.8519F, -14.0925F, -21.8308F, 9.0F, 28.0F, 44.0F, new CubeDeformation(0.0F))
		.texOffs(0, 285).addBox(14.8525F, -18.1325F, -17.8725F, 11.0F, 36.0F, 36.0F, new CubeDeformation(0.0F))
		.texOffs(174, 305).addBox(24.8125F, -14.0925F, -13.9125F, 5.0F, 28.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(376, 0).addBox(22.1125F, -9.9525F, -6.0525F, 12.0F, 20.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(94, 305).addBox(10.1125F, -17.9525F, -10.0525F, 20.0F, 36.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(300, 0).addBox(16.1125F, -21.9525F, -14.0525F, 10.0F, 44.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.8875F, -46.0475F, 6.0525F, 0.0F, -1.5708F, 0.0F));

		PartDefinition bone3 = eye_of_cthulhu.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(272, 207).addBox(-34.2275F, -6.0125F, 0.0475F, 68.0F, 12.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(138, 273).addBox(-29.8875F, -9.9525F, 5.9475F, 60.0F, 20.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(140, 87).addBox(-25.8875F, -13.9525F, 1.9475F, 52.0F, 28.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(0, 141).addBox(-26.1475F, -18.1325F, 3.1275F, 52.0F, 36.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(146, 0).addBox(-30.1875F, -14.0925F, 0.0875F, 60.0F, 28.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(284, 82).addBox(-33.8875F, -9.9525F, -0.0525F, 68.0F, 20.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 239).addBox(-29.1891F, -18.1319F, 0.0475F, 59.0F, 36.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(142, 193).addBox(-25.1491F, -22.1718F, 0.0875F, 51.0F, 44.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.8875F, -46.0475F, 6.0525F, 0.0F, 0.0F, 1.5708F));

		PartDefinition mouth_up = eye_of_cthulhu.addOrReplaceChild("mouth_up", CubeListBuilder.create().texOffs(378, 176).addBox(-57.08F, -33.88F, 11.92F, 9.0F, 5.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(94, 285).addBox(-13.08F, -33.88F, 11.92F, 8.0F, 5.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(378, 140).addBox(-61.78F, -29.7F, 13.82F, 13.0F, 6.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(390, 299).addBox(-13.845F, -29.73F, 14.875F, 13.0F, 6.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(272, 229).addBox(-65.08F, -21.88F, 22.92F, 7.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(272, 236).addBox(-5.08F, -21.88F, 22.92F, 7.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(294, 261).addBox(-61.82F, -25.72F, 18.855F, 8.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(324, 261).addBox(-8.78F, -25.7F, 18.82F, 8.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(154, 403).addBox(-57.34F, -30.06F, 6.1F, 13.0F, 4.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(0, 446).addBox(-18.34F, -30.06F, 6.1F, 13.0F, 4.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(37.08F, -34.12F, -19.92F));

		PartDefinition up = mouth_up.addOrReplaceChild("up", CubeListBuilder.create().texOffs(284, 108).addBox(-34.2275F, -6.0125F, 0.0475F, 13.0F, 12.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(376, 229).addBox(-29.2594F, -11.0043F, -1.8725F, 9.0F, 20.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(240, 355).addBox(-26.3043F, -14.2038F, -10.2525F, 5.0F, 29.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(36, 357).addBox(-26.1475F, -18.1325F, -6.8725F, 5.0F, 36.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(376, 32).addBox(-21.1475F, -18.1325F, -13.8725F, 5.0F, 36.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(116, 361).addBox(-30.1875F, -14.0925F, -3.9125F, 8.0F, 28.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(376, 401).addBox(-33.8875F, -9.9525F, 3.9475F, 13.0F, 20.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(240, 305).addBox(-29.4023F, -17.7013F, -0.0025F, 8.0F, 36.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(340, 355).addBox(-25.1491F, -22.1718F, -3.9125F, 4.0F, 44.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-31.1925F, -11.9275F, 15.9725F, 0.0F, 0.0F, 1.5708F));

		PartDefinition teeth = mouth_up.addOrReplaceChild("teeth", CubeListBuilder.create(), PartPose.offset(-61.0F, -16.5F, 23.0F));

		PartDefinition cube_r1 = teeth.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(320, 72).addBox(-1.0F, -2.5F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(100, 423).addBox(55.84F, -2.5F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r2 = teeth.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(332, 72).addBox(-1.0F, -2.5F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(218, 423).addBox(51.84F, -2.5F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -5.0F, -4.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r3 = teeth.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(128, 429).addBox(-0.48F, -2.5F, -2.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(330, 429).addBox(52.32F, -2.5F, -2.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, -6.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r4 = teeth.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(344, 72).addBox(-1.0F, -2.5F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(424, 176).addBox(43.84F, -2.5F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -8.0F, -9.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition cube_r5 = teeth.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(136, 429).addBox(-0.48F, -2.5F, -2.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(338, 429).addBox(44.32F, -2.5F, -2.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -5.0F, -10.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition cube_r6 = teeth.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(356, 72).addBox(-1.0F, -2.5F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(424, 185).addBox(33.84F, -2.5F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, -8.0F, -15.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r7 = teeth.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(144, 429).addBox(-0.48F, -2.5F, -2.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(346, 429).addBox(34.32F, -2.5F, -2.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, -4.0F, -16.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r8 = teeth.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(384, 261).addBox(-1.0F, -4.5F, -2.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(408, 195).addBox(19.84F, -4.5F, -2.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(18.0F, -8.0F, -17.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r9 = teeth.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(368, 72).addBox(-0.48F, -4.5F, -2.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(428, 149).addBox(20.32F, -4.5F, -2.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(18.0F, -5.0F, -17.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r10 = teeth.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(396, 261).addBox(-1.0F, -4.5F, -2.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(408, 261).addBox(5.84F, -4.5F, -2.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, -8.0F, -19.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r11 = teeth.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(332, 403).addBox(-0.48F, -4.5F, -2.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(428, 159).addBox(6.32F, -4.5F, -2.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, -4.0F, -19.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition mouth_up2 = eye_of_cthulhu.addOrReplaceChild("mouth_up2", CubeListBuilder.create().texOffs(390, 316).addBox(-57.08F, 28.88F, 11.92F, 9.0F, 5.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(288, 403).addBox(-13.08F, 28.88F, 11.92F, 8.0F, 5.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(378, 158).addBox(-61.845F, 23.67F, 13.88F, 13.0F, 6.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(390, 335).addBox(-13.845F, 23.67F, 14.875F, 13.0F, 6.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(272, 243).addBox(-65.08F, 17.88F, 22.92F, 7.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(300, 72).addBox(-5.08F, 17.88F, 22.92F, 7.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(354, 261).addBox(-61.78F, 21.7F, 18.82F, 8.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(378, 195).addBox(-8.78F, 21.7F, 18.82F, 8.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(406, 32).addBox(-57.34F, 26.06F, 6.1F, 13.0F, 4.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(406, 47).addBox(-18.34F, 26.06F, 6.1F, 13.0F, 4.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(37.08F, -57.88F, -19.92F));

		PartDefinition down = mouth_up2.addOrReplaceChild("down", CubeListBuilder.create().texOffs(330, 108).addBox(-34.2275F, -5.9875F, 0.0475F, 13.0F, 12.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(378, 108).addBox(-29.8875F, -10.0475F, -2.0525F, 9.0F, 20.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(290, 355).addBox(-25.6791F, -13.9532F, -9.9525F, 5.0F, 28.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(76, 361).addBox(-26.1475F, -17.8675F, -6.8725F, 5.0F, 36.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(376, 355).addBox(-21.1475F, -17.8675F, -13.8725F, 5.0F, 36.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(160, 361).addBox(-30.1875F, -13.9075F, -3.9125F, 8.0F, 28.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(116, 403).addBox(-33.8875F, -10.0475F, 3.9475F, 13.0F, 20.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(204, 361).addBox(-29.4023F, -17.9387F, -0.0025F, 8.0F, 36.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 357).addBox(-25.1491F, -21.8282F, -3.9125F, 4.0F, 44.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-31.1925F, 11.9275F, 15.9725F, 0.0F, 0.0F, -1.5708F));

		PartDefinition teeth2 = mouth_up2.addOrReplaceChild("teeth2", CubeListBuilder.create(), PartPose.offset(-61.0F, 16.5F, 23.0F));

		PartDefinition cube_r12 = teeth2.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(428, 140).addBox(-1.0F, -3.5F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(364, 424).addBox(55.84F, -3.5F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r13 = teeth2.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(88, 428).addBox(-1.0F, -3.5F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(376, 427).addBox(51.84F, -3.5F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 5.0F, -4.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r14 = teeth2.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(8, 431).addBox(-0.48F, -3.5F, -2.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(354, 429).addBox(52.32F, -3.5F, -2.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 1.0F, -6.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r15 = teeth2.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(76, 428).addBox(-1.0F, -3.5F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(388, 427).addBox(43.84F, -3.5F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 8.0F, -9.0F, 0.48F, 0.0F, 0.0F));

		PartDefinition cube_r16 = teeth2.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 431).addBox(-0.48F, -3.5F, -2.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(430, 272).addBox(44.32F, -3.5F, -2.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 5.0F, -10.0F, 0.48F, 0.0F, 0.0F));

		PartDefinition cube_r17 = teeth2.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(64, 428).addBox(-1.0F, -3.5F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(400, 427).addBox(33.84F, -3.5F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, 8.0F, -15.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r18 = teeth2.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(430, 288).addBox(-0.48F, -3.5F, -2.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(430, 280).addBox(34.32F, -3.5F, -2.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, 4.0F, -16.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r19 = teeth2.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(420, 261).addBox(-1.0F, -3.5F, -2.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(100, 412).addBox(19.84F, -3.5F, -2.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(18.0F, 8.0F, -17.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r20 = teeth2.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(120, 429).addBox(-0.48F, -3.5F, -2.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(428, 206).addBox(20.32F, -3.5F, -2.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(18.0F, 5.0F, -17.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r21 = teeth2.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(420, 195).addBox(-1.0F, -3.5F, -2.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(364, 413).addBox(5.84F, -3.5F, -2.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, 8.0F, -19.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r22 = teeth2.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(112, 429).addBox(-0.48F, -3.5F, -2.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(428, 216).addBox(6.32F, -3.5F, -2.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, 4.0F, -19.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition tail = eye_of_cthulhu.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bone4 = tail.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offset(-2.0F, -38.0F, 38.0F));

		PartDefinition cube_r23 = bone4.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(202, 407).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.0F, -12.0F, -4.0F, 0.2182F, 0.3491F, 0.0F));

		PartDefinition cube_r24 = bone4.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(406, 384).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, -4.0F, -0.1745F, -0.3927F, 0.0F));

		PartDefinition cube_r25 = bone4.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(406, 368).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -4.0F, 0.0F, -0.3491F, -0.3927F, 0.0F));

		PartDefinition cube_r26 = bone4.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(406, 352).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		PartDefinition cube_r27 = bone4.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(406, 62).addBox(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 6.0F, 10.0F, -0.0873F, -0.48F, 0.0F));

		PartDefinition bone5 = tail.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offsetAndRotation(18.0F, -62.0F, 30.0F, 0.6473F, -0.4878F, 0.6674F));

		PartDefinition cube_r28 = bone5.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(68, 412).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.48F, 0.0F));

		PartDefinition cube_r29 = bone5.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(36, 408).addBox(-2.0F, -0.8F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 4.0F, 8.0F, -0.0873F, 0.48F, 0.0F));

		PartDefinition bone6 = tail.addOrReplaceChild("bone6", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.6579F, -64.4511F, 42.5857F, 0.6473F, -0.4878F, 0.6674F));

		PartDefinition cube_r30 = bone6.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(414, 400).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1945F, -0.6368F, -10.7038F, -0.3927F, -0.48F, -0.9599F));

		PartDefinition cube_r31 = bone6.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(332, 413).addBox(-3.8524F, -1.0879F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6579F, 3.6511F, -2.7038F, -0.0436F, -0.48F, -0.9599F));

		PartDefinition bone7 = tail.addOrReplaceChild("bone7", CubeListBuilder.create(), PartPose.offsetAndRotation(18.0F, -38.0F, 38.0F, 0.0F, 0.0F, -1.309F));

		PartDefinition cube_r32 = bone7.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(414, 416).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		PartDefinition cube_r33 = bone7.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(0, 415).addBox(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 6.0F, 10.0F, -0.8636F, -0.6627F, 0.2237F));

		PartDefinition bone8 = tail.addOrReplaceChild("bone8", CubeListBuilder.create(), PartPose.offset(-10.0F, -50.0F, 30.0F));

		PartDefinition cube_r34 = bone8.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(418, 229).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		PartDefinition cube_r35 = bone8.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(154, 418).addBox(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 6.0F, 10.0F, -0.1603F, -0.2648F, -0.039F));

		PartDefinition bone9 = tail.addOrReplaceChild("bone9", CubeListBuilder.create(), PartPose.offsetAndRotation(26.0F, -50.0F, 30.0F, 0.0F, 0.0F, -1.7017F));

		PartDefinition cube_r36 = bone9.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(418, 245).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		PartDefinition cube_r37 = bone9.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(234, 418).addBox(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 6.0F, 10.0F, -0.3054F, -0.48F, 0.0F));

		PartDefinition bone10 = tail.addOrReplaceChild("bone10", CubeListBuilder.create(), PartPose.offsetAndRotation(6.0F, -66.0F, 30.0F, 0.3054F, 0.0F, 2.5744F));

		PartDefinition cube_r38 = bone10.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(420, 124).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		PartDefinition cube_r39 = bone10.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(420, 108).addBox(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 6.0F, 10.0F, -0.829F, -0.48F, 0.0F));

		PartDefinition bone11 = tail.addOrReplaceChild("bone11", CubeListBuilder.create(), PartPose.offsetAndRotation(10.0F, -30.0F, 34.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition cube_r40 = bone11.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(298, 422).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		PartDefinition cube_r41 = bone11.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(266, 422).addBox(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 6.0F, 10.0F, -0.0873F, -0.48F, 0.0F));

		PartDefinition bone12 = tail.addOrReplaceChild("bone12", CubeListBuilder.create(), PartPose.offsetAndRotation(10.0F, -50.0F, 34.0F, 0.6545F, 0.0F, 0.0F));

		PartDefinition cube_r42 = bone12.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(424, 0).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		PartDefinition cube_r43 = bone12.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(186, 423).addBox(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 6.0F, 10.0F, -0.0873F, -0.48F, 0.0F));

		PartDefinition bone13 = tail.addOrReplaceChild("bone13", CubeListBuilder.create(), PartPose.offsetAndRotation(14.0F, -46.0F, 34.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r44 = bone13.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(32, 424).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -0.48F, 0.0F));

		PartDefinition cube_r45 = bone13.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(424, 16).addBox(-1.847F, -2.4755F, 0.1168F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 6.0F, 10.0F, -0.2435F, -0.3109F, -0.0472F));

		return LayerDefinition.create(meshdefinition, 512, 512);
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