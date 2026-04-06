package dev.mswgumo.chishi.meteor.addon.modules;


import dev.mswgumo.chishi.meteor.addon.ChiShiAddon;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;

public class AntiTotem extends Module {
    public AntiTotem() {
        super(ChiShiAddon.CATEGORY, "AntiTotem", "AntiTotem");
    }
    @EventHandler
    public void onTick(TickEvent.Pre event) {
        return;
    }
}
