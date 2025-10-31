package com.imeetake.tlib.client.particle;

import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.Vec3d;

/**
 * Utility methods for directional particle spawning:
 * – spawnTowards  → move toward a target
 * – spawnAway     → move away from a target
 * – spawnUp       → move upward
 * – spawnDown     → move downward
 */
public final class TParticleTargeting {
    private TParticleTargeting() {}

    /**
     * Calculates a normalized direction vector from (sx, sy, sz) to (tx, ty, tz).
     *
     * @return a unit vector pointing from source to target
     */
    public static Vec3d direction(double sx, double sy, double sz, double tx, double ty, double tz) {
        Vec3d diff = new Vec3d(tx - sx, ty - sy, tz - sz);
        double len = diff.length();
        return len == 0 ? Vec3d.ZERO : diff.multiply(1.0 / len);
    }

    /**
     * Spawns a particle moving toward the specified target with a given speed.
     */
    public static void spawnTowards(ParticleEffect effect,
                                    double sx, double sy, double sz,
                                    double tx, double ty, double tz,
                                    double speed) {
        Vec3d dir = direction(sx, sy, sz, tx, ty, tz);
        TClientParticles.spawn(effect,
                sx, sy, sz,
                dir.x * speed, dir.y * speed, dir.z * speed
        );
    }

    /**
     * Spawns a particle moving away from the specified target with a given speed.
     */
    public static void spawnAway(ParticleEffect effect,
                                 double sx, double sy, double sz,
                                 double tx, double ty, double tz,
                                 double speed) {
        Vec3d dir = direction(sx, sy, sz, tx, ty, tz).multiply(-1.0);
        TClientParticles.spawn(effect,
                sx, sy, sz,
                dir.x * speed, dir.y * speed, dir.z * speed
        );
    }

    /**
     * Spawns a particle moving upward with the specified speed.
     */
    public static void spawnUp(ParticleEffect effect,
                               double x, double y, double z,
                               double speed) {
        TClientParticles.spawn(effect,
                x, y, z,
                0, speed, 0
        );
    }

    /**
     * Spawns a particle moving downward with the specified speed.
     */
    public static void spawnDown(ParticleEffect effect,
                                 double x, double y, double z,
                                 double speed) {
        TClientParticles.spawn(effect,
                x, y, z,
                0, -speed, 0
        );
    }
}
