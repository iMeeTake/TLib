package com.imeetake.tlib.client.particle;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Function;

/**
 * Utility class for registering particles and their factories.
 */
public class TParticles {

    /**
     * Registers a simple, stateless ParticleType (without custom data).
     *
     * @param modId the namespace (usually your mod ID)
     * @param name  the particle name
     * @return the registered ParticleType instance
     */
    public static ParticleType<?> simple(String modId, String name) {
        ParticleType<?> type = FabricParticleTypes.simple();
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(modId, name), type);
        return type;
    }

    /**
     * Registers a custom particle factory using a factory function that accepts a SpriteProvider.
     *
     * @param type            the particle type
     * @param factoryFunction a function that returns a factory given a sprite provider
     * @param <T>             the particle effect type
     */
    public static <T extends ParticleEffect> void register(ParticleType<T> type, Function<SpriteProvider, ParticleFactory<T>> factoryFunction) {
        ParticleFactoryRegistry.getInstance().register(type, factoryFunction::apply);
    }

    /**
     * Registers a simple factory using a particle creator (constructor-based).
     *
     * @param type    the particle type
     * @param creator a particle constructor or lambda expression
     * @param <T>     the particle effect type
     */
    public static <T extends ParticleEffect> void registerSimple(
            ParticleType<T> type,
            TParticleFactoryProvider.ParticleCreator<T> creator
    ) {
        ParticleFactoryRegistry.getInstance().register(type, sprite -> new TParticleFactoryProvider<>(sprite, creator));
    }
}
