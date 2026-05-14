package dev.mswgumo.chishi.meteor.addon.modules;


import dev.mswgumo.chishi.meteor.addon.ChiShiAddon;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// 胡言乱语
public class TalkNonsense extends Module {
    public enum DelayModeEnum {
        RangeRand,
        TextScaleRand
    }

    List<String> nonsenseTextList;

    public final Setting<DelayModeEnum> DelayMode = settings.getDefaultGroup().add(
        new EnumSetting.Builder<DelayModeEnum>()
            .name("DelayMode")
            .defaultValue(DelayModeEnum.TextScaleRand)
            .build()
    );
    public final Setting<Integer> CharacterMultiplier = settings.getDefaultGroup().add(
        new IntSetting.Builder()
            .name("Character-Multiplier")
            .min(0)
            .sliderRange(0, 1000)
            .defaultValue(20)
            .visible(() -> DelayMode.get().equals(DelayModeEnum.TextScaleRand))
            .build()
    );


    public final Setting<Integer> Delay = settings.getDefaultGroup().add(
        new IntSetting.Builder()
        .name("Delay")
        .min(0)
        .sliderRange(0, 1000)
        .defaultValue(20)
        .visible(() -> DelayMode.get().equals(DelayModeEnum.RangeRand))
        .build()
    );
    public final Setting<Integer> Jitter = settings.getDefaultGroup().add(
        new IntSetting.Builder()
            .name("jitter")
            .min(0)
            .sliderRange(0, 1000)
            .visible(() -> DelayMode.get().equals(DelayModeEnum.RangeRand))
            .build()
    );
    Random random = new Random();
    int delayRemaining = 0;
    public TalkNonsense() {
        super(ChiShiAddon.CATEGORY, "talk-nonsense", "talk nonsense");
    }
    public int getDelay() {
        if (nonsenseTextList.isEmpty()) return 0;
        if (DelayMode.get().equals(DelayModeEnum.TextScaleRand)) {
            String text = nonsenseTextList.getFirst();
            return CharacterMultiplier.get() * text.codePointCount(0, text.length());
        } else {
            int jitter = random.nextInt(Jitter.get() * 2 + 1) - Jitter.get();
            return Delay.get() + jitter;
        }

    }

    public boolean send() {
        if (mc.player == null) return false;;
        String msg = nonsenseTextList.getFirst();
        // 去除非法字符
        msg = msg.replaceAll("[\\p{Cntrl}&&[^\n\t]]", "");
        // 防止有空格
        if (msg.isBlank()) {
            return false;
        }
        mc.player.networkHandler.sendChatMessage(msg);
        return true;
    }

    @Override
    public void onActivate() {
        try {
            // 打开文件
            InputStream nonsenseTextStream = ChiShiAddon.class.getResourceAsStream(
                "/assets/chishi-addon/TalkNonsense.txt"
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
            nonsenseTextList = new ArrayList<>(
                List.of(nonsenseText.split("[，,。.\\n]"))
            );
        } catch (Exception e) {
            info("无法读取文件: " + e);
            this.toggle();
        }
        delayRemaining = 0;
        return;
    }
    @EventHandler
    public void onTick(TickEvent.Pre event) {
        if (mc.player == null) return;
        if (nonsenseTextList.isEmpty()) {
            this.toggle();
            return;
        };
        if (delayRemaining <= 0) {
            if (!send()) {
                return;
            }
            nonsenseTextList.removeFirst();
            delayRemaining = getDelay();
        } else {
            delayRemaining--;
        }
    }
}
