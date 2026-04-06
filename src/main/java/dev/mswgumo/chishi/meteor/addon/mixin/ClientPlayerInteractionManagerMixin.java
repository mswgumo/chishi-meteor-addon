package dev.mswgumo.chishi.meteor.addon.mixin;

import dev.mswgumo.chishi.meteor.addon.modules.NoMine;
import meteordevelopment.meteorclient.systems.modules.Modules;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;


@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
    // 调用后直接返回注入的目标函数
    @Unique
    private void Intercept(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
        cir.cancel();
    }
    // 注入开始挖掘的函数
    @Inject(method = "attackBlock", at = @At("HEAD"), cancellable = true)
    private void onAttackBlock(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        if (Objects.requireNonNull(Modules.get().get(NoMine.class)).isActive()) {
            this.Intercept(cir);
        }

    }
    // 注入创造模式直接破坏方块的函数
    @Inject(method = "breakBlock", at = @At("HEAD"), cancellable = true)
    private void onBreakBlock(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (Objects.requireNonNull(Modules.get().get(NoMine.class)).isActive()) {
            this.Intercept(cir);
        }
    }
    // 注入挖掘进度更新函数
    @Inject(method = "updateBlockBreakingProgress", at = @At("HEAD"), cancellable = true)
    private void onUpdateBlockBreakingProgress(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        if (Objects.requireNonNull(Modules.get().get(NoMine.class)).isActive()) {
            this.Intercept(cir);
        }
    }
}
