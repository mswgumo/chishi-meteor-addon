package dev.mswgumo.chishi.meteor.addon.modules;


import dev.mswgumo.chishi.meteor.addon.ChiShiAddon;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;

public class FPSKiller extends Module {
    public FPSKiller() {
        super(ChiShiAddon.CATEGORY, "FPS-killer", "Reduce game FPS");
    }

    @EventHandler
    public void onTick(TickEvent.Post event) {
        try {
            Thread.sleep(50);
        } catch (Exception e) {
            return;
        }

    }
}
