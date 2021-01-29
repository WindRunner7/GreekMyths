package com.Wind__Runner.GreekMyths.init;

import com.Wind__Runner.GreekMyths.GreekMyths;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public enum ModArmorMaterial implements IArmorMaterial
{
    GreekArmor("greekarmor", 400, new int[] {8, 10, 9, 7}, 25, ModItems.SILVER_INGOT.get(), "item.armor.equip_iron", 0.0f, 0),
    MythicArmor("mythicarmor", 400, new int[] {8, 10, 9, 7}, 25, ModItems.SILVER_INGOT.get(), "item.armor.equip_iron", 0.0f, 0);

    private static final int[] max_damage_array = new int[]{13, 15, 16, 11};
    private String name, equipSound;
    private int durability, enchantability;
    private Item repairItem;
    private int[] damageReductionAmounts;
    private float toughness, knockback;

    private ModArmorMaterial(String name, int durability, int[] damageReductionAmounts, int enchantability, Item repairItem, String equipSound, float toughness, float knockback)
    {
        this.name = name;
        this.equipSound = equipSound;
        this.durability = durability;
        this.enchantability = enchantability;
        this.repairItem = repairItem;
        this.damageReductionAmounts = damageReductionAmounts;
        this.toughness = toughness;
        this.knockback = knockback;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slot)
    {
        return this.damageReductionAmounts[slot.getIndex()];
    }

    @Override
    public int getDurability(EquipmentSlotType slot)
    {
        return max_damage_array[slot.getIndex()] * this.durability;
    }

    @Override
    public int getEnchantability()
    {
        return this.enchantability;
    }

    @Override
    public String getName()
    {
        return GreekMyths.MOD_ID + ":" + this.name;
    }

    @Override
    public Ingredient getRepairMaterial()
    {
        return Ingredient.fromItems(this.repairItem);
    }

    @Override
    public SoundEvent getSoundEvent()
    {
        return new SoundEvent(new ResourceLocation(equipSound));
    }

    @Override
    public float getToughness()
    {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockback;
    }
}
