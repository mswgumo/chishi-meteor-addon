package dev.mswgumo.chishi.meteor.addon;

import com.mojang.logging.LogUtils;
import dev.mswgumo.chishi.meteor.addon.modules.*;
import meteordevelopment.meteorclient.addons.GithubRepo;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.hud.HudGroup;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;

public class ChiShiAddon extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final Category CATEGORY = new Category("chishi");
    public static final HudGroup HUD_GROUP = new HudGroup("chishi");

    @Override
    public void onInitialize() {
        LOG.info("Initializing ChiShi Meteor Addon");

        // Modules
//        Modules.get().add(new ModuleExample());
        Modules.get().add(new NoJump());
        Modules.get().add(new NoSprint());
        Modules.get().add(new Boom());
        Modules.get().add(new ShortRange());
        Modules.get().add(new SelfDamage());
        Modules.get().add(new InventoryShuffle());
        Modules.get().add(new AntiAura());
        Modules.get().add(new AntiTotem());
        Modules.get().add(new NoMine());
        Modules.get().add(new TalkNonsense());
        Modules.get().add(new FPSKiller());
        // Commands
//        Commands.add(new CommandExample());

        // HUD
//        Hud.get().register(HudExample.INFO);

    }
    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(CATEGORY);
    }

    @Override
    public String getPackage() {
        return "dev.mswgumo.chishi.meteor.addon";
    }

    @Override
    public GithubRepo getRepo() {
        return new GithubRepo("mswgumo", "chishi-meteor-addon");
    }
}
