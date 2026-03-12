package net.raccoon.will.winestead.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.raccoon.will.winestead.Winestead;

import java.util.function.Supplier;

public class WSCreativeTab {
    public static DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Winestead.MODID);

    public static final Supplier<CreativeModeTab> WINESTEAD_TAB = CREATIVE_MODE_TAB.register("winestead_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(WSBlocks.AGING_BARREL.asItem()))
            .title(Component.translatable("itemGroup.winestead_tab"))
            .displayItems((parameters, output) -> {
                output.accept(WSBlocks.AGING_BARREL);
                output.accept(WSBlocks.WOODEN_VAT);
                output.accept(WSItems.WINE_ITEM);
                output.accept(WSItems.BLAZED_GRAPES);

                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}