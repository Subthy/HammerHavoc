package net.subthy.hammerhavoc.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.subthy.hammerhavoc.HammerHavoc;
import net.subthy.hammerhavoc.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, HammerHavoc.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.Hammer_Handle);
        simpleItem(ModItems.Iron_Hammer_Head);
        simpleItem(ModItems.Gold_Hammer_Head);
        simpleItem(ModItems.Diamond_Hammer_Head);
        simpleItem(ModItems.Netherite_Hammer_Head);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(HammerHavoc.MOD_ID,"item/" + item.getId().getPath()));
    }
}
