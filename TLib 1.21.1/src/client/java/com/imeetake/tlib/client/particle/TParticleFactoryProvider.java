package com.imeetake.tlib.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleEffect;

/**
 * A reusable wrapper for particle factories that automatically sets the sprite provider.
 *
 * @param <T> the particle effect type
 */
public class TParticleFactoryProvider<T extends ParticleEffect> implements ParticleFactory<T> {

    private final SpriteProvider spriteProvider;
    private final ParticleCreator<T> creator;

    /**
     * Creates a new particle factory wrapper using the given sprite provider and particle creation logic.
     *
     * @param spriteProvider the sprite provider for the particle
     * @param creator        the function that constructs the particle instance
     */
    public TParticleFactoryProvider(SpriteProvider spriteProvider, ParticleCreator<T> creator) {
        this.spriteProvider = spriteProvider;
        this.creator = creator;
    }

    /**
     * Creates a particle using the provided creator and assigns it the correct sprite.
     */
    @Override
    public Particle createParticle(T effect, ClientWorld world, double x, double y, double z, double dx, double dy, double dz) {
        SpriteBillboardParticle particle = creator.create(world, x, y, z, dx, dy, dz);
        particle.setSprite(spriteProvider);
        return particle;
    }

    /**
     * Functional interface used to create particles with given parameters.
     */
    @FunctionalInterface
    public interface ParticleCreator<T extends ParticleEffect> {
        SpriteBillboardParticle create(ClientWorld world, double x, double y, double z, double dx, double dy, double dz);
    }
}
