package com.imeetake.tlib.client.particle;

import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;

public class TParticleEffectSimple implements ParticleEffect {
    private final ParticleType<?> type;

    public TParticleEffectSimple(ParticleType<?> type) {
        this.type = type;
    }

    @Override
    public ParticleType<?> getType() {
        return type;
    }
}
