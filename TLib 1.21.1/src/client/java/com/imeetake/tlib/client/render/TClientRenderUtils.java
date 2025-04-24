package com.imeetake.tlib.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.render.Camera;
import net.minecraft.util.math.Vec3d;

/**
 * Утилита для получения информации о клиентском рендеринге:
 * камера, направление взгляда, режим от первого/третьего лица, состояние HUD и экрана.
 */
@Environment(EnvType.CLIENT)
public class TClientRenderUtils {

    // Возвращает текущую игровую камеру клиента.
    public static Camera getCamera() {
        return MinecraftClient.getInstance().gameRenderer.getCamera();
    }

    // Возвращает позицию камеры в мире. Может отличаться от позиции игрока.
    public static Vec3d getCameraPosition() {
        Camera camera = getCamera();
        return camera.getPos();
    }

    // Возвращает вектор направления взгляда камеры.
    public static Vec3d getCameraLookVector() {
        Camera camera = getCamera();
        return Vec3d.fromPolar(camera.getPitch(), camera.getYaw());
    }

    // Проверяет, включён ли режим от первого лица.
    public static boolean isFirstPerson() {
        return MinecraftClient.getInstance().options.getPerspective() == Perspective.FIRST_PERSON;
    }

    // Проверяет, включён ли режим от третьего лица (вид сзади).
    public static boolean isThirdPersonBack() {
        return MinecraftClient.getInstance().options.getPerspective() == Perspective.THIRD_PERSON_BACK;
    }

    // Проверяет, включён ли режим от третьего лица (вид спереди).
    public static boolean isThirdPersonFront() {
        return MinecraftClient.getInstance().options.getPerspective() == Perspective.THIRD_PERSON_FRONT;
    }

    // Проверяет, отображается ли HUD.
    public static boolean isHudVisible() {
        return !MinecraftClient.getInstance().options.hudHidden;
    }

    // Проверяет, открыт ли сейчас экран (инвентарь, меню, чат и т.п.).
    public static boolean isInGUI() {
        return MinecraftClient.getInstance().currentScreen != null;
    }

    // Возвращает текущий угол обзора (FOV), установленный в настройках игрока.
    public static double getFov() {
        return MinecraftClient.getInstance().options.getFov().getValue();
    }
}
