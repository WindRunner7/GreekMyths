package com.Wind__Runner.GreekMyths.data.recipes;

import com.Wind__Runner.GreekMyths.init.ModBlocks;
import com.Wind__Runner.GreekMyths.init.ModItems;
import com.Wind__Runner.GreekMyths.init.ModTags;
import net.minecraft.data.*;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Consumer;

public class ModRecipesProvider extends RecipeProvider {
    public ModRecipesProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    public String getName() {
        return "Greek Myths - Recipes";
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        craftingTableRecipes(consumer);
        smeltingRecipes(consumer);

    }

    private void craftingTableRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.SILVER_BLOCK.get())
                .key('#', ModItems.SILVER_INGOT.get())
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .addCriterion("has_item", hasItem(ModTags.Items.ORES_SILVER))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModItems.GREEK_ARMOR_HELMET.get())
                .key('#', ModItems.SILVER_INGOT.get())
                .patternLine("###")
                .patternLine("# #")
                .addCriterion("has_item", hasItem(ModTags.Items.ORES_SILVER))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModItems.GREEK_ARMOR_CHESTPLATE.get())
                .key('#', ModItems.SILVER_INGOT.get())
                .patternLine("# #")
                .patternLine("###")
                .patternLine("###")
                .addCriterion("has_item", hasItem(ModTags.Items.ORES_SILVER))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModItems.GREEK_ARMOR_LEGGINGS.get())
                .key('#', ModItems.SILVER_INGOT.get())
                .patternLine("###")
                .patternLine("# #")
                .patternLine("# #")
                .addCriterion("has_item", hasItem(ModTags.Items.ORES_SILVER))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModItems.GREEK_ARMOR_BOOTS.get())
                .key('#', ModItems.SILVER_INGOT.get())
                .patternLine("# #")
                .patternLine("# #")
                .addCriterion("has_item", hasItem(ModTags.Items.ORES_SILVER))
                .build(consumer);

    }

    private void smeltingRecipes(Consumer<IFinishedRecipe> consumer) {
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ModBlocks.SILVER_ORE.get().asItem()), ModItems.SILVER_INGOT.get(), 0.35F, 200)
                .addCriterion("has_item", hasItem(ModTags.Items.ORES_SILVER))
                .build(consumer);
    }
}