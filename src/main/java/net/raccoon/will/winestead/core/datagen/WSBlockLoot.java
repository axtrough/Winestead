package net.raccoon.will.winestead.core.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.raccoon.will.winestead.Winestead;
import net.raccoon.will.winestead.registry.WSBlocks;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class WSBlockLoot extends LootTableProvider {
    public WSBlockLoot(PackOutput output, Set<ResourceKey<LootTable>> requiredTables, List<SubProviderEntry> subProviders, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, requiredTables, subProviders, registries);
    }

    public static class VBlockSub extends BlockLootSubProvider {
        public VBlockSub(HolderLookup.Provider lookupProvider) {
            super(Set.of(), FeatureFlags.DEFAULT_FLAGS, lookupProvider);
        }

        @Override
        protected void generate() {
            this.dropSelf(WSBlocks.AGING_BARREL.get());
            this.dropSelf(WSBlocks.WOODEN_VAT.get());
            this.dropSelf(WSBlocks.WINE_BARREL.get());

        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return BuiltInRegistries.BLOCK.entrySet().stream().filter(e ->
                    e.getKey().identifier().getNamespace().equals(Winestead.MODID)).map(Map.Entry::getValue).toList();
        }
    }
}