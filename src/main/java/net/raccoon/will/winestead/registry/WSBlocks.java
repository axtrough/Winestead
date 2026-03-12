package net.raccoon.will.winestead.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.raccoon.will.winestead.Winestead;
import net.raccoon.will.winestead.common.blocks.AgingBarrelBlock;
import net.raccoon.will.winestead.common.blocks.WoodenVatBlock;
import net.raccoon.will.winestead.common.blocks.WineBarrelBlock;
import net.raccoon.will.winestead.util.RegistryUtil;

import static net.raccoon.will.winestead.util.RegistryUtil.registerBlock;


public class WSBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Winestead.MODID);



//    public static final DeferredBlock<Block> TREE_TAPPER = registerBlock("tree_tapper", registryName ->
//            new TreeTapperBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).setId(ResourceKey.create(Registries.BLOCK, registryName))));

    public static final DeferredBlock<Block> AGING_BARREL = registerBlock("aging_barrel", registryName ->
            new AgingBarrelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).setId(ResourceKey.create(Registries.BLOCK, registryName))));


    public static final DeferredBlock<Block> WOODEN_VAT = RegistryUtil.registerBlock("wooden_vat", registryName ->
            new WoodenVatBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).setId(ResourceKey.create(Registries.BLOCK, registryName))));

    public static final DeferredBlock<Block> WINE_BARREL = registerBlock("wine_barrel", registryName ->
            new WineBarrelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).setId(ResourceKey.create(Registries.BLOCK, registryName))));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
