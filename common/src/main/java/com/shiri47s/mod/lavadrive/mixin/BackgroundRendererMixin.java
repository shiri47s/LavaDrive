package com.shiri47s.mod.lavadrive.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.shiri47s.mod.lavadrive.RenderingContext;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {
    @Inject(method = "applyFog", at = @At("TAIL"))
    private static void applyFogInLava(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
        var submersionType = camera.getSubmersionType();
        if (submersionType == CameraSubmersionType.LAVA) {
            if (RenderingContext.LavaSetsWearer != null) {
                RenderSystem.setShaderFogStart(0.0F);
                RenderSystem.setShaderFogEnd(viewDistance);
            }
        }
    }
}
