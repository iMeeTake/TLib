package com.imeetake.tlib.client.particle;

import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.Vec3d;

/**
 * Utility class for directional particle emission.
 * Includes helpers to spawn particles:
 *  – toward a point
 *  – away from a point
 *  – straight upward
 *  – straight downward
 */
public final class TParticleTargeting {

    private TParticleTargeting() {}

    /**
     * Calculates a unit vector from (sx, sy, sz) to (tx, ty, tz).
     *
     * @param sx source X
     * @param sy source Y
     * @param sz source Z
     * @param tx target X
     * @param ty target Y
     * @param tz target Z
     * @return normalized direction vector
     */
    public static Vec3d direction(double sx, double sy, double sz, double tx, double ty, double tz) {
        Vec3d diff = new Vec3d(tx - sx, ty - sy, tz - sz);
        double len = diff.length();
        return len == 0 ? Vec3d.ZERO : diff.multiply(1.0 / len);
    }

    /**
     * Spawns a particle that moves toward a target point at the given speed.
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
     * Spawns a particle that moves away from a target point at the given speed.
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
     * Spawns a particle moving straight upward at the given speed.
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
     * Spawns a particle moving straight downward at the given speed.
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
