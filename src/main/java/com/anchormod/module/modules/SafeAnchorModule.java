package com.anchormod.module.modules;

import com.anchormod.AnchorClientModClient;
import com.anchormod.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SafeAnchorModule extends Module {
    private static final int ANCHOR_SEARCH_RADIUS = 4;
    private static final int GLOWSTONE_SEARCH_RADIUS = 3;
    private MinecraftClient client;

    public SafeAnchorModule() {
        super("Safe Anchor", "Automatically detonates anchors with glowstone");
        this.client = MinecraftClient.getInstance();
    }

    @Override
    public void onEnable() {
        AnchorClientModClient.LOGGER.info("Safe Anchor enabled");
    }

    @Override
    public void onDisable() {
        AnchorClientModClient.LOGGER.info("Safe Anchor disabled");
    }

    @Override
    public void onTick() {
        if (!isEnabled() || client.player == null || client.world == null) {
            return;
        }

        PlayerEntity player = client.player;
        World world = client.world;
        BlockPos playerPos = player.getBlockPos();

        // Search for respawn anchors nearby
        for (int x = -ANCHOR_SEARCH_RADIUS; x <= ANCHOR_SEARCH_RADIUS; x++) {
            for (int y = -ANCHOR_SEARCH_RADIUS; y <= ANCHOR_SEARCH_RADIUS; y++) {
                for (int z = -ANCHOR_SEARCH_RADIUS; z <= ANCHOR_SEARCH_RADIUS; z++) {
                    BlockPos checkPos = playerPos.add(x, y, z);
                    
                    // Check if it's a respawn anchor
                    if (isRespawnAnchor(world, checkPos)) {
                        // Look for glowstone nearby
                        if (hasGlowstoneNearby(world, checkPos)) {
                            // Place glowstone on the anchor and detonate
                            detonateAnchor(player, checkPos);
                        }
                    }
                }
            }
        }
    }

    private boolean isRespawnAnchor(World world, BlockPos pos) {
        return world.getBlockState(pos).getBlock().getName().getString().contains("respawn_anchor");
    }

    private boolean hasGlowstoneNearby(World world, BlockPos anchorPos) {
        for (int x = -GLOWSTONE_SEARCH_RADIUS; x <= GLOWSTONE_SEARCH_RADIUS; x++) {
            for (int y = -GLOWSTONE_SEARCH_RADIUS; y <= GLOWSTONE_SEARCH_RADIUS; y++) {
                for (int z = -GLOWSTONE_SEARCH_RADIUS; z <= GLOWSTONE_SEARCH_RADIUS; z++) {
                    BlockPos checkPos = anchorPos.add(x, y, z);
                    if (world.getBlockState(checkPos).getBlock().getName().getString().contains("glowstone")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void detonateAnchor(PlayerEntity player, BlockPos anchorPos) {
        // This would be handled by the event system
        // For now, we log the detected anchor
        AnchorClientModClient.LOGGER.info("Anchor detected at: " + anchorPos);
    }
}
