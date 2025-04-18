package com.imeetake.tlib.registry;

import com.imeetake.tlib.TContext;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class TRegistry {
    public static Item registerItem(String id, Item item) {
        String modId = TContext.getModId();
        return Registry.register(Registries.ITEM, Identifier.of(modId, id), item);
    }
    public static Identifier getItemId(String id) {
        return Identifier.of(TContext.getModId(), id);
    }
}