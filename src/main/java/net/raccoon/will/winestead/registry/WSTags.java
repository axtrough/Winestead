package net.raccoon.will.winestead.registry;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.raccoon.will.winestead.Winestead;

public class WSTags {

    public static class Blocks {
        public static final TagKey<Block> BLOCK_TAG = createTag("block_tag");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(Winestead.resLoc(name));
        }
    }

    public static class Items {
        public static final TagKey<Item> AGEABLE_ITEMS = createTag("ageable_items");
        public static final TagKey<Item> VALID_GRAPE = createTag("valid_grape");
        public static final TagKey<Item> VALID_INGREDIENTS = createTag("valid_ingredients");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(Winestead.resLoc(name));
        }
    }
}
