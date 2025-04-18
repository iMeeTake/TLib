package com.imeetake.tlib.item;

import com.imeetake.tlib.TContext;
import com.imeetake.tlib.registry.TRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class TItemBuilder {
    private final String id;
    private final Item.Settings settings;

    private TItemBuilder(String id) {
        this.id = id;
        this.settings = new Item.Settings();
    }

    public static TItemBuilder create(String id) {
        return new TItemBuilder(id);
    }

    public TItemBuilder maxCount(int count) {
        settings.maxCount(count);
        return this;
    }

    public TItemBuilder maxDamage(int damage) {
        settings.maxDamage(damage);
        return this;
    }


    public Item build() {
        String modId = TContext.getModId();
        Identifier fullId = Identifier.of(modId, id);
        Item item = new Item(settings);
        return TRegistry.registerItem(id, item);
    }
}
