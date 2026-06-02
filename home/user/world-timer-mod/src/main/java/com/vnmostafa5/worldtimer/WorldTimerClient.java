package com.vnmostafa5.worldtimer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

public class WorldTimerClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(this::renderTimerHUD);
    }

    private void renderTimerHUD(GuiGraphics drawContext, float tickDelta) {
        Minecraft client = Minecraft.getInstance();

        if (client.player == null || client.options.hideGui) {
            return;
        }

        TimeTracker tracker = TimeTracker.getInstance();
        if (!tracker.isRunning()) {
            return;
        }

        String timeText = tracker.getFormattedTotalTime();
        Font font = client.font;

        // Shadow for readability
        drawContext.drawString(font, timeText, 6, 6, 0x000000);
        // Gold text
        drawContext.drawString(font, timeText, 5, 5, 0xFFD700);
    }
}
