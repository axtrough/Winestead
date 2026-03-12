package net.raccoon.will.winestead.registry;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.raccoon.will.winestead.Winestead;
import net.raccoon.will.winestead.common.items.BlazedGrapesItem;
import net.raccoon.will.winestead.common.items.WineItem;

public class WSItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Winestead.MODID);

    public static final DeferredItem<Item> WINE_ITEM = ITEMS.registerItem("wine_item", WineItem::new, Item.Properties::new);

    public static final DeferredItem<Item> RED_GRAPES = ITEMS.registerSimpleItem("red_grapes", properties -> properties.food(WSFoods.Red_Grapes));
    public static final DeferredItem<Item> WHITE_GRAPES = ITEMS.registerSimpleItem("white_grapes", properties -> properties.food(WSFoods.White_Grapes));

    public static final DeferredItem<Item> BLAZED_GRAPES = ITEMS.registerItem("blazed_grapes", BlazedGrapesItem::new, Item.Properties::new);
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}