package ianplu.whizzy_wands.feature;

import net.minecraft.block.Block;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.PineFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class WhizzyWoodSaplingGenerator extends SaplingGenerator {
    private final RegistryEntry<? extends ConfiguredFeature<TreeFeatureConfig, ?>> feature;

    public WhizzyWoodSaplingGenerator(Block log, Block leaves) {
        this.feature = ConfiguredFeatures.register(
                "whizziwood", Feature.TREE,
                new TreeFeatureConfig.Builder(
                        BlockStateProvider.of(log),
                        new StraightTrunkPlacer(4, 2, 3),
                        BlockStateProvider.of(leaves),
                        new PineFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(1), ConstantIntProvider.create(4)), // radius, offset from trunk, height
                        new TwoLayersFeatureSize(1, 0, 1)
                ).build()
        );;
    }

    @Nullable
    @Override
    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return this.feature;
    }
}
