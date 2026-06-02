package com.vnmostafa5.worldtimer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.server.MinecraftServer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TimeStorage {
    private static final String FILE_NAME = "worldtimer_data.json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static long loadTime(MinecraftServer server) {
        Path filePath = getStoragePath(server);
        if (!Files.exists(filePath)) return 0;

        try {
            String json = Files.readString(filePath);
            JsonObject obj = GSON.fromJson(json, JsonObject.class);
            return obj.has("totalTicks") ? obj.get("totalTicks").getAsLong() : 0;
        } catch (IOException e) {
            WorldTimerMod.LOGGER.error("Failed to load time data: {}", e.getMessage());
            return 0;
        }
    }

    public static void saveTime(MinecraftServer server, long totalTicks) {
        Path filePath = getStoragePath(server);

        JsonObject obj = new JsonObject();
        obj.addProperty("totalTicks", totalTicks);
        obj.addProperty("worldName", server.getWorldData().getLevelName());
        obj.addProperty("lastSaved", System.currentTimeMillis());

        try {
            Files.createDirectories(filePath.getParent());
            Files.writeString(filePath, GSON.toJson(obj));
            WorldTimerMod.LOGGER.info("Time saved: {} ticks to {}", totalTicks, filePath);
        } catch (IOException e) {
            WorldTimerMod.LOGGER.error("Failed to save time data: {}", e.getMessage());
        }
    }

    private static Path getStoragePath(MinecraftServer server) {
        String worldDir = server.getWorldData().getLevelName();
        return server.getServerDirectory().resolve("worldtimer")
                .resolve(worldDir + "_" + FILE_NAME);
    }
}
