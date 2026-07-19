package com.anchormod;

import com.anchormod.event.EventManager;
import com.anchormod.keybind.KeybindManager;
import com.anchormod.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class AnchorClientModClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("anchorclient-client");
    public static EventManager EVENT_MANAGER = new EventManager();
    public static ModuleManager MODULE_MANAGER = new ModuleManager();

    @Override
    public void onInitializeClient() {
        LOGGER.info("Anchor Client initialized on client side!");
        MODULE_MANAGER.registerModules();
        KeybindManager.init();
        LOGGER.info("Keybind manager initialized - Press R-Shift to open GUI");
    }
}
