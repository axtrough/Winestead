package net.raccoon.will.winestead.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.raccoon.will.winestead.registry.WSTags;

public class WoodenVatBlock extends Block  {
    public static final IntegerProperty CONTENTS_LEVEL = IntegerProperty.create("contents_level", 0, 4);
    public static final IntegerProperty STOMPED_LEVEL = IntegerProperty.create("stomped_level", 0, 4);
    public static final BooleanProperty IS_FULL = BooleanProperty.create("is_full");
    private static final VoxelShape SHAPE;
    public static int MAX_CONTENTS = 4;
    public static int MAX_STOMPED = 4;

    public WoodenVatBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(CONTENTS_LEVEL, 0).setValue(STOMPED_LEVEL, 0).setValue(IS_FULL, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CONTENTS_LEVEL, STOMPED_LEVEL, IS_FULL);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        int filledLevel = state.getValue(CONTENTS_LEVEL);
        ItemStack held = player.getItemInHand(hand);

        if (filledLevel < MAX_CONTENTS && held.is(WSTags.Items.VALID_GRAPE)) {
            int newFilled = filledLevel + 1;
            BlockState newState = state.setValue(CONTENTS_LEVEL, newFilled);

            // set IS_FULL when its at full capacity
            if (newFilled == MAX_CONTENTS) {
                newState = newState.setValue(IS_FULL, true);
                player.sendOverlayMessage(Component.literal("too full. go stomp"));
            } else {
                player.sendOverlayMessage(Component.literal("adding grape " + newFilled));
            }

            level.setBlockAndUpdate(pos, newState);
            if (!player.isCreative()) {
                held.shrink(1);
            }
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.CONSUME;
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
        if (!(entity instanceof Player player)) return;
        if (level.isClientSide()) return;
        if (fallDistance < 0.5F) return;

        int filledLevel = state.getValue(CONTENTS_LEVEL);
        int stompedLevel = state.getValue(STOMPED_LEVEL);
        boolean isFull = state.getValue(IS_FULL);

        // has max grapes, now increase stomp level
        if (isFull && filledLevel > 0) {
            int newFill = filledLevel - 1;
            int newStomp = stompedLevel + 1;
            BlockState newState = state.setValue(STOMPED_LEVEL, newStomp).setValue(CONTENTS_LEVEL, newFill);
            level.setBlockAndUpdate(pos, newState);
            player.sendOverlayMessage(Component.literal("stomping grape " + newFill));
            return;
        }

        // stomp stomp done
        if (stompedLevel == MAX_STOMPED) {
            player.sendOverlayMessage(Component.literal("grape juice. YAY"));
        }
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    static {
        SHAPE = Block.box(0, 0, 0, 16, 7, 16);
    }
}