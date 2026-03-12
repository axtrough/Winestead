package net.raccoon.will.winestead.util;

import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.raccoon.will.winestead.registry.WSItems;

import java.util.function.Function;

import static net.raccoon.will.winestead.registry.WSBlocks.BLOCKS;

public class RegistryUtil {

    //Block Registry | Block Items
    public static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<Identifier, ? extends T> func, boolean registerItem) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, func);
        if (registerItem) {
            WSItems.ITEMS.registerSimpleBlockItem(name, toReturn);
        }
        return toReturn;
    }

    public static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<Identifier, ? extends T> func) {
        return registerBlock(name, func, true);
    }


}