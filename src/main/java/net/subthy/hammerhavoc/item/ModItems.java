package net.subthy.hammerhavoc.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.subthy.hammerhavoc.HammerHavoc;
import net.subthy.hammerhavoc.item.custom.HammerItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HammerHavoc.MOD_ID);

    public static final RegistryObject<Item> Hammer_Handle = ITEMS.register("hammer_handle",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> Iron_Hammer_Head = ITEMS.register("iron_hammer_head",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> Gold_Hammer_Head = ITEMS.register("gold_hammer_head",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> Diamond_Hammer_Head = ITEMS.register("diamond_hammer_head",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> Netherite_Hammer_Head = ITEMS.register("netherite_hammer_head",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> Iron_Hammer = ITEMS.register("iron_hammer",
            () -> new HammerItem(ModToolTiers.IRON_HAMMER, 1,  -2.8f, new Item.Properties().durability(251)));

    public static final RegistryObject<Item> Gold_Hammer = ITEMS.register("gold_hammer",
            () -> new HammerItem(ModToolTiers.GOLD_HAMMER, -1f,  -2.8f, new Item.Properties().durability(32)));

    public static final RegistryObject<Item> Diamond_Hammer = ITEMS.register("diamond_hammer",
            () -> new HammerItem(ModToolTiers.DIAMOND_HAMMER, 1,  -2.8f, new Item.Properties().durability(1561)));

    public static final RegistryObject<Item> Netherite_Hammer = ITEMS.register("netherite_hammer",
            () -> new HammerItem(ModToolTiers.NETHERITE_HAMMER, 1,  -3.2f, new Item.Properties().durability(2031)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
