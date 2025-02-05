package com.ricky.terrariamod.item.tool;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

public class ModPickaxeItem extends DiggerItem {
    public ModPickaxeItem(Tier $$0, int $$1, float $$2, Item.Properties $$3) {
        super((float)$$1, $$2, $$0, BlockTags.MINEABLE_WITH_PICKAXE, $$3);
    }
}
