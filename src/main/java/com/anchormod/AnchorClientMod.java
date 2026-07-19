package com.anchormod;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnchorClientMod implements ModInitializer {
    public static final String MOD_ID = "anchorclient";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Anchor Client Mod initialized!");
    }
}
