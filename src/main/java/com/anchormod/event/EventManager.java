package com.anchormod.event;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private final List<TickListener> tickListeners = new ArrayList<>();
    private final List<PlaceBlockListener> placeBlockListeners = new ArrayList<>();

    public void registerTickListener(TickListener listener) {
        tickListeners.add(listener);
    }

    public void registerPlaceBlockListener(PlaceBlockListener listener) {
        placeBlockListeners.add(listener);
    }

    public void fireTickEvent() {
        for (TickListener listener : tickListeners) {
            listener.onTick();
        }
    }

    public void firePlaceBlockEvent(int x, int y, int z) {
        for (PlaceBlockListener listener : placeBlockListeners) {
            listener.onPlaceBlock(x, y, z);
        }
    }

    public interface TickListener {
        void onTick();
    }

    public interface PlaceBlockListener {
        void onPlaceBlock(int x, int y, int z);
    }
}
