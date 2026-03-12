package net.raccoon.will.winestead.core.datagen;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import static net.minecraft.client.data.models.BlockModelGenerators.*;
import static net.raccoon.will.winestead.common.blocks.AgingBarrelBlock.TAPPED;

public class WSModelGenerators {



    public void createAgingBarrel(BlockModelGenerators blockModels, Block block) {
        MultiVariant normal = plainVariant(ModelLocationUtils.getModelLocation(block));
        MultiVariant tapped = plainVariant(ModelLocationUtils.getModelLocation(block, "_tapped"));

        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                .with(createBooleanModelDispatch(TAPPED, tapped, normal))
                .with(PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING)
                        .select(Direction.NORTH, NOP)
                        .select(Direction.EAST, Y_ROT_90)
                        .select(Direction.SOUTH, Y_ROT_180)
                        .select(Direction.WEST, Y_ROT_270)
                )
        );
    }

    public void existingModelHorizontal(BlockModelGenerators blockModels, Block block, Identifier modelLoc) {
        blockModels.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(block, new MultiVariant(WeightedList.of(new Variant(modelLoc))))
                        .with(PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING)
                                .select(Direction.NORTH, NOP)
                                .select(Direction.EAST, Y_ROT_90)
                                .select(Direction.SOUTH, Y_ROT_180)
                                .select(Direction.WEST, Y_ROT_270)));
        existingBlockItemModel(blockModels, block, modelLoc);
    }

    public void existingBlockItemModel(BlockModelGenerators blockModels, Block block, Identifier modelLoc) {
        blockModels.itemModelOutput.accept(
                block.asItem(),
                ItemModelUtils.plainModel(modelLoc));
    }

    public void existingItemModel(BlockModelGenerators blockModels, Item item, Identifier modelLoc) {
        blockModels.itemModelOutput.accept(
                item.asItem(),
                ItemModelUtils.plainModel(modelLoc));
    }
}