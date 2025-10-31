package com.imeetake.tlib.client.particle;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Function;

/**
 * Utility class for registering custom particle types and factories.
 */
public class TParticles {

    /**
     * Registers a simple (stateless) particle type.
     *
     * @param modId the mod ID
     * @param name the particle name
     * @return the registered ParticleType instance
     */
    public static ParticleType<?> simple(String modId, String name) {
        ParticleType<?> type = FabricParticleTypes.simple();
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(modId, name), type);
        return type;
    }

    @FunctionalInterface
    public interface OrientedParticleFactory<T extends ParticleEffect> {
        TOrientedParticle<T> create(ClientWorld world,
                                    double x, double y, double z,
                                    double velocityX, double velocityY, double velocityZ,
                                    SpriteProvider spriteProvider);
    }

    public static <T extends ParticleEffect> void registerOriented(
            ParticleType<T> type,
            OrientedParticleFactory<T> factory
    ) {
        ParticleFactoryRegistry.getInstance().register(type, (net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider spriteProvider) ->
                (T parameters, ClientWorld world, double x, double y, double z, double dx, double dy, double dz, net.minecraft.util.math.random.Random random) ->
                        factory.create(world, x, y, z, dx, dy, dz, spriteProvider)
        );
    }


    /**
     * Registers a particle factory using a custom factory function.
     *
     * @param type the particle type
     * @param factoryFunction a function that returns a factory given a sprite provider
     */
    public static <T extends ParticleEffect> void register(ParticleType<T> type, Function<SpriteProvider, ParticleFactory<T>> factoryFunction) {
        ParticleFactoryRegistry.getInstance().register(type, factoryFunction::apply);
    }

    /**
     * Registers a simple particle factory based on a particle constructor.
     *
     * @param type the particle type
     * @param creator a lambda or method reference to create the particle instance
     */
    public static <T extends ParticleEffect> void registerSimple(
            ParticleType<T> type,
            TParticleFactoryProvider.ParticleCreator<T> creator
    ) {
        ParticleFactoryRegistry.getInstance().register(type, sprite -> new TParticleFactoryProvider<>(sprite, creator));
    }
}