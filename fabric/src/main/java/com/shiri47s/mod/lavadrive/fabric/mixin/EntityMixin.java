package com.shiri47s.mod.lavadrive.fabric.mixin;

import com.shiri47s.mod.lavadrive.RenderingContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Redirect(method = "updateMovementInFluid(Lnet/minecraft/registry/tag/TagKey;D)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/fluid/FluidState;getVelocity(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/math/Vec3d;"))
    private Vec3d fluidStateIsIn(FluidState instance, BlockView world, BlockPos pos) {
        if (instance.isIn(FluidTags.LAVA))
        {
            Entity entity = (Entity)(Object)this;
            if (RenderingContext.LavaSetsWearer != null) {
                if (entity instanceof PlayerEntity player) {
                    Vec3d vec3d = player.getVelocity();
                    player.setVelocity(vec3d.multiply(1.05f, 1.02f, 1.05f));
                }
            }
        }

        return instance.getVelocity(world, pos);
    }
}