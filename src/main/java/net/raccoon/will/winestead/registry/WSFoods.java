package net.raccoon.will.winestead.registry;

import net.minecraft.world.food.FoodProperties;

public class WSFoods {

    public static FoodProperties Red_Grapes = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.2F)
            .build();

    public static FoodProperties White_Grapes = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.2F)
            .build();

    public static FoodProperties Blazed_Grapes = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.4F)
            .build();
}
