package ianplu.whizzy_wands;

import ianplu.whizzy_wands.init.Content;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WhizzyWands implements ModInitializer {
    static final String NAMESPACE = "whizzy_wands";

    public static final Logger LOGGER = LogManager.getLogger(NAMESPACE);

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier("whizzy_wands", "wands"))
            .icon(() -> new ItemStack(Content.LAUNCH_WAND))
            .build();

    @Override
    public void onInitialize() {
        Content.init();

        // Blocks
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "whizzy_wood_log"), Content.WHIZZY_WOOD_LOG);
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "whizzy_wood_leaves"), Content.WHIZZY_WOOD_LEAVES);
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "whizzy_wood_sapling"), Content.WHIZZY_WOOD_SAPLING);
        Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "whizzy_wood_planks"), Content.WHIZZY_WOOD_PLANKS);

        // Block Items
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "whizzy_wood_log"), new BlockItem(Content.WHIZZY_WOOD_LOG, new FabricItemSettings().group(ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "whizzy_wood_leaves"), new BlockItem(Content.WHIZZY_WOOD_LEAVES, new FabricItemSettings().group(ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "whizzy_wood_sapling"), new BlockItem(Content.WHIZZY_WOOD_SAPLING, new FabricItemSettings().group(ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "whizzy_wood_planks"), new BlockItem(Content.WHIZZY_WOOD_PLANKS, new FabricItemSettings().group(ITEM_GROUP)));

        // Items
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "wand_core"), Content.WAND_CORE);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "launch_wand"), Content.LAUNCH_WAND);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "rocket_wand"), Content.ROCKET_WAND);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "grapple_wand"), Content.GRAPPLE_WAND);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "lightning_wand"), Content.LIGHTNING_WAND);

        // Features
        RegistryEntry<PlacedFeature> whizziwoodTree = PlacedFeatures.register(
                new Identifier(NAMESPACE, "whizzy_wood_tree").toString(),
                Content.WHIZZY_WOOD_TREE_GENERATOR.LARGE_WHIZZIWOOD_TREE,
                PlacedFeatures.createCountExtraModifier(0, 0.005f, 1),
                SquarePlacementModifier.of(),
                VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER,
                PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Content.WHIZZY_WOOD_SAPLING.getDefaultState(), BlockPos.ORIGIN)),
                BiomePlacementModifier.of()
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(
                        BiomeKeys.FOREST,
                        BiomeKeys.FLOWER_FOREST,
                        BiomeKeys.PLAINS
                ),
                GenerationStep.Feature.VEGETAL_DECORATION,
                whizziwoodTree.getKey().get()
        );
    }
}
