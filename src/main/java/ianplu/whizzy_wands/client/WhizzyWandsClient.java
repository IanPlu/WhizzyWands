package ianplu.whizzy_wands.client;

import ianplu.whizzy_wands.WhizzyWands;
import ianplu.whizzy_wands.init.Content;
import ianplu.whizzy_wands.packet.EntitySpawnPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.impl.networking.ClientSidePacketRegistryImpl;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;

import java.util.UUID;

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

        // Register grapple wand hook renderer
        EntityRendererRegistry.register(Content.ENTITY_GRAPPLE_WAND_HOOK, (context) -> new FlyingItemEntityRenderer<>(context));
        receiveEntityPacket();
    }

    public void receiveEntityPacket() {
        ClientSidePacketRegistryImpl.INSTANCE.register(WhizzyWands.PACKET_ID, (ctx, byteBuf) -> {
            EntityType<?> et = Registry.ENTITY_TYPE.get(byteBuf.readVarInt());
            UUID uuid = byteBuf.readUuid();
            int entityId = byteBuf.readVarInt();
            Vec3d pos = EntitySpawnPacket.PacketBufUtil.readVec3d(byteBuf);
            float pitch = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
            float yaw = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
            ctx.getTaskQueue().execute(() -> {
                if (MinecraftClient.getInstance().world == null) {
                    throw new IllegalStateException("Tried to spawn entity in a null world!");
                }
                Entity e = et.create(MinecraftClient.getInstance().world);
                if (e == null) {
                    throw new IllegalStateException("Failed to create instance of entity \"" + Registry.ENTITY_TYPE.getId(et) + "\"!");
                }
                e.updateTrackedPosition(pos);
                e.setPos(pos.x, pos.y, pos.z);
                e.setPitch(pitch);
                e.setYaw(yaw);
                e.setId(entityId);
                e.setUuid(uuid);
                MinecraftClient.getInstance().world.addEntity(entityId, e);
            });
        });
    }
}
