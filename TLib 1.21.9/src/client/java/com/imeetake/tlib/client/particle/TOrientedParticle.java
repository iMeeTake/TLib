package com.imeetake.tlib.client.particle;

import net.minecraft.client.particle.BillboardParticle;
import net.minecraft.client.particle.BillboardParticleSubmittable;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.render.Camera;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleEffect;


public abstract class TOrientedParticle<T extends ParticleEffect> extends BillboardParticle {

    protected final SpriteProvider spriteProvider;
    protected float scale;

    protected TOrientedParticle(ClientWorld world,
                                double x, double y, double z,
                                double velocityX, double velocityY, double velocityZ,
                                SpriteProvider spriteProvider) {
        super(world, x, y, z, velocityX, velocityY, velocityZ, spriteProvider.getSprite(0, 0));
        this.spriteProvider = spriteProvider;
        this.scale = 1.0f;
        this.collidesWithWorld = false;
    }

    @Override
    public ParticleTextureSheet textureSheet() {
        return ParticleTextureSheet.SINGLE_QUADS;
    }

    @Override
    public abstract void render(BillboardParticleSubmittable submittable, Camera camera, float tickProgress);

    @Override
    public float getSize(float tickProgress) {
        return this.scale;
    }

    @Override
    protected RenderType getRenderType() {
        return RenderType.PARTICLE_ATLAS_TRANSLUCENT;
    }

}