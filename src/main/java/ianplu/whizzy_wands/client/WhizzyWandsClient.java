package ianplu.whizzy_wands.client;

import ianplu.whizzy_wands.init.Content;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.render.RenderLayer;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class WhizzyWandsClient implements ClientModInitializer {
    static final int WHIZZY_WOOD_LEAVES_COLOR = 0x4dbf86;

    @Override
    public void onInitializeClient() {
        // Register leaves color
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> WHIZZY_WOOD_LEAVES_COLOR, Content.WHIZZY_WOOD_LEAVES);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> WHIZZY_WOOD_LEAVES_COLOR, Content.WHIZZY_WOOD_LEAVES);

        // Transparent saplings
        BlockRenderLayerMap.INSTANCE.putBlock(Content.WHIZZY_WOOD_SAPLING, RenderLayer.getCutout());

    }
}
