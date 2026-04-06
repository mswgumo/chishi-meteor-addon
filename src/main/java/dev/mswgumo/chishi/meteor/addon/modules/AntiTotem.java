package dev.mswgumo.chishi.meteor.addon.modules;


import dev.mswgumo.chishi.meteor.addon.ChiShiAddon;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.SlotActionType;

public class AntiTotem extends Module {
    public AntiTotem() {
        super(ChiShiAddon.CATEGORY, "AntiTotem", "AntiTotem");
    }
    @EventHandler
    public void onTick(TickEvent.Pre event) {
        // 防止空指针
        if (mc.player == null) return;
        // 判断一下副手是否是图腾
        ItemStack Offhand = mc.player.getOffHandStack();
        if (Offhand.getItem() == Items.TOTEM_OF_UNDYING) {
            mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId,
                45,
                1,
                SlotActionType.THROW,
                mc.player
                );
        }
    }
}
