package dev.mswgumo.chishi.meteor.addon.modules;


import dev.mswgumo.chishi.meteor.addon.ChiShiAddon;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.text.Text;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

// 胡言乱语
public class TalkNonsense extends Module {
    List<String> nonsenseTextList;
    public TalkNonsense() {
        super(ChiShiAddon.CATEGORY, "talk-nonsense", "talk nonsense");
    }
    @Override
    public void onActivate() {
        try {
            // 打开文件
            InputStream nonsenseTextStream = ChiShiAddon.class.getResourceAsStream(
                "/assets/chishi/TalkNonsense.txt"
            );
            if (nonsenseTextStream == null) {
                throw new IOException("File not found");
            }
            // 读取文件
            String nonsenseText =  new String(
                nonsenseTextStream.readAllBytes(),
                StandardCharsets.UTF_8
            );
            // 用逗号和句号和换行来分割
//            nonsenseTextList = List.of(nonsenseText.split("[，,。.\\n]"));
            nonsenseTextList = new ArrayList<>(
                List.of(nonsenseText.split("[，,。.\\n]"))
            );
        } catch (Exception e) {
            info("无法读取文件: " + e);
            this.toggle();
        }

        return;
    }
    @EventHandler
    public void onTick(TickEvent.Pre event) {
        if (mc.player == null) return;
        if (nonsenseTextList.isEmpty()) {
            this.toggle();
            return;
        };

        String msg = nonsenseTextList.getFirst();
        // 去除非法字符
        msg = msg.replaceAll("[\\p{Cntrl}&&[^\n\t]]", "");
        // 防止有空格
        if (!msg.isBlank()) {
            mc.player.networkHandler.sendChatMessage(msg);
        }
        nonsenseTextList.removeFirst();
    }
}
