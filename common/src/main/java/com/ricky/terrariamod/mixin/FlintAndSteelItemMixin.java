package com.ricky.terrariamod.mixin;

import com.ricky.terrariamod.world.portal.CustomPortalShape;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FlintAndSteelItem.class)
public abstract class FlintAndSteelItemMixin {

    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    private void onUseOn(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());

        CustomPortalShape shape = new CustomPortalShape(level, pos, Direction.Axis.X);
        CustomPortalShape shape2 = new CustomPortalShape(level, pos, Direction.Axis.Z);
        if (shape.isValid()) {
            shape.createPortal();
            cir.setReturnValue(InteractionResult.SUCCESS);
        }else if (shape2.isValid()) {
            shape2.createPortal();
            cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }
}
