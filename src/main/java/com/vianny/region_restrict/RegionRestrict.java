package com.vianny.region_restrict;

import com.vianny.region_restrict.events.PlayerEnterZoneCallback;
import com.vianny.region_restrict.utils.zones.Zone;
import com.vianny.region_restrict.utils.zones.ZoneManager;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.math.Box;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegionRestrict implements ModInitializer {
	public static final String MOD_ID = "regionrestrictmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Start");

		Zone restrictedZone = new Zone("Test Zone", new Box(-18, -64, -2, -13, 1000, -7));
		ZoneManager.addZone(restrictedZone);

		PlayerEnterZoneCallback.EVENT.register((player, zone) -> false);
	}
}