package ianplu.whizzy_wands.item;

import ianplu.whizzy_wands.WhizzyWands;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
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
import net.minecraft.util.UseAction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class ItemLaunchWand extends Item {
    public ItemLaunchWand(Settings settings) {
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
        tooltip.add(new TranslatableText("item.whizzy_wands.launch_wand.tooltip_1"));
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    public static float getPullProgress(int useTicks) {
        // Copied shamelessly from BowItem, who knows what this is doing
        float f = ((float) useTicks) / 20.0F;
        f = (f * f * f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        WhizzyWands.LOGGER.info("Pull progress: %f".formatted(f));
        return f;
    }

        @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity caster) {
            float pullProgress = getPullProgress(this.getMaxUseTime(stack) - remainingUseTicks);

            world.playSound(null, caster.getX(), caster.getY(), caster.getZ(), SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, SoundCategory.PLAYERS, pullProgress, 1.0F);
            world.addParticle(ParticleTypes.GLOW, caster.getX(), caster.getY(), caster.getZ(), 0.0, -1.0, 0.0);

            float launchStrength = 1.1f * pullProgress;
            float halfPi = (float)Math.PI / 180;
            caster.addVelocity(
                    -MathHelper.sin(caster.getYaw() * halfPi) * MathHelper.cos(caster.getPitch() * halfPi) * launchStrength * 2.3f,
                    launchStrength,
                    MathHelper.cos(caster.getYaw() * halfPi) * MathHelper.cos(caster.getPitch() * halfPi) * launchStrength * 2.3f
            );

            stack.damage(1, caster, (p) -> {
                p.sendToolBreakStatus(caster.getActiveHand());
            });
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack stack = playerEntity.getStackInHand(hand);
        playerEntity.setCurrentHand(hand);
        return TypedActionResult.consume(stack);
    }
}
