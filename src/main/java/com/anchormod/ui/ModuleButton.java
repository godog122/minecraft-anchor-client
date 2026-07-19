package com.anchormod.ui;

import com.anchormod.module.Module;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class ModuleButton {
    private final Module module;
    private final int indexY;
    private Window parent;
    private boolean hovered = false;

    public ModuleButton(Window parent, Module module, int indexY) {
        this.parent = parent;
        this.module = module;
        this.indexY = indexY;
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta, int windowX, int windowY) {
        int x = windowX;
        int y = windowY + indexY - 25;
        int width = 150;
        int height = 25;

        // Check if hovered
        hovered = mouseX >= x && mouseX <= x + width &&
                  mouseY >= y && mouseY <= y + height;

        // Draw button background
        int backgroundColor = hovered ? 0xFF2A2A3A : 0xFF1F1F2E;
        context.fill(x, y, x + width, y + height, backgroundColor);

        // Draw enabled/disabled indicator
        int indicatorColor = module.isEnabled() ? 0xFF00FF00 : 0xFFFF0000;
        context.fill(x + 2, y + 2, x + 6, y + height - 2, indicatorColor);

        // Draw module name
        String displayName = module.getName();
        if (module.isEnabled()) {
            displayName = "✓ " + displayName;
        }

        context.drawTextWithShadow(
                context.getContext().getTextRenderer(),
                displayName,
                x + 12,
                y + 8,
                0xFFFFFF
        );
    }

    public void mouseClicked(int mouseX, int mouseY, int button, int windowX, int windowY) {
        int x = windowX;
        int y = windowY + indexY - 25;
        int width = 150;
        int height = 25;

        if (mouseX >= x && mouseX <= x + width &&
            mouseY >= y && mouseY <= y + height) {
            if (button == 0) { // Left click
                module.toggle();
            }
        }
    }
}
