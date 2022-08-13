package ianplu.whizzy_wands.item;

import ianplu.whizzy_wands.WhizzyWands;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class ItemLightningWand extends Item {
    private final Double MAX_RANGE = 128.0;

    public ItemLightningWand(Settings settings) {
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
        tooltip.add(new TranslatableText("item.whizzy_wands.lightning_wand.tooltip_1"));
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    public static float getPullTime(ItemStack stack) {
        return 15.0F;
    }

    public static float getPullProgress(int useTicks, ItemStack stack) {
        // Copied shamelessly from BowItem, who knows what this is doing
        float f = ((float) useTicks) / getPullTime(stack);
        f = (f * f * f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }
        return f;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity caster) {
            float pullProgress = getPullProgress(this.getMaxUseTime(stack) - remainingUseTicks, stack);

            world.playSound(null, caster.getX(), caster.getY(), caster.getZ(), SoundEvents.ITEM_TRIDENT_THUNDER, SoundCategory.PLAYERS, 0.2F, 1.0F);
            world.addParticle(ParticleTypes.GLOW, caster.getX(), caster.getY(), caster.getZ(), 0.0, -1.0, 0.0);

            if (pullProgress >= 1.0) {
                HitResult result = caster.raycast(MAX_RANGE, 1.0f, false);

                BlockPos blockPos = new BlockPos(result.getPos());

                // Spawn lightning at the targeted spot, if possible
                if (world.isSkyVisible(blockPos)) {
                    if (!world.isClient) {
                        LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(world);
                        lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(blockPos.down()));

                        world.spawnEntity(lightningEntity);
                        world.playSound(null, blockPos, SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.WEATHER, 5.0f, 0.5f);

                        AreaEffectCloudEntity aoeCloud = EntityType.AREA_EFFECT_CLOUD.create(world);
                        aoeCloud.setPotion(Potions.HARMING);
                        aoeCloud.setRadius(2);
                        aoeCloud.setDuration(200);
                        aoeCloud.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(blockPos.down()));

                        world.spawnEntity(aoeCloud);
                    }

                    stack.damage(1, caster, (p) -> {
                        p.sendToolBreakStatus(caster.getActiveHand());
                    });
                }
            }
        }
    }

            @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity caster, Hand hand) {
        ItemStack stack = caster.getStackInHand(hand);
        caster.setCurrentHand(hand);
        return TypedActionResult.consume(stack);
    }
}
