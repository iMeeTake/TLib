package com.imeetake.tlib.item;

import com.imeetake.tlib.TContext;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class TItemGroupBuilder {
    private final String id;
    private Supplier<ItemStack> icon = () -> ItemStack.EMPTY;
    private String displayName = null;
    private final List<Item> entries = new ArrayList<>();


    private TItemGroupBuilder(String id) {
        this.id = id;
    }

    public static TItemGroupBuilder create(String id) {
        return new TItemGroupBuilder(id);
    }

    public TItemGroupBuilder icon(Item item) {
        this.icon = () -> new ItemStack(item);
        return this;
    }

    public TItemGroupBuilder icon(Supplier<ItemStack> icon) {
        this.icon = icon;
        return this;
    }

    /**
     * Устанавливает локализуемое имя группы. Не влияет на отображение в игре,
     * но может использоваться для автогенерации lang-файлов.
     */
    public TItemGroupBuilder displayName(String name) {
        this.displayName = name;
        return this;
    }

    public TItemGroupBuilder add(Item item) {
        this.entries.add(item);
        return this;
    }

    public TItemGroupBuilder items(Item... items) {
        this.entries.addAll(List.of(items));
        return this;
    }

    public TItemGroupBuilder items(Collection<Item> items) {
        this.entries.addAll(items);
        return this;
    }

    public ItemGroup buildAndRegister() {
        String modId = TContext.getModId();
        Identifier id = Identifier.of(modId, this.id);

        // Ключ перевода, например "itemGroup.my_mod.gems"
        String translationKey = "itemGroup." + modId + "." + this.id;


        ItemGroup group = FabricItemGroup.builder()
                .displayName(Text.translatable(translationKey))
                .icon(icon)
                .entries((enabledFeatures, entries) -> {
                    for (Item item : this.entries) {
                        entries.add(item);
                    }
                })
                .build();

        return Registry.register(Registries.ITEM_GROUP, id, group);
    }
}
