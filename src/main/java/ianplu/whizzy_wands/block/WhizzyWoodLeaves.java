package ianplu.whizzy_wands.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class WhizzyWoodLeaves extends LeavesBlock {
    public WhizzyWoodLeaves() {
        super(FabricBlockSettings.of(Material.LEAVES)
                .strength(1.0f)
                .sounds(BlockSoundGroup.GRASS)
                .ticksRandomly()
                .nonOpaque()
        );
    }
}
