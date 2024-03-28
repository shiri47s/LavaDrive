package com.shiri47s.mod.lavadrive.mixin;

import com.shiri47s.mod.lavadrive.LavaDrive;
import com.shiri47s.mod.lavadrive.RenderingContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "isInvulnerableTo", at = @At(value = "HEAD", target = "net/minecraft/entity/Entity.isInvulnerableTo(Lnet/minecraft/entity/damage/DamageSource;)Z"), cancellable = true)
    private void injectInvulnerability(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if (RenderingContext.LavaSetsWearer != null) {
            if (damageSource.isIn(LavaDrive.INVULNERABLE_DAMAGE_TAG)) {
                Entity entity = (Entity)(Object)this;
                if (entity != null) {
                    RenderingContext.LavaSetsWearer = entity;
                }

                cir.setReturnValue(true);
            }
        }
    }
}