package dev.mswgumo.chishi.meteor.addon.modules;


import dev.mswgumo.chishi.meteor.addon.ChiShiAddon;
import meteordevelopment.meteorclient.systems.modules.Module;

public class KickOrBan extends Module {

    public KickOrBan() {
        super(ChiShiAddon.CATEGORY, "kick or ban", "Either get kick or ban");
    }
    @Override
    public void onActivate() {
        if (mc.player == null) this.toggle();
        mc.player.networkHandler.sendChatMessage(String.valueOf((char)0));
        this.toggle();
    }
}
