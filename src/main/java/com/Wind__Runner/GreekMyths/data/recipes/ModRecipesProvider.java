package com.Wind__Runner.GreekMyths.data.recipes;

import com.Wind__Runner.GreekMyths.init.ModBlocks;
import com.Wind__Runner.GreekMyths.init.ModItems;
import com.Wind__Runner.GreekMyths.init.ModTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SingleItemRecipe;
import net.minecraft.item.crafting.StonecuttingRecipe;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.brewing.BrewingRecipe;

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
        cuttingRecipes(consumer);
    }

    private void craftingTableRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapelessRecipe(ModItems.BRONZE_INGOT.get()).addIngredient(ModItems.TIN_INGOT.get()).addIngredient(ModItems.COPPER_INGOT.get()).addCriterion("has_tin", hasItem(ModItems.TIN_INGOT.get())).addCriterion("has_copper", hasItem(ModItems.COPPER_INGOT.get())).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.SILVER_BLOCK.get())
                .key('#', ModItems.SILVER_INGOT.get())
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .addCriterion("has_item", hasItem(ModTags.Items.ORES_SILVER))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.COPPER_BLOCK.get())
                .key('#', ModItems.COPPER_INGOT.get())
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .addCriterion("has_item", hasItem(ModTags.Items.ORES_COPPER))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.TIN_BLOCK.get())
                .key('#', ModItems.TIN_INGOT.get())
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .addCriterion("has_item", hasItem(ModTags.Items.ORES_TIN))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.BRONZE_BLOCK.get())
                .key('#', ModItems.BRONZE_INGOT.get())
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .addCriterion("has_item", hasItem(ModTags.Items.INGOT_BRONZE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ModItems.GREEK_ARMOR_HELMET.get())
                .key('#', ModItems.BRONZE_INGOT.get())
                .patternLine("###")
                .patternLine("# #")
                .addCriterion("has_item", hasItem(ModTags.Items.INGOT_BRONZE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModItems.GREEK_ARMOR_CHESTPLATE.get())
                .key('#', ModItems.BRONZE_INGOT.get())
                .patternLine("# #")
                .patternLine("###")
                .patternLine("###")
                .addCriterion("has_item", hasItem(ModTags.Items.INGOT_BRONZE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModItems.GREEK_ARMOR_LEGGINGS.get())
                .key('#', ModItems.BRONZE_INGOT.get())
                .patternLine("###")
                .patternLine("# #")
                .patternLine("# #")
                .addCriterion("has_item", hasItem(ModTags.Items.INGOT_BRONZE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModItems.GREEK_ARMOR_BOOTS.get())
                .key('#', ModItems.BRONZE_INGOT.get())
                .patternLine("# #")
                .patternLine("# #")
                .addCriterion("has_item", hasItem(ModTags.Items.INGOT_BRONZE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ModBlocks.MUD_BRICKS.get(),4)
                .key('#', Blocks.TERRACOTTA)
                .patternLine("##")
                .patternLine("##")
                .addCriterion("has_item", hasItem(Blocks.TERRACOTTA))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.MUD_BRICK_SLAB.get(),6)
                .key('#', Blocks.TERRACOTTA)
                .patternLine("###")
                .addCriterion("has_item", hasItem(ModBlocks.MUD_BRICKS.get()))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.MUD_BRICK_STAIRS.get(),4)
                .key('#', Blocks.TERRACOTTA)
                .patternLine("#  ")
                .patternLine("## ")
                .patternLine("###")
                .addCriterion("has_item", hasItem(ModBlocks.MUD_BRICKS.get()))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.MUD_BRICK_WALL.get(),6)
                .key('#', Blocks.TERRACOTTA)
                .patternLine("###")
                .patternLine("###")
                .addCriterion("has_item", hasItem(ModBlocks.MUD_BRICKS.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ModBlocks.LIMESTONE_BRICKS.get(),4)
                .key('#', ModBlocks.LIMESTONE.get())
                .patternLine("##")
                .patternLine("##")
                .addCriterion("has_item", hasItem(ModBlocks.LIMESTONE.get()))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.LIMESTONE_BRICK_SLAB.get(),6)
                .key('#', ModBlocks.LIMESTONE_BRICKS.get())
                .patternLine("###")
                .addCriterion("has_item", hasItem(ModBlocks.LIMESTONE_BRICKS.get()))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.LIMESTONE_BRICK_STAIRS.get(),4)
                .key('#', ModBlocks.LIMESTONE_BRICKS.get())
                .patternLine("#  ")
                .patternLine("## ")
                .patternLine("###")
                .addCriterion("has_item", hasItem(ModBlocks.LIMESTONE_BRICKS.get()))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.LIMESTONE_PILLAR.get(),3)
                .key('#', ModBlocks.LIMESTONE_BRICKS.get())
                .patternLine("#")
                .patternLine("#")
                .patternLine("#")
                .addCriterion("has_item", hasItem(ModBlocks.LIMESTONE_BRICKS.get()))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.LIMESTONE_BRICK_WALL.get(),6)
                .key('#', ModBlocks.LIMESTONE_BRICKS.get())
                .patternLine("###")
                .patternLine("###")
                .addCriterion("has_item", hasItem(ModBlocks.LIMESTONE_BRICKS.get()))
                .build(consumer);
    }

    private void cuttingRecipes(Consumer<IFinishedRecipe> consumer) {
        SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ModBlocks.LIMESTONE_BRICKS.get()),ModBlocks.LIMESTONE_BRICK_SLAB.get(),2).addCriterion("has_limestone_brick", hasItem(ModBlocks.LIMESTONE_BRICKS.get())).build(consumer, "lime_stone_brick_slab_from_lime_stone_bricks_stonecutting");
        SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ModBlocks.LIMESTONE_BRICKS.get()),ModBlocks.LIMESTONE_PILLAR.get(),1).addCriterion("has_limestone_brick", hasItem(ModBlocks.LIMESTONE_BRICKS.get())).build(consumer, "lime_stone_pillar_from_lime_stone_bricks_stonecutting");
        SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ModBlocks.LIMESTONE_BRICKS.get()),ModBlocks.LIMESTONE_BRICK_STAIRS.get(),1).addCriterion("has_limestone_brick", hasItem(ModBlocks.LIMESTONE_BRICKS.get())).build(consumer, "lime_stone_brick_stairs_from_lime_stone_bricks_stonecutting");
        SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ModBlocks.LIMESTONE_BRICKS.get()),ModBlocks.LIMESTONE_BRICK_WALL.get(),1).addCriterion("has_limestone_brick", hasItem(ModBlocks.LIMESTONE_BRICKS.get())).build(consumer, "lime_stone_brick_wall_from_lime_stone_bricks_stonecutting");
        SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ModBlocks.LIMESTONE.get()),ModBlocks.LIMESTONE_BRICK_SLAB.get(),2).addCriterion("has_limestone", hasItem(ModBlocks.LIMESTONE.get())).build(consumer, "lime_stone_brick_slab_from_lime_stone_stonecutting");
        SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ModBlocks.LIMESTONE.get()),ModBlocks.LIMESTONE_PILLAR.get(),1).addCriterion("has_limestone", hasItem(ModBlocks.LIMESTONE.get())).build(consumer, "lime_stone_pillar_from_lime_stone_stonecutting");
        SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ModBlocks.LIMESTONE.get()),ModBlocks.LIMESTONE_BRICK_STAIRS.get(),1).addCriterion("has_limestone", hasItem(ModBlocks.LIMESTONE.get())).build(consumer, "lime_stone_brick_stairs_from_lime_stone_stonecutting");
        SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ModBlocks.LIMESTONE.get()),ModBlocks.LIMESTONE_BRICK_WALL.get(),1).addCriterion("has_limestone", hasItem(ModBlocks.LIMESTONE.get())).build(consumer, "lime_stone_brick_wall_from_lime_stone_stonecutting");
        SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ModBlocks.LIMESTONE.get()),ModBlocks.LIMESTONE_BRICKS.get(),1).addCriterion("has_limestone", hasItem(ModBlocks.LIMESTONE.get())).build(consumer, "lime_stone_bricks_from_lime_stone_stonecutting");

        SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ModBlocks.MUD_BRICKS.get()),ModBlocks.MUD_BRICK_SLAB.get(),2).addCriterion("has_limestone", hasItem(ModBlocks.MUD_BRICKS.get())).build(consumer, "mud_brick_slab_from_mud_bricks_stonecutting");
        SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ModBlocks.MUD_BRICKS.get()),ModBlocks.MUD_BRICK_STAIRS.get(),1).addCriterion("has_limestone", hasItem(ModBlocks.MUD_BRICKS.get())).build(consumer, "mud_brick_stairs_from_mud_bricks_stonecutting");
        SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ModBlocks.MUD_BRICKS.get()),ModBlocks.MUD_BRICK_WALL.get(),1).addCriterion("has_limestone", hasItem(ModBlocks.MUD_BRICKS.get())).build(consumer, "mud_brick_wall_from_mud_bricks_stonecutting");
    }

    private void smeltingRecipes(Consumer<IFinishedRecipe> consumer) {
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ModBlocks.SILVER_ORE.get().asItem()), ModItems.SILVER_INGOT.get(), 0.35F, 200)
                .addCriterion("has_item", hasItem(ModTags.Items.ORES_SILVER))
                .build(consumer);
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ModBlocks.COPPER_ORE.get().asItem()), ModItems.COPPER_INGOT.get(), 0.35F, 200)
                .addCriterion("has_item", hasItem(ModTags.Items.ORES_COPPER))
                .build(consumer);
    }
}