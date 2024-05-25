package net.subthy.hammerhavoc.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.subthy.hammerhavoc.HammerHavoc;

public class ModTags {
    public static class Items {

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(HammerHavoc.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));

        }
    }

    public static class Blocks {

        public static final TagKey<Block> NEEDS_HAMMER_TOOL = tag("needs_alexandrite_tool");



        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(HammerHavoc.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }
}
