package ianplu.whizzy_wands;

import ianplu.whizzy_wands.init.Content;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WhizzyWands implements ModInitializer {
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier("whizzy_wands", "wands"))
            .icon(() -> new ItemStack(Content.LAUNCH_WAND))
            .build();

    @Override
    public void onInitialize() {
        Content.init();

        Registry.register(Registry.ITEM, new Identifier("whizzy_wands", "wand_core"), Content.WAND_CORE);

        Registry.register(Registry.ITEM, new Identifier("whizzy_wands", "launch_wand"), Content.LAUNCH_WAND);
        Registry.register(Registry.ITEM, new Identifier("whizzy_wands", "rocket_wand"), Content.ROCKET_WAND);
    }
}
