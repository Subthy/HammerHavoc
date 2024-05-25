package net.subthy.hammerhavoc.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.subthy.hammerhavoc.HammerHavoc;
import net.subthy.hammerhavoc.item.ModItems;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> future,
                               CompletableFuture<TagLookup<Block>> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, future, completableFuture, HammerHavoc.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        // Add Item Tags here
        this.tag(ItemTags.TOOLS)
                .add(ModItems.Diamond_Hammer.get(),
                        ModItems.Netherite_Hammer.get());

        this.tag(ItemTags.PICKAXES)
                .add(ModItems.Diamond_Hammer.get(),
                        ModItems.Netherite_Hammer.get());

    }

    @Override
    public String getName() {
        return "Item Tags";
    }
}
