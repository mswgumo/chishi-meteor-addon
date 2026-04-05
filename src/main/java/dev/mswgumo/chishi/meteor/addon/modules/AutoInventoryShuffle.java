package dev.mswgumo.chishi.meteor.addon.modules;

import dev.mswgumo.chishi.meteor.addon.ChiShiAddon;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.screen.slot.SlotActionType;
import java.util.Random;

public class AutoInventoryShuffle extends Module {
    private final Random random = new Random();
    public AutoInventoryShuffle() {
        super(ChiShiAddon.CATEGORY, "Auto Inventory Shuffle", "AutoInventoryShuffle");
    }

    @EventHandler
    public void onTick(TickEvent.Pre event) {


        if (mc.getNetworkHandler() == null || mc.player == null || mc.interactionManager == null) return;
        mc.interactionManager.clickSlot(
            mc.player.currentScreenHandler.syncId,
            this.random.nextInt(35 - 9 + 1) + 9,
            this.random.nextInt(8),
            SlotActionType.SWAP,
            mc.player
        );

    }

}
