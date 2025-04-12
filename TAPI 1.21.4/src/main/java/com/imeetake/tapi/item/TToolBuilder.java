package com.imeetake.tapi.item;

import com.imeetake.tapi.registry.TRegistry;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class TToolBuilder {
    private final String id;
    private final ToolMaterial material;
    private final Item.Settings settings;

    private int damage = 1;
    private float attackSpeed = -2.8f;
    private ToolType type;

    private TToolBuilder(String id, ToolMaterial material, ToolType type) {
        this.id = id;
        this.material = material;
        this.type = type;
        this.settings = new Item.Settings();
    }

    public static TToolBuilder pickaxe(String id, ToolMaterial material) {
        return new TToolBuilder(id, material, ToolType.PICKAXE);
    }

    public static TToolBuilder axe(String id, ToolMaterial material) {
        return new TToolBuilder(id, material, ToolType.AXE);
    }

    public static TToolBuilder shovel(String id, ToolMaterial material) {
        return new TToolBuilder(id, material, ToolType.SHOVEL);
    }

    public static TToolBuilder hoe(String id, ToolMaterial material) {
        return new TToolBuilder(id, material, ToolType.HOE);
    }

    public TToolBuilder damage(int damage) {
        this.damage = damage;
        return this;
    }

    public TToolBuilder attackSpeed(float speed) {
        this.attackSpeed = speed;
        return this;
    }

    public TToolBuilder maxCount(int count) {
        settings.maxCount(count);
        return this;
    }

    public TToolBuilder maxDamage(int damage) {
        settings.maxDamage(damage);
        return this;
    }


    public Item build() {
        Identifier fullId = TRegistry.getItemId(this.id);
        settings.registryKey(RegistryKey.of(RegistryKeys.ITEM, fullId));

        return switch (type) {
            case PICKAXE -> {
                PickaxeItem item = new PickaxeItem(material, damage, attackSpeed, settings);
                TRegistry.registerItem(id, item);
                yield item;
            }
            case AXE -> {
                AxeItem item = new AxeItem(material, damage, attackSpeed, settings);
                TRegistry.registerItem(id, item);
                yield item;
            }
            case SHOVEL -> {
                ShovelItem item = new ShovelItem(material, damage, attackSpeed, settings);
                TRegistry.registerItem(id, item);
                yield item;
            }
            case HOE -> {
                HoeItem item = new HoeItem(material, damage, attackSpeed, settings);
                TRegistry.registerItem(id, item);
                yield item;
            }
        };
    }

    private enum ToolType {
        PICKAXE, AXE, SHOVEL, HOE
    }
}
