package dev.mswgumo.chishi.meteor.addon.mixin;

import dev.mswgumo.chishi.meteor.addon.modules.NoJump;
import dev.mswgumo.chishi.meteor.addon.modules.NoSprint;
import meteordevelopment.meteorclient.systems.modules.Modules;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(LivingEntity.class)
public class NoSprintMixin {
    @Inject(method = "setSprinting", at = @At("HEAD"), cancellable = true)
    private void onSetSprinting(boolean sprinting, CallbackInfo ci) {
        if (Objects.requireNonNull(Modules.get().get(NoSprint.class)).isActive()) {
            ci.cancel();
        }

    }
}
