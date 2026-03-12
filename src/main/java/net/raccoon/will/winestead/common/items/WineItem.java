package net.raccoon.will.winestead.common.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.raccoon.will.winestead.common.data.WineItemData;
import net.raccoon.will.winestead.registry.WSComponents;

import java.util.function.Consumer;

public class WineItem extends Item {

    public WineItem(Properties properties) {
        super(properties.stacksTo(8));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
        int ticks = getAgeTime(itemStack);
        if (ticks > 0) {
            double days = ticks / 24000.0;
            String formatted = String.format("%.2f", days);
            builder.accept(Component.literal("Time Aged: " + formatted + "d").withStyle(ChatFormatting.GRAY));

            if (days == 1) {
                builder.accept(Component.literal("Basic Wine").withStyle(ChatFormatting.GRAY));
            } else if (days == 3) {
                builder.accept(Component.literal("Fine Wine").withStyle(ChatFormatting.WHITE));
            } else if (days == 7) {
            builder.accept(Component.literal("Grand Wine").withStyle(ChatFormatting.GOLD));
        }
        }
    }

    public static int getAgeTime(ItemStack stack) {
        WineItemData data = stack.get(WSComponents.WINE_ITEM_DATA.get());
        return data != null ? data.ticks_aged() : 0;
    }

    public static void setAgeTime(ItemStack stack, int value) {
        WineItemData data = new WineItemData(value);
        stack.set(WSComponents.WINE_ITEM_DATA.get(), data);
    }
}


