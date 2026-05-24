/*
 * MIT License

 * Copyright (c) 2026 mswgumo

 * email: mswgumo@dev.mswgumo.com
 * GitHub: https://github.com/mswgumo
 */

package dev.mswgumo.chishi.meteor.addon.mixin;

import dev.mswgumo.chishi.meteor.addon.modules.Boom;
import meteordevelopment.meteorclient.systems.modules.Modules;
import net.minecraft.util.crash.CrashReport;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CrashReport.class)
public class CrashReportMixin {
    @Redirect(method = "asString*", at = @At(
        value = "INVOKE",
        target = "Lnet/minecraft/util/crash/CrashReport;getCauseAsString()Ljava/lang/String;"
    ))
    private String onGetCauseAsString(CrashReport instance) {
        Modules modules = Modules.get();
        if (modules == null) return instance.getCauseAsString();
        Boom boom = modules.get(Boom.class);
        if (boom == null || (boom.isActive() && boom.AntiErrorLog.get())) {
            return "";
        }

        return instance.getCauseAsString();
    }
}

