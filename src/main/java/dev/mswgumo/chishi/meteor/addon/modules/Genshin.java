package dev.mswgumo.chishi.meteor.addon.modules;

import dev.mswgumo.chishi.meteor.addon.ChiShiAddon;
import meteordevelopment.meteorclient.systems.modules.Module;
import net.minecraft.util.Util;



public class Genshin extends Module {

    public Genshin() {
        super(ChiShiAddon.CATEGORY, "Genshin", "Genshin Start!");
    }

    @Override
    public void onActivate() {
        Util.getOperatingSystem().open("https://ys.mihoyo.com/cloud/#/");
        info("原神, 启动!!!");
        this.toggle();
    }
}
