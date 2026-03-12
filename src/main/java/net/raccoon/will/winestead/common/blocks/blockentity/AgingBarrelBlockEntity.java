package net.raccoon.will.winestead.common.blocks.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.ticks.ContainerSingleItem;
import net.raccoon.will.winestead.common.data.WineItemData;
import net.raccoon.will.winestead.registry.WSComponents;

import java.util.List;

import static net.raccoon.will.winestead.registry.WSBlockEntities.AGING_BARREL_BE;

public class AgingBarrelBlockEntity extends BlockEntity implements ContainerSingleItem.BlockContainerSingleItem{
    private ItemStack barrelItem = ItemStack.EMPTY;


    public AgingBarrelBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(AGING_BARREL_BE.get(), blockPos, blockState);

    }

    public static void tick(Level level, BlockPos pos, BlockState state, AgingBarrelBlockEntity blockEntity) {
        if (blockEntity.barrelItem.isEmpty()) return;
        if (level.isClientSide()) return;

        ItemStack stack = blockEntity.getTheItem();
        WineItemData data = stack.getOrDefault(WSComponents.WINE_ITEM_DATA.get(), new WineItemData(0));

        int newAge = data.ticks_aged() + 1;

        stack.set(WSComponents.WINE_ITEM_DATA.get(), new WineItemData(newAge));

        //update every 5 sec.
        if (newAge % 100 == 0) {
            blockEntity.setChanged();
        }
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        components.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(List.of(this.barrelItem)));
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter components) {
        this.barrelItem = components.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyOne();
    }

    @Override
    public BlockEntity getContainerBlockEntity() {
        return this;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        if (!this.getTheItem().isEmpty()) {
            output.store("item", ItemStack.CODEC, this.barrelItem);
        }
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.barrelItem = input.read("item", ItemStack.CODEC)
                .orElse(ItemStack.EMPTY);
    }

    @Override
    public ItemStack getTheItem() {
        return this.barrelItem;
    }

    @Override
    public void setTheItem(ItemStack itemStack) {
        this.barrelItem = itemStack;
        this.setChanged();
    }
}