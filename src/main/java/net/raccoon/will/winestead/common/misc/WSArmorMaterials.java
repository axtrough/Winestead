package net.raccoon.will.winestead.common.misc;

import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.neoforged.neoforge.common.Tags;
import net.raccoon.will.winestead.Winestead;

import java.util.EnumMap;

public class WSArmorMaterials {
    private static final ResourceKey<EquipmentAsset> SLIDING_BOOTS_ASSET = ResourceKey.create(EquipmentAssets.ROOT_ID, Winestead.resLoc("sliding_boots"));

    public static final ArmorMaterial SLIDING_BOOTS_MATERIAL = new ArmorMaterial(3, Util.make(new EnumMap<>(ArmorType.class), map -> {
                map.put(ArmorType.BOOTS, 2);
            }), 10, SoundEvents.ARMOR_EQUIP_LEATHER, 0, 0, Tags.Items.LEATHERS, SLIDING_BOOTS_ASSET);
}
