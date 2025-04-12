package com.imeetake.tapi;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

import java.util.Optional;

public class TContext {
    private static String modId = null;
    private static boolean manuallyInitialized = false;

    public static void init(String modId) {
        if (manuallyInitialized)
            throw new IllegalStateException("[TAPI] modId already initialized as: " + TContext.modId);
        if (modId == null || modId.isBlank())
            throw new IllegalArgumentException("[TAPI] Provided modId is null or empty");

        TContext.modId = modId;
        manuallyInitialized = true;
        System.out.println("[TAPI] Initialized manually with modId: " + modId);
    }

    public static String getModId() {
        if (modId != null) return modId;

        for (ModContainer container : FabricLoader.getInstance().getAllMods()) {
            try {
                if (container.getMetadata().getId().equals("minecraft")) continue;

                ClassLoader cl = container.getClass().getClassLoader();
                if (cl.loadClass(TContext.class.getName()) == TContext.class) {
                    modId = container.getMetadata().getId();
                    System.out.println("[TAPI] Auto-detected modId: " + modId);
                    return modId;
                }
            } catch (ClassNotFoundException | NoClassDefFoundError ignored) {}
        }

        throw new IllegalStateException("[TAPI] modId not set and could not be auto-detected. Please call TContext.init(modId).");
    }
}
