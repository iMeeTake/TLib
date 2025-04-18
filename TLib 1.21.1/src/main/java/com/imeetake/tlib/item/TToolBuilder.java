package com.imeetake.tlib.item;

import com.imeetake.tlib.TContext;
import com.imeetake.tlib.registry.TRegistry;
import net.minecraft.item.*;
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
        String modId = TContext.getModId();
        Identifier fullId = Identifier.of(modId, id);

        switch (type) {
            case PICKAXE -> {
                settings.attributeModifiers(PickaxeItem.createAttributeModifiers(material, damage, attackSpeed));
                PickaxeItem item = new PickaxeItem(material, settings);
                TRegistry.registerItem(id, item);
                return item;
            }
            case AXE -> {
                settings.attributeModifiers(AxeItem.createAttributeModifiers(material, damage, attackSpeed));
                AxeItem item = new AxeItem(material, settings);
                TRegistry.registerItem(id, item);
                return item;
            }
            case SHOVEL -> {
                settings.attributeModifiers(ShovelItem.createAttributeModifiers(material, damage, attackSpeed));
                ShovelItem item = new ShovelItem(material, settings);
                TRegistry.registerItem(id, item);
                return item;
            }
            case HOE -> {
                settings.attributeModifiers(HoeItem.createAttributeModifiers(material, damage, attackSpeed));
                HoeItem item = new HoeItem(material, settings);
                TRegistry.registerItem(id, item);
                return item;
            }
            default -> throw new IllegalStateException("Unknown tool type: " + type);
        }
    }

    private enum ToolType {
        PICKAXE, AXE, SHOVEL, HOE
    }
}
