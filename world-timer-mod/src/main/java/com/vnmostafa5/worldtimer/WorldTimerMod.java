package com.vnmostafa5.worldtimer;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorldTimerMod implements ModInitializer {
    public static final String MOD_ID = "worldtimer";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("World Timer mod initializing...");

        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            TimeTracker.getInstance().start(server);
            LOGGER.info("World Timer started for world: {}",
                    server.getWorldData().getLevelName());
        });

        ServerLifecycleEvents.SERVER_STOPPING.register(server -> {
            TimeTracker.getInstance().stop();
            LOGGER.info("World Timer stopped. Time saved.");
        });

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            TimeTracker.getInstance().tick();
        });

        LOGGER.info("World Timer mod initialized successfully!");
    }
}
