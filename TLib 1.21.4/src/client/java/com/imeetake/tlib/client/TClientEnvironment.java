package com.imeetake.tlib.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.biome.Biome;

/**
 * Утилита для получения информации о погоде, времени суток и биоме на клиентской стороне.
 */
@Environment(EnvType.CLIENT)
public class TClientEnvironment {

    // Возвращает текущий клиентский мир
    public static ClientWorld getWorld() {
        return MinecraftClient.getInstance().world;
    }

    // Проверяет, идёт ли дождь
    public static boolean isRaining() {
        ClientWorld world = getWorld();
        return world != null && world.isRaining();
    }

    // Проверяет, идёт ли гроза
    public static boolean isThundering() {
        ClientWorld world = getWorld();
        return world != null && world.isThundering();
    }

    // Возвращает силу дождя (от 0 до 1)
    public static float getRainStrength(float tickDelta) {
        ClientWorld world = getWorld();
        return world != null ? world.getRainGradient(tickDelta) : 0.0f;
    }

    // Возвращает текущий биом, в котором находится игрок
    public static RegistryEntry<Biome> getCurrentBiome() {
        ClientWorld world = getWorld();
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        if (world != null && player != null) {
            return world.getBiome(player.getBlockPos());
        }

        return null;
    }

    // Проверяет, находится ли игрок под открытым небом
    public static boolean isInOpenSky() {
        ClientWorld world = getWorld();
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        if (world != null && player != null) {
            return world.isSkyVisible(player.getBlockPos());
        }

        return false;
    }

    // Проверяет, является ли текущее время ночью
    public static boolean isNight() {
        ClientWorld world = getWorld();

        if (world != null) {
            long timeOfDay = world.getTimeOfDay() % 24000L;
            return timeOfDay >= 13000L && timeOfDay <= 23000L;
        }

        return false;
    }
}
