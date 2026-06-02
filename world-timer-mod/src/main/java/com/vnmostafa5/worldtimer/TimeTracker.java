package com.vnmostafa5.worldtimer;

import net.minecraft.server.MinecraftServer;
import java.util.concurrent.TimeUnit;

public class TimeTracker {
    private static final TimeTracker INSTANCE = new TimeTracker();

    private long sessionStartTick;
    private long sessionElapsedTicks;
    private long savedTotalTicks;
    private boolean running;
    private MinecraftServer server;

    private TimeTracker() {
        this.sessionStartTick = 0;
        this.sessionElapsedTicks = 0;
        this.savedTotalTicks = 0;
        this.running = false;
    }

    public static TimeTracker getInstance() {
        return INSTANCE;
    }

    public void start(MinecraftServer server) {
        this.server = server;
        this.sessionStartTick = server.getTickCount();
        this.sessionElapsedTicks = 0;
        this.running = true;
        this.savedTotalTicks = TimeStorage.loadTime(server);
        WorldTimerMod.LOGGER.info("Loaded saved time: {} ticks ({})",
                savedTotalTicks, formatTicks(savedTotalTicks));
    }

    public void tick() {
        if (!running || server == null) return;
        this.sessionElapsedTicks = server.getTickCount() - sessionStartTick;
    }

    public void stop() {
        if (!running) return;
        long totalTicks = savedTotalTicks + sessionElapsedTicks;
        TimeStorage.saveTime(server, totalTicks);
        this.running = false;
        this.server = null;
    }

    public boolean isRunning() {
        return running;
    }

    public long getTotalTicks() {
        return savedTotalTicks + sessionElapsedTicks;
    }

    public String getFormattedTotalTime() {
        return formatTicks(getTotalTicks());
    }

    public static String formatTicks(long ticks) {
        long totalSeconds = ticks / 20;
        long hours = TimeUnit.SECONDS.toHours(totalSeconds);
        long minutes = TimeUnit.SECONDS.toMinutes(totalSeconds) % 60;
        long seconds = totalSeconds % 60;
        return String.format("\u23F1 %02d:%02d:%02d", hours, minutes, seconds);
    }
}
