package com.anchormod.keybind;

import com.anchormod.AnchorClientModClient;
import com.anchormod.ui.ClickGUI;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeybindManager {
    private static KeyBinding guiKeyBinding;
    private static boolean guiScreenOpen = false;

    public static void init() {
        // Create R-Shift keybinding
        guiKeyBinding = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.anchorclient.gui",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_RIGHT_SHIFT,
                        "category.anchorclient"
                )
        );

        // Register tick event for keybind checking
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (guiKeyBinding.wasPressed()) {
                toggleGUI();
            }
        });
    }

    private static void toggleGUI() {
        MinecraftClient client = MinecraftClient.getInstance();
        
        if (guiScreenOpen) {
            client.setScreen(null);
            guiScreenOpen = false;
        } else {
            client.setScreen(new ClickGUI(AnchorClientModClient.MODULE_MANAGER.getModules()));
            guiScreenOpen = true;
        }
    }

    public static boolean isGUIOpen() {
        return guiScreenOpen;
    }
}
