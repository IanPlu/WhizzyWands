package ianplu.whizzy_wands.item;

import ianplu.whizzy_wands.WhizzyWands;
import ianplu.whizzy_wands.config.WhizzyWandsConfig;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class ItemRocketWand extends Item {
    public ItemRocketWand(Settings settings) {
        super(settings
                .group(WhizzyWands.ITEM_GROUP)
                .maxCount(1)
                .fireproof()
                .maxDamageIfAbsent(WhizzyWandsConfig.rocket_wand_durability)
                .rarity(Rarity.UNCOMMON)
        );
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.whizzy_wands.rocket_wand.tooltip_1"));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity caster, Hand hand) {
        ItemStack stack = caster.getStackInHand(hand);
        caster.setCurrentHand(hand);

        world.playSound(null, caster.getX(), caster.getY(), caster.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 0.5F, 2.0F);
        world.addParticle(ParticleTypes.EXPLOSION, caster.getX(), caster.getY(), caster.getZ(), 1.0, 0.0, 0.0);

        caster.getItemCooldownManager().set(this, 5);

        float launchStrength = -3.0f * WhizzyWandsConfig.rocket_wand_scalar;
        float halfPi = (float)Math.PI / 180;

        double x = -MathHelper.sin(caster.getYaw() * halfPi) * MathHelper.cos(caster.getPitch() * halfPi) * launchStrength;
        double y = -MathHelper.sin(caster.getPitch() * halfPi) * launchStrength * 0.9f;
        double z = MathHelper.cos(caster.getYaw() * halfPi) * MathHelper.cos(caster.getPitch() * halfPi) * launchStrength;

        caster.addVelocity(x, y, z);

        stack.damage(1, caster, (p) -> {
            p.sendToolBreakStatus(caster.getActiveHand());
        });

        return TypedActionResult.consume(stack);
    }
}
