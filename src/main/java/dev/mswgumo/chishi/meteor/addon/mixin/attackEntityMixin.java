package dev.mswgumo.chishi.meteor.addon.mixin;

import dev.mswgumo.chishi.meteor.addon.modules.SelfDamage;
import dev.mswgumo.chishi.meteor.addon.modules.ShortRange;
import meteordevelopment.meteorclient.systems.modules.Modules;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.Entity;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;


@Mixin(ClientPlayerInteractionManager.class)
public class attackEntityMixin {
    @Inject(method = "attackEntity", at = @At("HEAD"), cancellable = true)
    private void onAttackEntity(PlayerEntity player, Entity target, CallbackInfo ci) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (Objects.requireNonNull(Modules.get().get(SelfDamage.class)).isActive() && mc.player != null) {
            // 检测到是3C3U，就执行3C3U的自杀指令
            if (mc.getCurrentServerEntry() != null && mc.getCurrentServerEntry().address.equalsIgnoreCase("3c3u.org")) {
                mc.player.networkHandler.sendChatCommand("suicide --confirm");
                return;
            }
            mc.player.networkHandler.sendChatCommand("kill");
        }
        if (Objects.requireNonNull(Modules.get().get(ShortRange.class)).isActive() && player.distanceTo(target) > 1) {
            ci.cancel();
        }

    }
}
