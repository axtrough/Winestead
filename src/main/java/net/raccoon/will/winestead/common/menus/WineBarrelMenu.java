package net.raccoon.will.winestead.common.menus;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.raccoon.will.winestead.common.blocks.blockentity.WineBarrelBlockEntity;

import static net.raccoon.will.winestead.registry.WSMenus.WINE_BARREL;

public class WineBarrelMenu extends AbstractContainerMenu {
    private final Container wineBarrel;
    private final ContainerData data;

    //client side
    public WineBarrelMenu(int containerId, Inventory inv) {
        this(containerId, inv, new SimpleContainer(3), new SimpleContainerData(4));
    }

    //server side
    public WineBarrelMenu(int containerId, Inventory inv, Container wineBarrel, BlockPos pos) {
        this(containerId, inv, wineBarrel, new SimpleContainerData(4) {{
            set(0, pos.getX());
            set(1, pos.getY());
            set(2, pos.getZ());
            set(3, 0);
        }});
    }

    private WineBarrelMenu(int containerId, Inventory inv, Container wineBarrel, ContainerData data) {
        super(WINE_BARREL.get(), containerId);
        this.wineBarrel = wineBarrel;
        this.data = data;

//        this.addSlot(new Slot(wineBarrel, 0, 120, 10)); //ingredients
        this.addSlot(new Slot(wineBarrel, 1, 80, 9)); //input
        this.addSlot(new Slot(wineBarrel, 2, 80, 59)); //output
        this.addStandardInventorySlots(inv, 8, 84);
        this.addDataSlots(data);
    }

    @Override
    public void broadcastChanges() {
        if (wineBarrel instanceof WineBarrelBlockEntity be) {
            data.set(3, be.hasIngredients() ? 1 : 0);
        }
        super.broadcastChanges();
    }

    public BlockPos getBlockPos() {
        return new BlockPos(data.get(0), data.get(1), data.get(2));
    }

    public boolean barrelHasIngredients() {
        return data.get(3) == 1;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack returnStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            returnStack = slotStack.copy();
            if (index < WineBarrelBlockEntity.SIZE) {
                if (!this.moveItemStackTo(slotStack, WineBarrelBlockEntity.SIZE, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.moveItemStackTo(slotStack, 0, WineBarrelBlockEntity.SIZE, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (slotStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return returnStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.wineBarrel.stillValid(player);
    }
}
