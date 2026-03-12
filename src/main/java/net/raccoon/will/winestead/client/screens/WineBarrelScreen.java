package net.raccoon.will.winestead.client.screens;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.raccoon.will.winestead.Winestead;
import net.raccoon.will.winestead.common.menus.WineBarrelMenu;
import net.raccoon.will.winestead.core.network.packets.ItemBarrelPacket;

public class WineBarrelScreen extends AbstractContainerScreen<WineBarrelMenu> {

    public WineBarrelScreen(WineBarrelMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 176, 167);
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2 + 52;
        this.inventoryLabelX = (this.imageWidth - this.font.width(this.title)) / 2 + 56;
    }

    private static final Identifier TEXTURE =
            Winestead.resLoc("textures/gui/winebarrel_gui.png");

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        int xo = (this.width - this.imageWidth) / 2;
        int yo = (this.height - this.imageHeight) / 2;
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, xo, yo, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);


        if (menu.barrelHasIngredients()) {
            int fluidLeft = xo + 11;
            int fluidTop = yo + 42;
            int fluidRight = xo + 62;
            int fluidBottom = yo + 76;
            graphics.fill(fluidLeft, fluidTop, fluidRight, fluidBottom, 0xffe1ff00);
        }
    }

//    @Override
//    public void render(GuiGraphics graphics, int mouseX, int mouseY, float a) {
//        super.render(graphics, mouseX, mouseY, a);
//        int x = leftPos;
//        int y = topPos;
//        int fluidLeft = x + 11;
//        int fluidTop = y + 42;
//        int fluidRight = x + 62;
//        int fluidBottom = y + 76;
//
//        Minecraft mc = Minecraft.getInstance();
//        assert mc.player != null;
//        ItemStack held = mc.player.containerMenu.getCarried();
//
//        if (menu.barrelHasIngredients()) {
//            graphics.fill(fluidLeft, fluidTop, fluidRight, fluidBottom, 0xffe1ff00);
//        }
//    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        int x = leftPos;
        int y = topPos;

        int barrelLeft = x + 11;
        int barrelTop = y + 8;
        int barrelRight = x + 61;
        int barrelBottom = y + 75;

        if (event.x() >= barrelLeft && event.x() <= barrelRight &&
                event.y() >= barrelTop && event.y() <= barrelBottom) {

            onBarrelClick(event.button());
            return true;
        }

        return super.mouseClicked(event, doubleClick);
    }

    public void onBarrelClick(int button) {
        Minecraft mc = Minecraft.getInstance();
        assert mc.player != null;
        ItemStack held = mc.player.containerMenu.getCarried();
        System.out.println("barrel clicked");

        if (button == 0) {
            if (!held.isEmpty()) {
                BlockPos pos = menu.getBlockPos();
                ClientPacketDistributor.sendToServer(new ItemBarrelPacket(pos, held.copy()));
            }
            return;
        }

        if (button == 1) {
            // right click
        }
    }
}
