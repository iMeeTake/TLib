package com.imeetake.tlib.client.particle;

import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;

/**
 * A simple implementation of ParticleEffect that only stores the particle type.
 * Useful for effects that don't require additional data.
 */
public class TParticleEffectSimple implements ParticleEffect {
    private final ParticleType<?> type;

    /**
     * Creates a new simple particle effect with the specified type.
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
