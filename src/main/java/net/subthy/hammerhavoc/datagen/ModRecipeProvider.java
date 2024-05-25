package net.subthy.hammerhavoc.datagen;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.subthy.hammerhavoc.HammerHavoc;
import net.subthy.hammerhavoc.item.ModItems;

import java.util.List;
import java.util.function.Consumer;

import static net.subthy.hammerhavoc.HammerHavoc.MOD_ID;
import static org.openjdk.nashorn.internal.runtime.Debug.id;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {


    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.Hammer_Handle.get())
                .pattern("  C")
                .pattern(" A ")
                .pattern("C  ")
                .define('A', Items.STICK)
                .define('C', Items.IRON_NUGGET)
                .unlockedBy("has_stick", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Items.STICK).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.Iron_Hammer_Head.get())
                .pattern("CCC")
                .pattern("C C")
                .define('C', Items.IRON_BLOCK)
                .unlockedBy("has_iron", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Items.IRON_INGOT).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.Gold_Hammer_Head.get())
                .pattern("CCC")
                .pattern("CAC")
                .define('C', Items.GOLD_INGOT)
                .define('A', ModItems.Iron_Hammer_Head.get())
                .unlockedBy("has_gold", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Items.GOLD_INGOT).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.Diamond_Hammer_Head.get())
                .pattern("CCC")
                .pattern("CAC")
                .define('C', Items.DIAMOND)
                .define('A', ModItems.Iron_Hammer_Head.get())
                .unlockedBy("has_diamond", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Items.DIAMOND).build()))
                .save(pWriter,"diamond_hammer_head_2");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.Diamond_Hammer_Head.get())
                .pattern("CCC")
                .pattern("CAC")
                .define('C', Items.DIAMOND)
                .define('A', ModItems.Gold_Hammer_Head.get())
                .unlockedBy("has_diamond", inventoryTrigger(ItemPredicate.Builder.item().
                        of(Items.DIAMOND).build()))
                .save(pWriter);


        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(ModItems.Diamond_Hammer.get()), Ingredient.of(Items.NETHERITE_INGOT),
                        RecipeCategory.MISC, ModItems.Netherite_Hammer.get())
                .unlocks(getHasName(Items.NETHERITE_INGOT), has(Tags.Items.INGOTS_NETHERITE)).save(pWriter, id("netherite"));
    }



    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer,
                                     List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime,
                            pCookingSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }


}




