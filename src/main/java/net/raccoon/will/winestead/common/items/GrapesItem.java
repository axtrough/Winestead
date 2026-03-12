package net.raccoon.will.winestead.common.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.raccoon.will.winestead.common.misc.Ripeness;
import net.raccoon.will.winestead.registry.WSComponents;

import java.util.function.Consumer;

public class GrapesItem extends Item {
    public GrapesItem(Properties properties) {
        super(properties);
    }


    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag flag) {
        super.appendHoverText(stack, context, display, builder, flag);
        Ripeness ripeness = stack.getOrDefault(WSComponents.RIPENESS.get(), Ripeness.UNRIPE);

        if (ripeness == Ripeness.UNRIPE) {
            builder.accept(Component.literal(ripeness.name()).withStyle(ChatFormatting.WHITE));
        } else if (ripeness == Ripeness.RIPE) {
            builder.accept(Component.literal(ripeness.name()).withStyle(ChatFormatting.GREEN));
        } else if (ripeness == Ripeness.OVERRIPE) {
            builder.accept(Component.literal(ripeness.name()).withStyle(ChatFormatting.DARK_GREEN));
        }
    }
}
