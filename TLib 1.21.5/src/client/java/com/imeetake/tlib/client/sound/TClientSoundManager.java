package com.imeetake.tlib.client.sound;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

import java.util.HashMap;
import java.util.Map;

/**
 * Утилита для управления звуками на клиенте.
 * Поддерживает однократные и зацикленные звуки.
 */
@Environment(EnvType.CLIENT)
public class TClientSoundManager {

    // Хранит активные зацикленные звуки по их идентификаторам
    private static final Map<Identifier, SoundInstance> loopedSounds = new HashMap<>();

    // Воспроизводит одиночный звук
    public static void play(SoundEvent event, float volume) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null || client.player == null) return;

        // Устанавливаем pitch = 1.0f, а громкость из аргумента
        SoundInstance sound = PositionedSoundInstance.master(event, 1.0f, volume);
        client.getSoundManager().play(sound);
    }

    // Воспроизводит зацикленный звук, если он ещё не играет
    public static void playLooped(SoundEvent event, Identifier id, float volume) {
        if (loopedSounds.containsKey(id)) return;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null || client.player == null) return;

        Random random = client.world.getRandom();

        SoundInstance sound = new PositionedSoundInstance(
                event.id(),
                SoundCategory.AMBIENT,
                volume,
                1.0f,
                random,
                true,
                0,
                SoundInstance.AttenuationType.NONE,
                client.player.getX(),
                client.player.getY(),
                client.player.getZ(),
                false
        );

        loopedSounds.put(id, sound);
        client.getSoundManager().play(sound);
    }

    // Останавливает зацикленный звук по ID
    public static void stopLooped(Identifier id) {
        SoundManager soundManager = MinecraftClient.getInstance().getSoundManager();
        SoundInstance sound = loopedSounds.remove(id);

        if (sound != null) {
            soundManager.stop(sound);
        }
    }

    // Проверяет, воспроизводится ли зацикленный звук
    public static boolean isPlaying(Identifier id) {
        return loopedSounds.containsKey(id);
    }

    // Останавливает все зацикленные звуки
    public static void stopAll() {
        SoundManager soundManager = MinecraftClient.getInstance().getSoundManager();

        for (SoundInstance sound : loopedSounds.values()) {
            soundManager.stop(sound);
        }

        loopedSounds.clear();
    }
}
