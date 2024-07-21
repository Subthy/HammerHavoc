package net.subthy.hammerhavoc.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.subthy.hammerhavoc.HammerHavoc;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HammerHavoc.MOD_ID);

    public static final RegistryObject<CreativeModeTab> HAMMER_TAB = CREATIVE_MODE_TABS.register("course_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.Iron_Hammer.get()))
                    .title(Component.translatable("creativetab.hammer_tab"))
                    .displayItems((displayParameters, output) -> {
                        output.accept(ModItems.Iron_Hammer.get());
                        output.accept(ModItems.Gold_Hammer.get());
                        output.accept(ModItems.Diamond_Hammer.get());
                        output.accept(ModItems.Netherite_Hammer.get());
                        output.accept(ModItems.Hammer_Handle.get());
                        output.accept(ModItems.Iron_Hammer_Head.get());
                        output.accept(ModItems.Gold_Hammer_Head.get());
                        output.accept(ModItems.Diamond_Hammer_Head.get());

                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
