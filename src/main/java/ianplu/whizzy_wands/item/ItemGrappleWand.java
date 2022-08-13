package ianplu.whizzy_wands.item;

import ianplu.whizzy_wands.WhizzyWands;
import ianplu.whizzy_wands.entity.EntityGrappleWandHook;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class ItemGrappleWand extends Item {
    private final Double MAX_GRAPPLE_DISTANCE = 64.0;

    public ItemGrappleWand(Settings settings) {
        super(settings
                .group(WhizzyWands.ITEM_GROUP)
                .maxCount(1)
                .fireproof()
                .maxDamageIfAbsent(256)
                .rarity(Rarity.UNCOMMON)
        );
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.whizzy_wands.grapple_wand.tooltip_1"));
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity caster, Hand hand) {
        ItemStack stack = caster.getStackInHand(hand);
        caster.setCurrentHand(hand);

        HitResult result = caster.raycast(MAX_GRAPPLE_DISTANCE, 1.0f, false);

//        Vec3d pos = result.getPos();
//        WhizzyWands.LOGGER.info("Raycast result: (%f, %f, %f) (%f away)".formatted(pos.getX(), pos.getY(), pos.getZ(), result.squaredDistanceTo(caster)));

        WhizzyWands.LOGGER.info("Firing grapple!");
        EntityGrappleWandHook hook = new EntityGrappleWandHook(world, caster);
        hook.setVelocity(caster, caster.getPitch(), caster.getYaw(), 0.0F, 2.5F, 0F);
        world.spawnEntity(hook);

        caster.getItemCooldownManager().set(this, 5);

        stack.damage(1, caster, (p) -> {
            p.sendToolBreakStatus(caster.getActiveHand());
        });

        return TypedActionResult.consume(stack);
    }
}
