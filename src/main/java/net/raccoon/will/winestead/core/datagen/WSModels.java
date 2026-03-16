package net.raccoon.will.winestead.core.datagen;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.raccoon.will.winestead.Winestead;
import net.raccoon.will.winestead.registry.WSBlocks;
import net.raccoon.will.winestead.registry.WSItems;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.stream.Stream;

public class WSModels extends ModelProvider {
    WSModelGenerators wineModels = new WSModelGenerators();

    public WSModels(PackOutput output) {
        super(output, Winestead.MODID);
    }

    @Override
    protected void registerModels(@NotNull BlockModelGenerators blockModels, @NotNull ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(WSItems.WINE_ITEM.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(WSItems.BLAZED_WINE.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(WSItems.RED_GRAPES.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(WSItems.WHITE_GRAPES.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(WSItems.BLAZED_GRAPES.get(), ModelTemplates.FLAT_ITEM);

        wineModels.existingModelHorizontal(blockModels, WSBlocks.WINE_BARREL.get(), Winestead.resLoc("block/aging_barrel"));
        wineModels.createAgingBarrel(blockModels, WSBlocks.AGING_BARREL.get());
//        wineModels.createAgingBarrel(blockModels, WSBlocks.WINE_BARREL.get());



    }

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return super.getKnownBlocks()
                .filter(holder -> !ignoredBlocks.contains(holder.value()));
    }

    private static final Set<Block> ignoredBlocks = Set.of(
            WSBlocks.WOODEN_VAT.get()
    );
}