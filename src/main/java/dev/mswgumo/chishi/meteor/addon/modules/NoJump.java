package dev.mswgumo.chishi.meteor.addon.modules;

import dev.mswgumo.chishi.meteor.addon.ChiShiAddon;
import meteordevelopment.meteorclient.systems.modules.Module;

public class NoJump extends Module {
    public NoJump() {
        super(ChiShiAddon.CATEGORY, "no-jump", "can’t get off the ground");
    }
}
