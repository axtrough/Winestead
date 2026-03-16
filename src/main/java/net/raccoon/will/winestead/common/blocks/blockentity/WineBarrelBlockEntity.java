package net.raccoon.will.winestead.common.blocks.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.raccoon.will.winestead.common.menus.WineBarrelMenu;

import static net.raccoon.will.winestead.registry.WSBlockEntities.WINE_BARREL_BE;


public class WineBarrelBlockEntity extends BaseContainerBlockEntity {
    public static final int SIZE = 3;
    private NonNullList<ItemStack> items = NonNullList.withSize(SIZE, ItemStack.EMPTY);
    private final NonNullList<ItemStack> ingredients = NonNullList.withSize(8, ItemStack.EMPTY);

    public WineBarrelBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(WINE_BARREL_BE.get(), worldPosition, blockState);
    }

    @Override
    public int getContainerSize() {
        return SIZE;
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.winestead.wine_barrel");
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    public boolean insertItem(ItemStack stack, Player player) {
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).isEmpty()) {
                ingredients.set(i, stack.copy());
                this.setChanged();
                return true;
            }
        }
        return false;
    }

    public boolean hasIngredients() {
        return ingredients.stream().anyMatch(stack -> !stack.isEmpty());
    }

    public int getIngredientSlotsFilled() {
        int count = 0;
        for (ItemStack stack : ingredients) {
            if (!stack.isEmpty()) {
                count++;
            }
        }
        return count;
    }

    public int getMaxIngredients() {
        return ingredients.size();
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new WineBarrelMenu(containerId, inventory, this, this.worldPosition);
    }
}
