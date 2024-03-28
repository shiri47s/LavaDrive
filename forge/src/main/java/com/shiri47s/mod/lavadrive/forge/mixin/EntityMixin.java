package com.shiri47s.mod.lavadrive.forge.mixin;

import com.shiri47s.mod.lavadrive.RenderingContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fluids.FluidType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Redirect(method = "updateMovementInFluid(Lnet/minecraft/registry/tag/TagKey;D)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;isInFluidType(Lnet/minecraftforge/fluids/FluidType;)Z"))
    private boolean fluidStateIsIn(Entity instance, FluidType fluidType) {
        if (instance.isInFluidType(fluidType))
        {
            Entity entity = (Entity)(Object)this;
            if (RenderingContext.LavaSetsWearer != null) {
                if (entity instanceof PlayerEntity player) {
                    Vec3d vec3d = player.getVelocity();
                    player.setVelocity(vec3d.multiply(1.05f, 1.02f, 1.05f));
                    return true;
                }
            }
        }

        return false;
    }
}