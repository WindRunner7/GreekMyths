package com.Wind__Runner.GreekMyths.data.loot;

import com.Wind__Runner.GreekMyths.init.ModItems;
import net.minecraft.data.loot.ChestLootTables;
import net.minecraft.loot.*;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;

import java.util.function.BiConsumer;

public class ModChestLootTables extends ChestLootTables {
    public static final ResourceLocation CHESTS_GREEK_CAMP = register("greekmyths:chests/greek_camp");
    public static final ResourceLocation CHESTS_LABYRINTH = register("greekmyths:chests/labyrinth");

    private static ResourceLocation register(String id) {
        return new ResourceLocation(id);
    }

    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
        LootTable.Builder builder = LootTable.builder();
        consumer.accept(CHESTS_GREEK_CAMP, addGreekLoot(builder));
        consumer.accept(CHESTS_LABYRINTH, addGreekLoot(builder));

    }

    private static LootTable.Builder addGreekLoot(LootTable.Builder builder) {
        builder.addLootPool(LootPool.builder()
                .rolls(RandomValueRange.of(1, 4))
                .addEntry(EmptyLootEntry.func_216167_a()
                        .weight(20)
                )
                .addEntry(ItemLootEntry.builder(ModItems.COPPER_INGOT.get())
                        .weight(35)
                        .acceptFunction(SetCount.builder(RandomValueRange.of(1, 4)))
                )
                .addEntry(ItemLootEntry.builder(ModItems.SILVER_INGOT.get())
                        .weight(35)
                        .acceptFunction(SetCount.builder(RandomValueRange.of(1, 4)))
                )
                .addEntry(ItemLootEntry.builder(ModItems.GREEK_ARMOR_CHESTPLATE.get())
                        .weight(15)
                )
                .addEntry(ItemLootEntry.builder(ModItems.GREEK_ARMOR_LEGGINGS.get())
                        .weight(15)
                )
                .addEntry(ItemLootEntry.builder(ModItems.GREEK_ARMOR_BOOTS.get())
                        .weight(15)
                )
                .addEntry(ItemLootEntry.builder(ModItems.GREEK_ARMOR_HELMET.get())
                        .weight(15)
                )
        );
        return builder;
    }
}
