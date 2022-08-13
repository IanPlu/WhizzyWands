package ianplu.whizzy_wands.entity;

import ianplu.whizzy_wands.WhizzyWands;
import ianplu.whizzy_wands.init.Content;
import ianplu.whizzy_wands.packet.EntitySpawnPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityGrappleWandHook extends ThrownItemEntity {
    public EntityGrappleWandHook(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public EntityGrappleWandHook(World world, LivingEntity owner) {
        super(Content.ENTITY_GRAPPLE_WAND_HOOK, owner, world);
    }

    public EntityGrappleWandHook(World world, double x, double y, double z) {
        super(Content.ENTITY_GRAPPLE_WAND_HOOK, x, y, z, world);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        WhizzyWands.LOGGER.info("Hit entity!");
        this.setVelocity(0.0f, 0.0f, 0.0f);
        this.pullOwner();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        WhizzyWands.LOGGER.info("Hit block!");
        this.setVelocity(0.0f, 0.0f, 0.0f);
        this.pullOwner();
    }

    @Override
    public Packet createSpawnPacket() {
        return EntitySpawnPacket.create(this, WhizzyWands.PACKET_ID);
    }

    private void pullOwner() {
        Entity owner = this.getOwner();
        if (owner != null) {
            WhizzyWands.LOGGER.info("Pulling owner to (%f, %f, %f)".formatted(this.getX(), this.getY(), this.getZ()));
            Vec3d pullForce = new Vec3d(
                    owner.getX() - this.getX(),
                    owner.getY() - this.getY(),
                    owner.getZ() - this.getZ()
            ).multiply(1.0);
            owner.setVelocity(owner.getVelocity().add(pullForce));
        }
    }

    @Override
    protected Item getDefaultItem() {
        return Content.GRAPPLE_WAND_HOOK;
    }

    @Override
    protected float getGravity() {
        return 0.005f;
    }
}
