package net.raccoon.will.winestead.util;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class Util {

    public static boolean barrelClicked(int button) {
        Minecraft mc = Minecraft.getInstance();
        assert mc.player != null;
        ItemStack held = mc.player.containerMenu.getCarried();
        System.out.println("barrel clicked");
        if (button == 0) {
            mc.player.sendOverlayMessage(Component.literal("The item you just put in was " + held.getHoverName().getString()));
            return true;
        }
        return false;
    }
}
