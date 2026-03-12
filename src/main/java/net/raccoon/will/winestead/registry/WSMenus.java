package net.raccoon.will.winestead.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.raccoon.will.winestead.Winestead;
import net.raccoon.will.winestead.common.menus.WineBarrelMenu;

import java.util.function.Supplier;

public class WSMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, Winestead.MODID);

    public static final Supplier<MenuType<WineBarrelMenu>> WINE_BARREL = MENUS.register("wine_barrel", () -> new MenuType<>(WineBarrelMenu::new, FeatureFlags.DEFAULT_FLAGS));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
