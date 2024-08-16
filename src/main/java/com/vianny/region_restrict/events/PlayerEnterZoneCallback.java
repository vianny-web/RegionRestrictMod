package com.vianny.region_restrict.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;

public interface PlayerEnterZoneCallback {
    Event<PlayerEnterZoneCallback> EVENT = EventFactory.createArrayBacked(PlayerEnterZoneCallback.class,
            (listeners) -> (player, box) -> {
                for (PlayerEnterZoneCallback listener : listeners) {
                    if (!listener.onPlayerEnterZone(player, box)) {
                        return false;
                    }
                }
                return true;
            });

    boolean onPlayerEnterZone(PlayerEntity player, Box box);
}

