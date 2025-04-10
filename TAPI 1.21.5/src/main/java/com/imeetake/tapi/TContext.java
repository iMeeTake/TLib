package com.imeetake.tapi;

public class TContext {
    private static String modId = null;

    public static void init(String modId) {
        if (TContext.modId != null)
            throw new IllegalStateException("TAPI modId already set");
        TContext.modId = modId;
    }

    public static String getModId() {
        if (modId == null)
            throw new IllegalStateException("TAPI modId not set. Call TContext.init(modId) in your mod initializer.");
        return modId;
    }
}
