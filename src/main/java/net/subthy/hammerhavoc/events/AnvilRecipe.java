package net.subthy.hammerhavoc.events;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.subthy.hammerhavoc.item.ModItems;

import static net.subthy.hammerhavoc.HammerHavoc.MOD_ID;

public class AnvilRecipe {

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public class ModAnvilRecipes {

        @SubscribeEvent
        public static void onAnvilUpdate(AnvilUpdateEvent event) {
            ItemStack leftStack = event.getLeft();
            ItemStack rightStack = event.getRight();

            if (leftStack.getItem() == ModItems.Iron_Hammer_Head.get() && rightStack.getItem() == ModItems.Hammer_Handle.get()) {
                ItemStack resultStack = new ItemStack(ModItems.Iron_Hammer.get());
                event.setOutput(resultStack);
                event.setCost(1);

            }
            if (leftStack.getItem() == ModItems.Gold_Hammer_Head.get() && rightStack.getItem() == ModItems.Hammer_Handle.get()) {
                ItemStack resultStack = new ItemStack(ModItems.Gold_Hammer.get());
                event.setOutput(resultStack);
                event.setCost(1);
            }
            if (leftStack.getItem() == ModItems.Diamond_Hammer_Head.get() && rightStack.getItem() == ModItems.Hammer_Handle.get()) {
                ItemStack resultStack = new ItemStack(ModItems.Diamond_Hammer.get());
                event.setOutput(resultStack);
                event.setCost(1);
            }
        }
    }
}