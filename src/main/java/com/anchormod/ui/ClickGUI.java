package com.anchormod.ui;

import com.anchormod.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.DrawContext;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ClickGUI extends Screen {
    public List<Window> windows = new ArrayList<>();
    private final MinecraftClient client;
    private final Map<String, List<Module>> categoryModules;

    public ClickGUI(List<Module> modules) {
        super(null);
        this.client = MinecraftClient.getInstance();
        this.categoryModules = new HashMap<>();

        // Organize modules by category
        for (Module module : modules) {
            String category = "Crystal"; // Default category
            categoryModules.computeIfAbsent(category, k -> new ArrayList<>()).add(module);
        }

        // Create windows for each category
        int xOffset = 10;
        for (Map.Entry<String, List<Module>> entry : categoryModules.entrySet()) {
            windows.add(new Window(xOffset, 20, entry.getKey(), entry.getValue()));
            xOffset += 170;
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Draw semi-transparent background
        context.fill(0, 0, this.width, this.height, 0x80000000);

        // Render all windows
        for (Window window : windows) {
            window.render(context, mouseX, mouseY, delta);
        }

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (Window window : windows) {
            window.mouseClicked((int) mouseX, (int) mouseY, button);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for (Window window : windows) {
            window.mouseReleased((int) mouseX, (int) mouseY, button);
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        // R-Shift to close GUI (GLFW key code for R-Shift is 344)
        if (keyCode == GLFW.GLFW_KEY_RIGHT_SHIFT) {
            this.close();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void close() {
        this.client.setScreen(null);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}
