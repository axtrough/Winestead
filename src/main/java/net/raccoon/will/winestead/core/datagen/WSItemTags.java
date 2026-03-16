package net.raccoon.will.winestead.core.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import net.raccoon.will.winestead.Winestead;
import net.raccoon.will.winestead.registry.WSItems;
import net.raccoon.will.winestead.registry.WSTags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class WSItemTags extends ItemTagsProvider {
    public WSItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Winestead.MODID);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        tag(WSTags.Items.AGEABLE_ITEMS)
                .add(WSItems.WINE_ITEM.get())
                .add(WSItems.BLAZED_WINE.get());

        tag(WSTags.Items.VALID_GRAPE)
                .add(WSItems.RED_GRAPES.get());

        tag(WSTags.Items.VALID_INGREDIENTS)
                .add(Items.FEATHER)
                .add(Items.BONE)
                .add(Items.BLAZE_ROD);

    }
}