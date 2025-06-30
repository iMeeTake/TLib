package com.imeetake.tlib.client.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleEffect;


public abstract class TOrientedParticle<T extends ParticleEffect> extends Particle {

    protected final SpriteProvider spriteProvider;
    protected float scale;

    protected TOrientedParticle(ClientWorld world,
                                double x, double y, double z,
                                double velocityX, double velocityY, double velocityZ,
                                SpriteProvider spriteProvider) {
        super(world, x, y, z, velocityX, velocityY, velocityZ);
        this.spriteProvider = spriteProvider;
        this.scale = 1.0f;
        this.collidesWithWorld = false;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public abstract void buildGeometry(VertexConsumer vertexConsumer,
                                       Camera camera,
                                       float tickDelta);
}
