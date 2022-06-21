package ianplu.whizzy_wands.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.sound.BlockSoundGroup;

public class WhizzyWoodLog extends PillarBlock {
    public WhizzyWoodLog() {
        super(FabricBlockSettings.of(Material.WOOD)
                .strength(2.5f)
                .sounds(BlockSoundGroup.WOOD)
        );
    }
}
