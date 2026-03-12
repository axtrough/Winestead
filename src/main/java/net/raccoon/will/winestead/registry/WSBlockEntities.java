package net.raccoon.will.winestead.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.raccoon.will.winestead.Winestead;
import net.raccoon.will.winestead.common.blocks.blockentity.AgingBarrelBlockEntity;
import net.raccoon.will.winestead.common.blocks.blockentity.WineBarrelBlockEntity;

import java.util.function.Supplier;

public class WSBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Winestead.MODID);

//    public static final Supplier<BlockEntityType<TreeTapperBlockEntity>> TREE_TAPPER = BLOCK_ENTITY_TYPES.register("tree_tapper",
//            () -> new BlockEntityType<>(TreeTapperBlockEntity::new, VBlocks.TREE_TAPPER.get()));

    public static final Supplier<BlockEntityType<AgingBarrelBlockEntity>> AGING_BARREL_BE = BLOCK_ENTITY_TYPES.register("aging_barrel",
            () -> new BlockEntityType<>(AgingBarrelBlockEntity::new, WSBlocks.AGING_BARREL.get()));

    public static final Supplier<BlockEntityType<WineBarrelBlockEntity>> WINE_BARREL_BE = BLOCK_ENTITY_TYPES.register("wine_barrel",
            () -> new BlockEntityType<>(WineBarrelBlockEntity::new, WSBlocks.WINE_BARREL.get()));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITY_TYPES.register(eventBus);
    }
}
