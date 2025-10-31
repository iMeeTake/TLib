package com.imeetake.tlib.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.biome.Biome;

/**
 * Utility class for getting information about weather, time of day,
 * and the current biome on the client side.
 */
@Environment(EnvType.CLIENT)
public class TClientEnvironment {

    /**
     * Returns the current client-side world.
     */
    public static ClientWorld getWorld() {
        return MinecraftClient.getInstance().world;
    }

    /**
     * Returns true if it is currently raining in the client world.
     */
    public static boolean isRaining() {
        ClientWorld world = getWorld();
        return world != null && world.isRaining();
    }

    /**
     * Returns true if there is a thunderstorm in the client world.
     */
    public static boolean isThundering() {
        ClientWorld world = getWorld();
        return world != null && world.isThundering();
    }

    /**
     * Returns the current rain strength (between 0 and 1).
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
     * Returns true if the player is currently under open sky.
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
     * Returns true if it is currently nighttime in the client world.
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
