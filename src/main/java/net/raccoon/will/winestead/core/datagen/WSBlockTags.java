package net.raccoon.will.winestead.core.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.raccoon.will.winestead.Winestead;
import net.raccoon.will.winestead.registry.WSBlocks;

import java.util.concurrent.CompletableFuture;

public class WSBlockTags extends BlockTagsProvider {
    public WSBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Winestead.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
       tag(BlockTags.MINEABLE_WITH_AXE)
               .add(WSBlocks.AGING_BARREL.get())
               .add(WSBlocks.WOODEN_VAT.get());


    }
}