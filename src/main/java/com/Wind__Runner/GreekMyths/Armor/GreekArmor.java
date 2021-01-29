package com.Wind__Runner.GreekMyths.Armor;

import com.Wind__Runner.GreekMyths.init.ModArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;


public class GreekArmor extends ArmorItem {

    public GreekArmor(EquipmentSlotType slot, Properties builderIn) {
        super(ModArmorMaterial.GreekArmor, slot, builderIn);
    }
}
