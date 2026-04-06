package dev.mswgumo.chishi.meteor.addon.modules;

import dev.mswgumo.chishi.meteor.addon.ChiShiAddon;
import meteordevelopment.meteorclient.systems.modules.Module;
public class SelfDamage extends Module {
    public SelfDamage() {
        super(ChiShiAddon.CATEGORY, "self-damage", "Attacking results in self-destruction");
    }

}
