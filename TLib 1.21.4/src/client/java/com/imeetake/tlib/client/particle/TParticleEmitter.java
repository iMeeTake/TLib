package com.imeetake.tlib.client.particle;

import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.Box;

import java.util.Random;

/**
 * Utility class for emitting multiple particles in common shapes.
 * Supports sphere, cube, and AABB (axis-aligned bounding box).
 */
public class TParticleEmitter {

    private static final Random RANDOM = new Random();

    /**
     * Spawns particles randomly distributed inside a 3D sphere.
     *
     * @param effect the particle to spawn
     * @param cx     center X coordinate
     * @param cy     center Y coordinate
     * @param cz     center Z coordinate
     * @param radius the radius of the sphere
     * @param count  number of particles to spawn
     */
    public static void sphere(ParticleEffect effect, double cx, double cy, double cz, double radius, int count) {
        for (int i = 0; i < count; i++) {
            double costheta = 2 * RANDOM.nextDouble() - 1;
            double phi = 2 * Math.PI * RANDOM.nextDouble();
            double r = radius * Math.cbrt(RANDOM.nextDouble()); // uniform sphere
            double sinTheta = Math.sqrt(1 - costheta * costheta);

            double x = cx + r * sinTheta * Math.cos(phi);
            double y = cy + r * sinTheta * Math.sin(phi);
            double z = cz + r * costheta;

            TClientParticles.spawn(effect, x, y, z);
        }
    }

    /**
     * Spawns particles randomly distributed inside a cube.
     *
     * @param effect the particle to spawn
     * @param minX   cube start X
     * @param minY   cube start Y
     * @param minZ   cube start Z
     * @param maxX   cube end X
     * @param maxY   cube end Y
     * @param maxZ   cube end Z
     * @param count  number of particles to spawn
     */
    public static void cube(ParticleEffect effect,
                            double minX, double minY, double minZ,
                            double maxX, double maxY, double maxZ,
                            int count) {
        for (int i = 0; i < count; i++) {
            double x = minX + RANDOM.nextDouble() * (maxX - minX);
            double y = minY + RANDOM.nextDouble() * (maxY - minY);
            double z = minZ + RANDOM.nextDouble() * (maxZ - minZ);

            TClientParticles.spawn(effect, x, y, z);
        }
    }

    /**
     * Spawns particles randomly distributed inside a bounding box.
     *
     * @param effect the particle to spawn
     * @param box    the AABB to fill with particles
     * @param count  number of particles to spawn
     */
    public static void aabb(ParticleEffect effect, Box box, int count) {
        cube(effect,
                box.minX, box.minY, box.minZ,
                box.maxX, box.maxY, box.maxZ,
                count);
    }
}
