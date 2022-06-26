package ianplu.whizzy_wands.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.sound.BlockSoundGroup;

public class WhizzyWoodSapling extends SaplingBlock {
    public WhizzyWoodSapling(SaplingGenerator generator) {

        super(generator, FabricBlockSettings.of(Material.PLANT)
                .noCollision()
                .ticksRandomly()
                .breakInstantly()
                .sounds(BlockSoundGroup.GRASS)
                .nonOpaque()
        );
    }
}
