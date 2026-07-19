package com.anchormod.ui;

import com.anchormod.module.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Window {
    public List<ModuleButton> moduleButtons = new ArrayList<>();
    public int x, y;
    private final int width = 150;
    private final int height = 25;
    private String category;
    public boolean dragging = false;
    private int dragX, dragY;
    private int prevX, prevY;
    private Color currentColor;

    public Window(int x, int y, String category, List<Module> modules) {
        this.x = x;
        this.y = y;
        this.category = category;
        this.prevX = x;
        this.prevY = y;
        this.currentColor = new Color(0, 0, 0, 0);

        int offset = height;
        for (Module module : modules) {
            moduleButtons.add(new ModuleButton(this, module, offset));
            offset += height;
        }
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Smooth color transition
        int targetAlpha = 150;
        int currentAlpha = currentColor.getAlpha();
        if (currentAlpha != targetAlpha) {
            currentAlpha = MathHelper.lerp(0.1f, currentAlpha, targetAlpha);
            currentColor = new Color(20, 20, 30, currentAlpha);
        }

        // Draw window background
        fill(context, prevX, prevY, prevX + width, prevY + height * (moduleButtons.size() + 1), currentColor.getRGB());
        
        // Draw header border
        fill(context, prevX, prevY + height - 2, prevX + width, prevY + height, 0xFF9D4EDD);

        // Draw category title
        context.drawTextWithShadow(
                context.getContext().getTextRenderer(),
                category,
                prevX + 8,
                prevY + 8,
                0xFFFFFF
        );

        // Update and render buttons
        int offset = height;
        for (ModuleButton button : moduleButtons) {
            button.render(context, mouseX, mouseY, delta, prevX, prevY + offset);
            offset += height;
        }

        // Handle dragging
        if (dragging) {
            prevX += mouseX - dragX;
            prevY += mouseY - dragY;
            dragX = mouseX;
            dragY = mouseY;
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (isMouseInHeader(mouseX, mouseY)) {
            if (button == 0) {
                dragging = true;
                dragX = mouseX;
                dragY = mouseY;
            }
        }

        for (ModuleButton moduleButton : moduleButtons) {
            moduleButton.mouseClicked(mouseX, mouseY, button, prevX, prevY + 25);
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int button) {
        if (button == 0) {
            dragging = false;
        }
    }

    private boolean isMouseInHeader(int mouseX, int mouseY) {
        return mouseX >= prevX && mouseX <= prevX + width &&
               mouseY >= prevY && mouseY <= prevY + height;
    }

    private void fill(DrawContext context, int x1, int y1, int x2, int y2, int color) {
        context.fill(x1, y1, x2, y2, color);
    }
}
