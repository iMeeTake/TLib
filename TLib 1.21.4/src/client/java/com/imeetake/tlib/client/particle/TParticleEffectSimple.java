package com.imeetake.tlib.client.particle;

import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;

/**
 * A simple implementation of ParticleEffect that only stores its type.
 * Useful for stateless particles without additional data.
 */
public class TParticleEffectSimple implements ParticleEffect {

    private final ParticleType<?> type;

    /**
     * Creates a simple particle effect for the given type.
     */
    public TParticleEffectSimple(ParticleType<?> type) {
        this.type = type;
    }

    /**
     * Returns the type of this particle effect.
     */
    @Override
    public ParticleType<?> getType() {
        return type;
    }
}
