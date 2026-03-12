package net.raccoon.will.winestead.common.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.raccoon.will.winestead.common.blocks.blockentity.AgingBarrelBlockEntity;
import net.raccoon.will.winestead.registry.WSSounds;
import net.raccoon.will.winestead.registry.WSTags;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Map;

import static net.raccoon.will.winestead.registry.WSBlockEntities.AGING_BARREL_BE;

public class AgingBarrelBlock extends BaseEntityBlock {
    public static final MapCodec<AgingBarrelBlock> CODEC = simpleCodec(AgingBarrelBlock::new);
    public static final BooleanProperty TAPPED = BooleanProperty.create("tapped");
    private static final Map<Direction, VoxelShape> SHAPES;
    public static final EnumProperty<Direction> FACING;

    public AgingBarrelBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TAPPED, false));
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        Direction clickedDirection = hitResult.getDirection();
        ItemStack held = player.getItemInHand(hand);

        if (blockEntity instanceof AgingBarrelBlockEntity barrel) {
            if (clickedDirection == state.getValue(FACING)) { // always get the front face of the block

                // tap blockstate
                if (!state.getValue(TAPPED) && player.isHolding(Items.COPPER_INGOT)) {
                    level.setBlockAndUpdate(pos, state.setValue(TAPPED, true));
                    level.playLocalSound(pos, WSSounds.BARREL_TAP.get(), SoundSource.BLOCKS, 1, 0.85f, false);
                    return InteractionResult.SUCCESS;
                }

                // cork blockstate
                if (state.getValue(TAPPED) && player.isHolding(Items.BIRCH_BUTTON)) {
                    level.setBlockAndUpdate(pos, state.setValue(TAPPED, false));
                    level.playLocalSound(pos, WSSounds.BARREL_SEAL.get(), SoundSource.BLOCKS, 1, 1, false);
                    return InteractionResult.SUCCESS;
                }
            }

            // insert item
            if (barrel.isEmpty() && held.is(WSTags.Items.AGEABLE_ITEMS)) {
                ItemStack insert = held.copyWithCount(1);
                barrel.setTheItem(insert);
                held.shrink(1);
                return InteractionResult.SUCCESS;
            }

            // extract item
            if (held.isEmpty() && !barrel.isEmpty()) {
                player.setItemInHand(hand, barrel.getTheItem());
                barrel.setTheItem(ItemStack.EMPTY);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }


    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity) {
        return createTickerHelper(blockEntity, AGING_BARREL_BE.get(), AgingBarrelBlockEntity::tick);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NonNull BlockPos blockPos, @NonNull BlockState blockState) {
        return AGING_BARREL_BE.get().create(blockPos, blockState);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.get(state.getValue(FACING));
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TAPPED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    static {
        FACING = BlockStateProperties.HORIZONTAL_FACING;
        SHAPES = Shapes.rotateHorizontal(Shapes.or(
                Block.box(1.0F, 2.0F, 0.0, 15.0F, 16.0F, 16.0F), //barrel box
                Block.box(0.0F, 0.0F, 1.0F, 16.0F, 4.0F, 3.0F), //front base box
                Block.box(0.0F, 0.0F, 13.0F, 16.0F, 4.0F, 15.0F) // back base box
        ));
    }
}