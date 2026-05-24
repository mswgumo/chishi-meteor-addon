package dev.mswgumo.chishi.meteor.addon.modules;

import dev.mswgumo.chishi.meteor.addon.ChiShiAddon;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.settings.BoolSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;

public class Boom extends Module {
    public Setting<Boolean> AntiErrorLog = settings.getDefaultGroup().add(
        new BoolSetting.Builder()
            .name("Anti-Error-Log")
            .defaultValue(true)
            .build()
    );

    public Boom() {
        super(ChiShiAddon.CATEGORY, "Boom", "Force the client to crash instantly");
    }
    @EventHandler
    public void onTick(TickEvent.Pre event) {
        throw new RuntimeException("Boom!!!");
    }
}
