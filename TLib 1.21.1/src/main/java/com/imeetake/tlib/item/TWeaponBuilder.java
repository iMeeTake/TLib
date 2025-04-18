package com.imeetake.tlib.item;

import com.imeetake.tlib.TContext;
import com.imeetake.tlib.registry.TRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Identifier;

public class TWeaponBuilder {
    private final String id;
    private final ToolMaterial material;
    private int damage = 3;
    private float attackSpeed = -2.4f;
    private final Item.Settings settings;
    private ItemGroup group = null;

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


    public SwordItem build() {
        String modId = TContext.getModId();
        Identifier fullId = Identifier.of(modId, id);
        settings.attributeModifiers(SwordItem.createAttributeModifiers(material, damage, attackSpeed));
        SwordItem item = new SwordItem(material, settings);
        TRegistry.registerItem(id, item);
        return item;
    }
}
