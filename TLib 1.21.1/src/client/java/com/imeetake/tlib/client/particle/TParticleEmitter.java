package com.imeetake.tlib.client.particle;

import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.Box;

import java.util.Random;

/**
 * Утилиты для массового спавна частиц по шаблонам (сфера, куб, AABB).
 */
public class TParticleEmitter {
    private static final Random RANDOM = new Random();

    /**
     * Спавнит {@code count} частиц с эффектом {@code effect} внутри сферы.
     *
     * @param effect эффект частицы
     * @param cx     X-координата центра
     * @param cy     Y-координата центра
     * @param cz     Z-координата центра
     * @param radius радиус сферы
     * @param count  количество частиц
     */
    public static void sphere(ParticleEffect effect, double cx, double cy, double cz, double radius, int count) {
        for (int i = 0; i < count; i++) {
            double costheta = 2 * RANDOM.nextDouble() - 1;
            double phi     = 2 * Math.PI * RANDOM.nextDouble();
            double r       = radius * Math.cbrt(RANDOM.nextDouble());
            double sinTheta = Math.sqrt(1 - costheta * costheta);

            double x = cx + r * sinTheta * Math.cos(phi);
            double y = cy + r * sinTheta * Math.sin(phi);
            double z = cz + r * costheta;

            TClientParticles.spawn(effect, x, y, z);
        }
    }

    /**
     * Спавнит {@code count} частиц в пределах куба [minX…maxX]×[minY…maxY]×[minZ…maxZ].
     *
     * @param effect эффект частицы
     * @param minX   минимальный X
     * @param minY   минимальный Y
     * @param minZ   минимальный Z
     * @param maxX   максимальный X
     * @param maxY   максимальный Y
     * @param maxZ   максимальный Z
     * @param count  количество частиц
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
     * @param effect эффект частицы
     * @param box    область AABB
     * @param count  количество частиц
     */
    public static void aabb(ParticleEffect effect, Box box, int count) {
        cube(effect,
                box.minX, box.minY, box.minZ,
                box.maxX, box.maxY, box.maxZ,
                count);
    }
}
