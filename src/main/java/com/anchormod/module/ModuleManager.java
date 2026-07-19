package com.anchormod.module;

import com.anchormod.module.modules.SafeAnchorModule;
import com.anchormod.module.modules.AutoCrystalModule;
import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public void registerModules() {
        registerModule(new SafeAnchorModule());
        registerModule(new AutoCrystalModule());
    }

    public void registerModule(Module module) {
        modules.add(module);
    }

    public List<Module> getModules() {
        return modules;
    }

    public Module getModule(String name) {
        return modules.stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
