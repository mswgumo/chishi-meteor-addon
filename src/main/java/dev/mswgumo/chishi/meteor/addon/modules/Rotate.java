package dev.mswgumo.chishi.meteor.addon.modules;

import dev.mswgumo.chishi.meteor.addon.ChiShiAddon;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;

public class Rotate extends Module {
    int yaw = 0;
    public Rotate() {
        super(ChiShiAddon.CATEGORY, "Rotate", "Super Rotate!!!");
    }
    @EventHandler
    public void onTick(TickEvent.Post event) {
        if (mc.player == null) return;

        mc.player.setYaw(this.yaw);
        this.yaw += 60;
    }
}
