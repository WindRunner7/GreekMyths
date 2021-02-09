package com.Wind__Runner.GreekMyths.init;

import com.Wind__Runner.GreekMyths.Armor.GreekArmor;
import com.Wind__Runner.GreekMyths.GreekMyths;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;


public class ModItems {

    public static final RegistryObject<Item> SILVER_INGOT = Registration.ITEMS.register("silver_ingot", () ->
            new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
    public static final RegistryObject<Item> COPPER_INGOT = Registration.ITEMS.register("copper_ingot", () ->
            new Item(new Item.Properties().group(ItemGroup.MATERIALS)));

    public static final RegistryObject<Item> GREEK_ARMOR_HELMET = Registration.ITEMS.register("greekarmor_helmet", () ->
            new GreekArmor(EquipmentSlotType.HEAD, new Item.Properties().group(GreekMyths.ITEM_GROUP)));
    public static final RegistryObject<Item> GREEK_ARMOR_CHESTPLATE = Registration.ITEMS.register("greekarmor_chestplate", () ->
            new GreekArmor(EquipmentSlotType.CHEST, new Item.Properties().group(GreekMyths.ITEM_GROUP)));
    public static final RegistryObject<Item> GREEK_ARMOR_LEGGINGS = Registration.ITEMS.register("greekarmor_leggings", () ->
            new GreekArmor(EquipmentSlotType.LEGS, new Item.Properties().group(GreekMyths.ITEM_GROUP)));
    public static final RegistryObject<Item> GREEK_ARMOR_BOOTS = Registration.ITEMS.register("greekarmor_boots", () ->
            new GreekArmor(EquipmentSlotType.FEET, new Item.Properties().group(GreekMyths.ITEM_GROUP)));

    public static final RegistryObject<Item> MYTHIC_ARMOR_HELMET = Registration.ITEMS.register("mythicarmor_helmet", () ->
            new ArmorItem(ModArmorMaterial.MythicArmor, EquipmentSlotType.HEAD, new Item.Properties().group(GreekMyths.ITEM_GROUP)));
    public static final RegistryObject<Item> MYTHIC_ARMOR_CHESTPLATE = Registration.ITEMS.register("mythicarmor_chestplate", () ->
            new ArmorItem(ModArmorMaterial.MythicArmor, EquipmentSlotType.CHEST, new Item.Properties().group(GreekMyths.ITEM_GROUP)));
    public static final RegistryObject<Item> MYTHIC_ARMOR_LEGGINGS = Registration.ITEMS.register("mythicarmor_leggings", () ->
            new ArmorItem(ModArmorMaterial.MythicArmor, EquipmentSlotType.LEGS, new Item.Properties().group(GreekMyths.ITEM_GROUP)));
    public static final RegistryObject<Item> MYTHIC_ARMOR_BOOTS = Registration.ITEMS.register("mythicarmor_boots", () ->
            new ArmorItem(ModArmorMaterial.MythicArmor, EquipmentSlotType.FEET, new Item.Properties().group(GreekMyths.ITEM_GROUP)));

    public static void register() {
    }

}
