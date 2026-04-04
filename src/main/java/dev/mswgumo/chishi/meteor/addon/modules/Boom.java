package dev.mswgumo.chishi.meteor.addon.modules;

import dev.mswgumo.chishi.meteor.addon.ChiShiAddon;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;

public class Boom extends Module {
    public Boom() {
        super(ChiShiAddon.CATEGORY, "Boom", "Force the client to crash instantly");
    }
    @EventHandler
    public void onTick(TickEvent.Pre event) {
        // 判断玩家是否进入世界，如果没有就关闭
        // 变相修复一键崩端后，再从加入世界，如果没有关闭Boom功能会再次崩溃的问题
        if (mc.player == null && mc.world != null) {
            this.toggle();
            return;
        };
        throw new RuntimeException("一键崩端");
    }
}
