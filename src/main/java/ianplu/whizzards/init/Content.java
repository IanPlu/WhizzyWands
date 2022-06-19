package ianplu.whizzards.init;

import ianplu.whizzards.Whizzards;
import ianplu.whizzards.item.ItemLaunchWand;
import ianplu.whizzards.item.ItemRocketWand;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

public class Content {

    public static Item WAND_CORE;

    public static Item LAUNCH_WAND;
    public static Item ROCKET_WAND;

    public static void init() {
        WAND_CORE = new Item(new FabricItemSettings()
                .group(Whizzards.ITEM_GROUP)
                .rarity(Rarity.COMMON)
        );

        LAUNCH_WAND = new ItemLaunchWand(new FabricItemSettings());
        ROCKET_WAND = new ItemRocketWand(new FabricItemSettings());
    }
}
