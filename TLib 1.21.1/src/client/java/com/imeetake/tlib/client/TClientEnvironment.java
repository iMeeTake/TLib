package com.imeetake.tlib.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

/**
 * Utility class for accessing weather, time, and biome information on the client side.
 */
@Environment(EnvType.CLIENT)
public class TClientEnvironment {

    /**
     * Returns the current client world instance.
     *
     * @return the {@link ClientWorld} or null, если мир ещё не загружен
     */
    public static ClientWorld getWorld() {
        return MinecraftClient.getInstance().world;
    }

    /**
     * Returns true if it is currently raining in the world.
     *
     * @return true, если идёт дождь
     */
    public static boolean isRaining() {
        ClientWorld world = getWorld();
        return world != null && world.isRaining();
    }

    /**
     * Returns true if a thunderstorm is currently active.
     *
     * @return true, если сейчас гроза
     */
    public static boolean isThundering() {
        ClientWorld world = getWorld();
        return world != null && world.isThundering();
    }

    /**
     * Returns the current rain intensity (from 0.0 to 1.0).
     *
     * @param tickDelta partial ticks для сглаживания
     * @return уровень дождя, или 0.0f, если мир не загружен
     */
    public static float getRainStrength(float tickDelta) {
        ClientWorld world = getWorld();
        return world != null ? world.getRainGradient(tickDelta) : 0.0f;
    }

    /**
     * Returns the biome at the player's current position.
     *
     * @return запись биома под игроком, или null, если мир/игрок не готовы
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
     * Returns true if the player is under open sky.
     *
     * @return true, если над игроком нет тёмного потолка (не в пещере)
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
     * Returns true if the current in-game time is considered nighttime.
     *
     * @return true, если игра временно в ночной фазе
     */
    public static boolean isNight() {
        ClientWorld world = getWorld();

        if (world != null) {
            long timeOfDay = world.getTimeOfDay() % 24000L;
            return timeOfDay >= 13000L && timeOfDay <= 23000L;
        }
        return false;
    }

    public static boolean isColdBiome(RegistryEntry<Biome> entry, BlockPos pos) {
        if (entry == null) return false;
        Biome b = entry.value();
        try {
            return b.getPrecipitation(pos) == Biome.Precipitation.SNOW
                    || b.getTemperature() <= 0.15f;
        } catch (Throwable t) {
            return false;
        }
    }

    public static boolean isAridBiome(RegistryEntry<Biome> entry, BlockPos pos) {
        if (entry == null) return false;
        Biome b = entry.value();
        try {
            return b.getPrecipitation(pos) == Biome.Precipitation.NONE;
        } catch (Throwable t) {
            return false;
        }
    }


}
