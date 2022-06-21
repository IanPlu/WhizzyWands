package ianplu.whizzy_wands;

import ianplu.whizzy_wands.init.Content;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WhizzyWands implements ModInitializer {
    static final String NAMESPACE = "whizzy_wands";

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

        // Block Items
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "whizzy_wood_log"), new BlockItem(Content.WHIZZY_WOOD_LOG, new FabricItemSettings().group(ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "whizzy_wood_leaves"), new BlockItem(Content.WHIZZY_WOOD_LEAVES, new FabricItemSettings().group(ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "whizzy_wood_sapling"), new BlockItem(Content.WHIZZY_WOOD_SAPLING, new FabricItemSettings().group(ITEM_GROUP)));

        // Items
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "wand_core"), Content.WAND_CORE);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "launch_wand"), Content.LAUNCH_WAND);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE, "rocket_wand"), Content.ROCKET_WAND);
    }
}
