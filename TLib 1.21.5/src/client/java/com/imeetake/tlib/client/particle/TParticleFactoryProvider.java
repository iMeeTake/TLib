package com.imeetake.tlib.client.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleEffect;

/**
 * Обёртка для фабрики частиц с автоматическим подключением спрайта.
 */
public class TParticleFactoryProvider<T extends ParticleEffect> implements ParticleFactory<T> {

    private final SpriteProvider spriteProvider;
    private final ParticleCreator<T> creator;

    public TParticleFactoryProvider(SpriteProvider spriteProvider, ParticleCreator<T> creator) {
        this.spriteProvider = spriteProvider;
        this.creator = creator;
    }

    @Override
    public Particle createParticle(T effect, ClientWorld world, double x, double y, double z, double dx, double dy, double dz) {
        SpriteBillboardParticle particle = creator.create(world, x, y, z, dx, dy, dz);
        particle.setSprite(spriteProvider);
        return particle;
    }

    @FunctionalInterface
    public interface ParticleCreator<T extends ParticleEffect> {
        SpriteBillboardParticle create(ClientWorld world, double x, double y, double z, double dx, double dy, double dz);
    }
}
