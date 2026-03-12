package net.raccoon.will.winestead.common.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.raccoon.will.winestead.registry.WSFoods;
import org.jetbrains.annotations.NotNull;

public class BlazedGrapesItem extends GrapesItem {
    public BlazedGrapesItem(Properties properties) {
        super(properties.food(WSFoods.Blazed_Grapes));
    }

    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!entity.fireImmune() && !level.isClientSide()) {
            entity.igniteForSeconds(1.5F);
        }

        return super.finishUsingItem(stack, level, entity);

    }
}
