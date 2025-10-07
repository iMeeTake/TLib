package com.imeetake.tlib.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.biome.Biome;

/**
 * Utility class for accessing client-side environment data:
 * weather, time of day, biome, and sky visibility.
 */
@Environment(EnvType.CLIENT)
public class TClientEnvironment {

    /**
     * Returns the current client world.
     */
    public static ClientWorld getWorld() {
        return MinecraftClient.getInstance().world;
    }

    /**
     * Returns true if it is currently raining.
     */
    public static boolean isRaining() {
        ClientWorld world = getWorld();
        return world != null && world.isRaining();
    }

    /**
     * Returns true if it is currently thundering.
     */
    public static boolean isThundering() {
        ClientWorld world = getWorld();
        return world != null && world.isThundering();
    }

    /**
     * Returns the current rain strength (from 0 to 1).
     *
     * @param tickDelta partial tick time
     */
    public static float getRainStrength(float tickDelta) {
        ClientWorld world = getWorld();
        return world != null ? world.getRainGradient(tickDelta) : 0.0f;
    }

    /**
     * Returns the biome the player is currently in.
     */
    public static RegistryEntry<Biome> getCurrentBiome() {
        ClientWorld world = getWorld();
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        if (world != null && player != null) {
            return world.getBiome(player.getBlockPos());
        }

        return null;
    }

    /**
     * Checks if the player is under open sky (not covered).
     */
    public static boolean isInOpenSky() {
        ClientWorld world = getWorld();
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        if (world != null && player != null) {
            return world.isSkyVisible(player.getBlockPos());
        }

        return false;
    }

    /**
     * Returns true if the current time is considered nighttime.
     */
    public static boolean isNight() {
        ClientWorld world = getWorld();

        if (world != null) {
            long timeOfDay = world.getTimeOfDay() % 24000L;
            return timeOfDay >= 13000L && timeOfDay <= 23000L;
        }

        return false;
    }
}
