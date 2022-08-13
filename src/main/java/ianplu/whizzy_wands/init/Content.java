package ianplu.whizzy_wands.init;

import ianplu.whizzy_wands.WhizzyWands;
import ianplu.whizzy_wands.block.WhizzyWoodLeaves;
import ianplu.whizzy_wands.block.WhizzyWoodLog;
import ianplu.whizzy_wands.block.WhizzyWoodPlanks;
import ianplu.whizzy_wands.block.WhizzyWoodSapling;
import ianplu.whizzy_wands.entity.EntityGrappleWandHook;
import ianplu.whizzy_wands.feature.WhizzyWoodSaplingGenerator;
import ianplu.whizzy_wands.item.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

public class Content {

    // Items
    public static Item WAND_CORE;
    public static Item GRAPPLE_WAND_HOOK;

    public static Item LAUNCH_WAND;
    public static Item ROCKET_WAND;
    public static Item GRAPPLE_WAND;
    public static Item LIGHTNING_WAND;
    public static Item ICICLE_WAND;

    // Blocks
    public static Block WHIZZY_WOOD_LOG;
    public static Block WHIZZY_WOOD_LEAVES;
    public static Block WHIZZY_WOOD_SAPLING;
    public static Block WHIZZY_WOOD_PLANKS;
    public static Block WHIZZY_WOOD_STAIRS;
    public static Block WHIZZY_WOOD_SLAB;

    // Entities
    public static EntityType<EntityGrappleWandHook> ENTITY_GRAPPLE_WAND_HOOK;

    // Features
    public static WhizzyWoodSaplingGenerator WHIZZY_WOOD_TREE_GENERATOR;

    public static void init() {
        WAND_CORE = new Item(new FabricItemSettings()
                .group(WhizzyWands.ITEM_GROUP)
                .rarity(Rarity.COMMON)
        );
        GRAPPLE_WAND_HOOK = new Item(new FabricItemSettings()
                .group(WhizzyWands.ITEM_GROUP)
                .rarity(Rarity.COMMON)
        );

        // Item
        LAUNCH_WAND = new ItemLaunchWand(new FabricItemSettings());
        ROCKET_WAND = new ItemRocketWand(new FabricItemSettings());
        GRAPPLE_WAND = new ItemGrappleWand(new FabricItemSettings());
        LIGHTNING_WAND = new ItemLightningWand(new FabricItemSettings());

        // Block
        WHIZZY_WOOD_LOG = new WhizzyWoodLog();
        WHIZZY_WOOD_LEAVES = new WhizzyWoodLeaves();
        WHIZZY_WOOD_PLANKS = new WhizzyWoodPlanks();
        WHIZZY_WOOD_STAIRS = new StairsBlock(WHIZZY_WOOD_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(WHIZZY_WOOD_PLANKS));
        WHIZZY_WOOD_SLAB = new SlabBlock(FabricBlockSettings.copyOf(WHIZZY_WOOD_PLANKS));

        // Entity
        ENTITY_GRAPPLE_WAND_HOOK = FabricEntityTypeBuilder.<EntityGrappleWandHook>create(SpawnGroup.MISC, EntityGrappleWandHook::new)
                .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                .trackRangeBlocks(4)
                .trackedUpdateRate(10)
                .build();

        // Feature
        WHIZZY_WOOD_TREE_GENERATOR = new WhizzyWoodSaplingGenerator(WHIZZY_WOOD_LOG, WHIZZY_WOOD_LEAVES);
        WHIZZY_WOOD_SAPLING = new WhizzyWoodSapling(WHIZZY_WOOD_TREE_GENERATOR);
    }
}
