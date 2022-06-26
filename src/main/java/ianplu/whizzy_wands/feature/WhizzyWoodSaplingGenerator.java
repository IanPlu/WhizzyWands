package ianplu.whizzy_wands.feature;

import net.minecraft.block.Block;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class WhizzyWoodSaplingGenerator extends SaplingGenerator {
    public final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> SMALL_WHIZZIWOOD_TREE;
    public final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> LARGE_WHIZZIWOOD_TREE;

    public WhizzyWoodSaplingGenerator(Block log, Block leaves) {
        this.SMALL_WHIZZIWOOD_TREE = ConfiguredFeatures.register(
                "whizziwood", Feature.TREE,
                new TreeFeatureConfig.Builder(
                        BlockStateProvider.of(log),
                        new StraightTrunkPlacer(5, 2, 2),
                        BlockStateProvider.of(leaves),
                        new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1),3), // radius, offset, height
                        new TwoLayersFeatureSize(1, 0, 1)
                ).build()
        );

        this.LARGE_WHIZZIWOOD_TREE = ConfiguredFeatures.register(
                "whizziwood_large", Feature.TREE,
                new TreeFeatureConfig.Builder(
                        BlockStateProvider.of(log),
                        new LargeOakTrunkPlacer(5, 3, 3),
                        BlockStateProvider.of(leaves),
                        new LargeOakFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(3), 4),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).build()
        );
    }

    @Nullable
    @Override
    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        int chance = random.nextInt(100);
        if (chance <= 15) {
            return this.LARGE_WHIZZIWOOD_TREE;
        } else {
            return this.SMALL_WHIZZIWOOD_TREE;
        }
    }
}
