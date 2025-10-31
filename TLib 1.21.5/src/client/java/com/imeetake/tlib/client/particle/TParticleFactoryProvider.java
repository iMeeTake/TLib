package com.imeetake.tlib.client.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleEffect;

/**
 * A generic particle factory that automatically assigns a sprite to the created particle.
 * Simplifies particle creation using a custom particle creator function.
 *
 * @param <T> the particle effect type
 */
public class TParticleFactoryProvider<T extends ParticleEffect> implements ParticleFactory<T> {

    private final SpriteProvider spriteProvider;
    private final ParticleCreator<T> creator;

    /**
     * Constructs a new factory provider with the given sprite and particle creator.
     *
     * @param spriteProvider the sprite provider used for the particle
     * @param creator a lambda or method that creates the particle instance
     */
    public TParticleFactoryProvider(SpriteProvider spriteProvider, ParticleCreator<T> creator) {
        this.spriteProvider = spriteProvider;
        this.creator = creator;
    }

    /**
     * Creates a particle and assigns the sprite to it.
     */
    @Override
    public Particle createParticle(T effect, ClientWorld world, double x, double y, double z, double dx, double dy, double dz) {
        SpriteBillboardParticle particle = creator.create(world, x, y, z, dx, dy, dz);
        particle.setSprite(spriteProvider);
        return particle;
    }

    /**
     * A functional interface for defining custom particle creation logic.
     */
    @FunctionalInterface
    public interface ParticleCreator<T extends ParticleEffect> {
        SpriteBillboardParticle create(ClientWorld world, double x, double y, double z, double dx, double dy, double dz);
    }
}
