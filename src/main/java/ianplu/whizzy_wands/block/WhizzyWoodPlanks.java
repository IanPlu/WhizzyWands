package ianplu.whizzy_wands.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class WhizzyWoodPlanks extends Block {
    public WhizzyWoodPlanks() {
        super(FabricBlockSettings.of(Material.WOOD)
                .strength(2.0f, 3.0f)
                .sounds(BlockSoundGroup.WOOD)
        );
    }
}
