package ianplu.whizzy_wands.init;

import ianplu.whizzy_wands.WhizzyWands;
import ianplu.whizzy_wands.block.WhizzyWoodLeaves;
import ianplu.whizzy_wands.block.WhizzyWoodLog;
import ianplu.whizzy_wands.block.WhizzyWoodPlanks;
import ianplu.whizzy_wands.block.WhizzyWoodSapling;
import ianplu.whizzy_wands.feature.WhizzyWoodSaplingGenerator;
import ianplu.whizzy_wands.item.ItemLaunchWand;
import ianplu.whizzy_wands.item.ItemRocketWand;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

public class Content {

    public static Item WAND_CORE;

    public static Item LAUNCH_WAND;
    public static Item ROCKET_WAND;

    public static Block WHIZZY_WOOD_LOG;
    public static Block WHIZZY_WOOD_LEAVES;
    public static Block WHIZZY_WOOD_SAPLING;
    public static Block WHIZZY_WOOD_PLANKS;
    public static Block WHIZZY_WOOD_STAIRS;
    public static Block WHIZZY_WOOD_SLAB;

    public static WhizzyWoodSaplingGenerator WHIZZY_WOOD_TREE_GENERATOR;

    public static void init() {
        WAND_CORE = new Item(new FabricItemSettings()
                .group(WhizzyWands.ITEM_GROUP)
                .rarity(Rarity.COMMON)
        );

        // Item
        LAUNCH_WAND = new ItemLaunchWand(new FabricItemSettings());
        ROCKET_WAND = new ItemRocketWand(new FabricItemSettings());

        // Block
        WHIZZY_WOOD_LOG = new WhizzyWoodLog();
        WHIZZY_WOOD_LEAVES = new WhizzyWoodLeaves();
        WHIZZY_WOOD_PLANKS = new WhizzyWoodPlanks();

        // Feature
        WHIZZY_WOOD_TREE_GENERATOR = new WhizzyWoodSaplingGenerator(WHIZZY_WOOD_LOG, WHIZZY_WOOD_LEAVES);
        WHIZZY_WOOD_SAPLING = new WhizzyWoodSapling(WHIZZY_WOOD_TREE_GENERATOR);
    }
}
