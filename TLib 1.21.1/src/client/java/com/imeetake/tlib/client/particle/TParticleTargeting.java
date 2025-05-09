package com.imeetake.tlib.client.particle;

import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.Vec3d;

/**
 * Утилиты для направления частиц:
 *  – spawnTowards  – к точке
 *  – spawnAway    – от точки
 *  – spawnUp      – вверх
 *  – spawnDown    – вниз
 */
public final class TParticleTargeting {
    private TParticleTargeting() {}

    /**
     * Вычисляет единичный вектор от (sx,sy,sz) к (tx,ty,tz).
     */
    public static Vec3d direction(double sx, double sy, double sz, double tx, double ty, double tz) {
        Vec3d diff = new Vec3d(tx - sx, ty - sy, tz - sz);
        double len = diff.length();
        return len == 0 ? Vec3d.ZERO : diff.multiply(1.0 / len);
    }

    /**
     * Спавнит частицу, движущуюся со скоростью speed к указанной точке.
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
     * Спавнит частицу, движущуюся со скоростью speed от указанной точки.
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
     * Спавнит частицу, движущуюся вверх со скоростью speed.
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
     * Спавнит частицу, движущуюся вниз со скоростью speed.
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
