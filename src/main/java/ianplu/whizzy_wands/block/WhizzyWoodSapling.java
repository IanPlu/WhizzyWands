package ianplu.whizzy_wands.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;

public class WhizzyWoodSapling extends SaplingBlock {
    public WhizzyWoodSapling(SaplingGenerator generator) {
        super(generator, FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)
        );
    }
}
