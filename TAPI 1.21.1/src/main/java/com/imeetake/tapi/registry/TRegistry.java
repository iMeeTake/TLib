package com.imeetake.tapi.registry;

import com.imeetake.tapi.TContext;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class TRegistry {
    public static Item registerItem(String id, Item item) {
        String modId = TContext.getModId();
        return Registry.register(Registries.ITEM, Identifier.of(modId, id), item);
    }
}