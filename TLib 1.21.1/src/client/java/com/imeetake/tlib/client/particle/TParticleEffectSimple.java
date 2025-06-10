package com.imeetake.tlib.client.particle;

import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;

/**
 * A simple implementation of ParticleEffect that only stores its type.
 * Useful for stateless particles with no custom data.
 */
public class TParticleEffectSimple implements ParticleEffect {

    private final ParticleType<?> type;

    /**
     * Creates a simple particle effect based on the given type.
     *
     * @param type the particle type
     */
    public TParticleEffectSimple(ParticleType<?> type) {
        this.type = type;
    }

    /**
     * Returns the particle type associated with this effect.
     */
    @Override
    public ParticleType<?> getType() {
        return type;
    }
}
