package net.raccoon.will.winestead.core.datagen;

import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.raccoon.will.winestead.Winestead;

import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = Winestead.MODID)
public class Datagen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(WSModels::new);
        event.createProvider(WSItemTags::new);

        event.createProvider(WSBlockTags::new);
        event.createProvider((WSRecipes.Runner::new));

        event.createProvider((output, lookupProvider) -> new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(
                        WSBlockLoot.VBlockSub::new,
                        LootContextParamSets.BLOCK
/*                 ),
               new LootTableProvider.SubProviderEntry(
                        BEntityLoot.VEntitySub::new,
                        LootContextParamSets.ENTITY

 */
                )), lookupProvider
        ));
    }
}