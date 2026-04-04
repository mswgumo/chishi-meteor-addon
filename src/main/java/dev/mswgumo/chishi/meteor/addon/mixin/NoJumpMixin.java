package dev.mswgumo.chishi.meteor.addon.mixin;

import dev.mswgumo.chishi.meteor.addon.modules.NoJump;
import meteordevelopment.meteorclient.systems.modules.Modules;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(LivingEntity.class)
public class NoJumpMixin {
    @Inject(method = "jump", at = @At("HEAD"),  cancellable = true)
    private void onJump(CallbackInfo ci) {
        if (Objects.requireNonNull(Modules.get().get(NoJump.class)).isActive()) {
            ci.cancel();
        }
    }
}
