package com.imeetake.tlib.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.render.Camera;
import net.minecraft.util.math.Vec3d;

/**
 * Utility class for accessing client-side rendering state:
 * camera, look direction, perspective mode, HUD visibility, and GUI status.
 */
@Environment(EnvType.CLIENT)
public class TClientRenderUtils {

    /**
     * Returns the current active game camera.
     */
    public static Camera getCamera() {
        return MinecraftClient.getInstance().gameRenderer.getCamera();
    }

    /**
     * Returns the current world-space position of the camera.
     * May differ from the player's position.
     */
    public static Vec3d getCameraPosition() {
        Camera camera = getCamera();
        return camera.getPos();
    }

    /**
     * Returns the unit vector representing the direction the camera is looking at.
     */
    public static Vec3d getCameraLookVector() {
        Camera camera = getCamera();
        return Vec3d.fromPolar(camera.getPitch(), camera.getYaw());
    }

    /**
     * Returns true if the current camera perspective is first-person.
     */
    public static boolean isFirstPerson() {
        return MinecraftClient.getInstance().options.getPerspective() == Perspective.FIRST_PERSON;
    }

    /**
     * Returns true if the camera is in third-person back view.
     */
    public static boolean isThirdPersonBack() {
        return MinecraftClient.getInstance().options.getPerspective() == Perspective.THIRD_PERSON_BACK;
    }

    /**
     * Returns true if the camera is in third-person front view.
     */
    public static boolean isThirdPersonFront() {
        return MinecraftClient.getInstance().options.getPerspective() == Perspective.THIRD_PERSON_FRONT;
    }

    /**
     * Returns true if the HUD is currently visible.
     */
    public static boolean isHudVisible() {
        return !MinecraftClient.getInstance().options.hudHidden;
    }

    /**
     * Returns true if any GUI screen is currently open (e.g. inventory, menu, chat).
     */
    public static boolean isInGUI() {
        return MinecraftClient.getInstance().currentScreen != null;
    }

    /**
     * Returns the current field of view (FOV) set in the player’s settings.
     */
    public static double getFov() {
        return MinecraftClient.getInstance().options.getFov().getValue();
    }
}
