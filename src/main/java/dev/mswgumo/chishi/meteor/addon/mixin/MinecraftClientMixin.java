/*
 * MIT License

 * Copyright (c) 2026 mswgumo

 * email: mswgumo@dev.mswgumo.com
 * GitHub: https://github.com/mswgumo
 */

package dev.mswgumo.chishi.meteor.addon.mixin;

import com.mojang.logging.LogUtils;
import dev.mswgumo.chishi.meteor.addon.modules.Boom;
import meteordevelopment.meteorclient.systems.modules.Modules;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.crash.CrashReport;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Objects;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Redirect(method = "run", at = @At(
        value = "INVOKE",
        target = "Lorg/slf4j/Logger;error(Lorg/slf4j/Marker;Ljava/lang/String;Ljava/lang/Throwable;)V"
    ))
    private void onError(Logger LOGGER, Marker marker, String string, Throwable throwable) {
        Boom boom = Modules.get().get(Boom.class);
        if (boom == null) return;
        if (boom.isActive() && boom.AntiErrorLog.get()) {
            return;
        }
        LOGGER.error(marker, string, throwable);
    }

    @Redirect(method = "run", at = @At(
        value = "INVOKE",
        target = "Lnet/minecraft/client/MinecraftClient;printCrashReport(Lnet/minecraft/util/crash/CrashReport;)V"
    ))
    public void onCrashReport(MinecraftClient instance, CrashReport crashReport) {
        Boom boom = Modules.get().get(Boom.class);
        if (boom == null) return;
        if (boom.isActive() && boom.AntiErrorLog.get()) {
            return;
        }
        instance.printCrashReport(crashReport);
    }
}
