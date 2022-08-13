package ianplu.whizzy_wands.config;

public class WhizzyWandsConfig extends MidnightConfig {
    // LAUNCH WAND
    @Comment public static Comment launch_wand_scalar_comment;
    @Entry public static float launch_wand_scalar = 1.0f;
    @Comment public static Comment launch_wand_durability_comment;
    @Entry public static int launch_wand_durability = 256;

    // ROCKET WAND
    @Comment public static Comment rocket_wand_scalar_comment;
    @Entry public static float rocket_wand_scalar = 1.0f;
    @Comment public static Comment rocket_wand_durability_comment;
    @Entry public static int rocket_wand_durability = 256;

    // LIGHTNING WAND
    @Comment public static Comment lightning_wand_durability_comment;
    @Entry public static int lightning_wand_durability = 256;
}
