package ianplu.whizzy_wands.item;

import ianplu.whizzy_wands.WhizzyWands;
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

        Vec3d pos = result.getPos();
        WhizzyWands.LOGGER.info("Raycast result: (%f, %f, %f) (%f away)".formatted(pos.getX(), pos.getY(), pos.getZ(), result.squaredDistanceTo(caster)));

//        pos.

//        float launchStrength = 2.5f;
        float halfPi = (float)Math.PI / 180;

//        double x = -MathHelper.sin(caster.getYaw() * halfPi) * MathHelper.cos(caster.getPitch() * halfPi) * launchStrength;
//        double y = -MathHelper.sin(caster.getPitch() * halfPi) * launchStrength * 0.9f;
//        double z = MathHelper.cos(caster.getYaw() * halfPi) * MathHelper.cos(caster.getPitch() * halfPi) * launchStrength;
//
//        WhizzyWands.LOGGER.info("Rocket Wand launch parameters: (%f, %f, %f)".formatted(x, y, z));
//
//        caster.addVelocity(x, y, z);

        stack.damage(1, caster, (p) -> {
            p.sendToolBreakStatus(caster.getActiveHand());
        });

        return TypedActionResult.consume(stack);
    }
}
