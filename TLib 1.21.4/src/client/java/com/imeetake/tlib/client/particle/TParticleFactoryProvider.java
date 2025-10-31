package com.imeetake.tlib.client.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleEffect;

/**
 * A reusable factory for creating sprite-based particles with automatic sprite binding.
 *
 * @param <T> the type of particle effect this factory handles
 */
public class TParticleFactoryProvider<T extends ParticleEffect> implements ParticleFactory<T> {

    private final SpriteProvider spriteProvider;
    private final ParticleCreator<T> creator;

    /**
     * Creates a particle factory with the given sprite provider and particle creation logic.
     *
     * @param spriteProvider the sprite provider for this particle type
     * @param creator        a function that constructs the particle instance
     */
    public TParticleFactoryProvider(SpriteProvider spriteProvider, ParticleCreator<T> creator) {
        this.spriteProvider = spriteProvider;
        this.creator = creator;
    }

    /**
     * Creates a new particle and assigns its sprite from the provider.
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
