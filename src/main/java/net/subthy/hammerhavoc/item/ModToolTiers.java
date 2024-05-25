package net.subthy.hammerhavoc.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.subthy.hammerhavoc.HammerHavoc;
import net.subthy.hammerhavoc.util.ModTags;

import java.util.List;

public class ModToolTiers {
    public static final Tier IRON_HAMMER = TierSortingRegistry.registerTier(
            new ForgeTier(2, 250, 3f, 7f, 14,
                    ModTags.Blocks.NEEDS_HAMMER_TOOL, () -> Ingredient.of(Items.IRON_INGOT)),
            new ResourceLocation(HammerHavoc.MOD_ID, "iron_hammer"), List.of(Tiers.IRON), List.of());

    public static final Tier GOLD_HAMMER = TierSortingRegistry.registerTier(
            new ForgeTier(0, 32, 9f, 8f, 22,
                    ModTags.Blocks.NEEDS_HAMMER_TOOL, () -> Ingredient.of(Items.GOLD_INGOT)),
            new ResourceLocation(HammerHavoc.MOD_ID, "gold_hammer"), List.of(Tiers.GOLD), List.of());

    public static final Tier DIAMOND_HAMMER = TierSortingRegistry.registerTier(
            new ForgeTier(3, 1561, 5f, 8f, 10,
                    ModTags.Blocks.NEEDS_HAMMER_TOOL, () -> Ingredient.of(Items.DIAMOND)),
            new ResourceLocation(HammerHavoc.MOD_ID, "diamond_hammer"), List.of(Tiers.DIAMOND), List.of());

    public static final Tier NETHERITE_HAMMER = TierSortingRegistry.registerTier(
            new ForgeTier(4, 2031, 6f, 9f, 16,
                    ModTags.Blocks.NEEDS_HAMMER_TOOL, () -> Ingredient.of(Items.NETHERITE_INGOT)),
            new ResourceLocation(HammerHavoc.MOD_ID, "netherite_hammer"), List.of(Tiers.NETHERITE), List.of());

}
