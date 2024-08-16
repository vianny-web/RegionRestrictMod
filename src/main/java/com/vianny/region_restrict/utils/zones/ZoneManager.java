package com.vianny.region_restrict.utils.zones;

import net.minecraft.entity.player.PlayerEntity;

import java.util.HashSet;
import java.util.Set;

public class ZoneManager {
    private static final Set<Zone> zones = new HashSet<>();

    public static void addZone(Zone zone) {
        zones.add(zone);
    }

    public static boolean isPlayerInRestrictedZone(PlayerEntity player) {
        for (Zone zone : zones) {
            if (zone.box().contains(player.getPos())) {
                return true;
            }
        }
        return false;
    }
}
