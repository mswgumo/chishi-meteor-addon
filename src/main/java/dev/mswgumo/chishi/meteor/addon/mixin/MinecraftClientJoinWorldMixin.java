package dev.mswgumo.chishi.meteor.addon.mixin;


import dev.mswgumo.chishi.meteor.addon.modules.Boom;
import meteordevelopment.meteorclient.systems.modules.Modules;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientJoinWorldMixin {
    @Inject(method = "joinWorld", at = @At("HEAD"))
    public void onJoinWorld(ClientWorld world, CallbackInfo ci) {
        // 检测Boom是否开启，如果开启就关闭
        // 如果游戏一直崩溃那就不好玩了
        Boom boom = Modules.get().get(Boom.class);
        if (boom != null && boom.isActive()) {
            boom.toggle();
        }
    }

}
