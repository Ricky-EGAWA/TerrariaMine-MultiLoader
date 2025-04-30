package com.ricky.terrariamod.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.ricky.terrariamod.Constants;
import com.ricky.terrariamod.util.IEntityDataSaver;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class ManaHudOverlay {
    //TODO エフェクトのアイコンと被るので位置調整

    private static final ResourceLocation MANA = new ResourceLocation(Constants.MOD_ID, "textures/misc/mana.png");
    private static final ResourceLocation NO_MANA = new ResourceLocation(Constants.MOD_ID, "textures/misc/no_mana.png");

    public static void render(GuiGraphics guiGraphics) {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null) return;

        int x = client.getWindow().getGuiScaledWidth();

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        // 背景（空のマナ）を描画
        for (int i = 0; i < 10; i++) {
            guiGraphics.blit(NO_MANA, x - 20, 90 - (i * 9), 0, 0, 12, 12, 12, 12);
        }

        int currentMana = ((IEntityDataSaver) client.player).getPersistentData().getInt("mana");

        // 現在のマナを描画（下から順に）
        for (int i = 0; i < 10; i++) {
            int manaForThisIcon = Math.min(currentMana - (i * 20), 20);
            if (manaForThisIcon > 0) {
                int iconHeight = (int) (12 * (manaForThisIcon / 20.0));
                int v = 12 - iconHeight;
                guiGraphics.blit(MANA, x - 20, 90 - (i * 9) + v, 0, v, 12, iconHeight, 12, 12);
            }
        }

        RenderSystem.disableBlend();
    }
}
