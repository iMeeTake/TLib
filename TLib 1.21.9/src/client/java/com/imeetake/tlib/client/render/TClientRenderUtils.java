package com.imeetake.tlib.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.render.Camera;
import net.minecraft.util.math.Vec3d;

/**
 * Utility class for accessing client-side rendering state:
 * camera, view direction, perspective mode, HUD visibility, and screen status.
 */
@Environment(EnvType.CLIENT)
public class TClientRenderUtils {

    /**
     * Returns the client's current game camera.
     */
    public static Camera getCamera() {
        return MinecraftClient.getInstance().gameRenderer.getCamera();
    }

    /**
     * Returns the current position of the camera in the world.
     * This may differ from the player's position.
     */
    public static Vec3d getCameraPosition() {
        Camera camera = getCamera();
        return camera.getPos();
    }

    /**
     * Returns the direction vector the camera is currently facing.
     */
    public static Vec3d getCameraLookVector() {
        Camera camera = getCamera();
        return Vec3d.fromPolar(camera.getPitch(), camera.getYaw());
    }

    /**
     * Checks if the camera is in first-person view.
     */
    public static boolean isFirstPerson() {
        return MinecraftClient.getInstance().options.getPerspective() == Perspective.FIRST_PERSON;
    }

    /**
     * Checks if the camera is in third-person back view.
     */
    public static boolean isThirdPersonBack() {
        return MinecraftClient.getInstance().options.getPerspective() == Perspective.THIRD_PERSON_BACK;
    }

    /**
     * Checks if the camera is in third-person front view.
     */
    public static boolean isThirdPersonFront() {
        return MinecraftClient.getInstance().options.getPerspective() == Perspective.THIRD_PERSON_FRONT;
    }

    /**
     * Checks if the HUD (overlay) is currently visible.
     */
    public static boolean isHudVisible() {
        return !MinecraftClient.getInstance().options.hudHidden;
    }

    /**
     * Checks if any GUI screen is currently open (inventory, menu, chat, etc.).
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
