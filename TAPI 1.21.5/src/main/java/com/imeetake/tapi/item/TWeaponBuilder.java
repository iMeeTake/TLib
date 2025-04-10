package com.imeetake.tapi.item;

import com.imeetake.tapi.registry.TRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class TWeaponBuilder {
    private final String id;
    private final ToolMaterial material;
    private int damage = 3;
    private float attackSpeed = -2.4f;
    private final Item.Settings settings;

    private TWeaponBuilder(String id, ToolMaterial material) {
        this.id = id;
        this.material = material;
        this.settings = new Item.Settings();
    }

    public static TWeaponBuilder sword(String id, ToolMaterial material) {
        return new TWeaponBuilder(id, material);
    }

    public TWeaponBuilder damage(int damage) {
        this.damage = damage;
        return this;
    }

    public TWeaponBuilder attackSpeed(float speed) {
        this.attackSpeed = speed;
        return this;
    }

    public TWeaponBuilder maxCount(int count) {
        settings.maxCount(count);
        return this;
    }

    public TWeaponBuilder maxDamage(int damage) {
        settings.maxDamage(damage);
        return this;
    }

    public Item build() {
        // Получаем полный ID с учётом modId
        Identifier identifier = TRegistry.getItemId(id);

        // Устанавливаем характеристики оружия и обязательно registryKey ДО создания предмета
        settings
                .sword(material, damage, attackSpeed)
                .registryKey(RegistryKey.of(RegistryKeys.ITEM, identifier));

        // Создаём и регистрируем предмет
        Item item = new Item(settings);
        return TRegistry.registerItem(id, item);
    }
}
