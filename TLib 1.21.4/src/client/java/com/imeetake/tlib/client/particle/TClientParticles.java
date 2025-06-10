package com.imeetake.tlib.client.particle;

import net.minecraft.client.MinecraftClient;
import net.minecraft.particle.ParticleEffect;

/**
 * Utility class for spawning client-side particles.
 */
public class TClientParticles {

    /**
     * Spawns a particle at the given position with no velocity.
     */
    public static void spawn(ParticleEffect effect, double x, double y, double z) {
        spawn(effect, x, y, z, 0, 0, 0);
    }

    /**
     * Spawns a particle at the given position with specified velocity.
     */
    public static void spawn(ParticleEffect effect, double x, double y, double z, double dx, double dy, double dz) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world != null) {
            client.world.addParticle(effect, x, y, z, dx, dy, dz);
        }
    }
}
