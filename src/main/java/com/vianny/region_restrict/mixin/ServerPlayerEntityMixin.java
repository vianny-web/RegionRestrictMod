package com.vianny.region_restrict.mixin;

import com.vianny.region_restrict.events.PlayerEnterZoneCallback;
import com.vianny.region_restrict.utils.zones.ZoneManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Unique
    private Vec3d lastPositionPlayer;

    @Inject(method = "tick", at = @At("HEAD"))
    private void onMove(CallbackInfo info) {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;

        if (lastPositionPlayer == null) {
            lastPositionPlayer = player.getPos();
        } else {
            Vec3d currentPosition = player.getPos();
            if (ZoneManager.isPlayerInRestrictedZone(player)) {
                Box playerBox = new Box(currentPosition, currentPosition.add(1, 0, 1));
                boolean allowed = PlayerEnterZoneCallback.EVENT.invoker().onPlayerEnterZone(player, playerBox);

                if (!allowed) {
                    player.teleport(lastPositionPlayer.x, lastPositionPlayer.y, lastPositionPlayer.z);
                } else {
                    lastPositionPlayer = currentPosition;
                }
            } else {
                lastPositionPlayer = currentPosition;
            }
        }
    }
}

