package com.anchormod.module;

public abstract class Module {
    private String name;
    private String description;
    private boolean enabled = false;
    private int keybind = -1;

    public Module(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract void onEnable();

    public abstract void onDisable();

    public abstract void onTick();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        if (enabled && !this.enabled) {
            onEnable();
        } else if (!enabled && this.enabled) {
            onDisable();
        }
        this.enabled = enabled;
    }

    public void toggle() {
        setEnabled(!enabled);
    }

    public void setKeybind(int key) {
        this.keybind = key;
    }

    public int getKeybind() {
        return keybind;
    }
}
