package cn.ksmcbrigade.dr;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

@Mod(DampReborn.MODID)
public class DampReborn
{
    public static final String MODID = "dr";

    public static final ModConfigSpec COMMON_CONFIG;
    public static final ModConfigSpec.BooleanValue creeperDampness;
    public static final ModConfigSpec.BooleanValue tntDampness;
    public static final ModConfigSpec.IntValue creeperMaxSwell;
    public static final ModConfigSpec.IntValue tntMaxFuse;

    static {
        final var builder = new ModConfigSpec.Builder();
        builder.comment("QuickPlant Configuration");
        creeperDampness = builder.comment("Damp Configuration", "Activate creeper dampness")
                .define("creeper_dampness", true);
        tntDampness = builder.comment("Activate TNT dampness")
                .define("tnt_dampness", true);
        creeperMaxSwell = builder.comment("What's the max fuse time when the creeper reaches his max dampness")
                .defineInRange("creeper_swell", 2000, 100, Integer.MAX_VALUE);
        tntMaxFuse = builder.comment("What's the max fuse time when the primed tnt reaches his max dampness")
                .defineInRange("tnt_fuse", 2000, 100, Integer.MAX_VALUE);
        COMMON_CONFIG = builder.build();
    }

    public DampReborn(IEventBus modEventBus, ModContainer modContainer)
    {
        modContainer.registerConfig(ModConfig.Type.COMMON,COMMON_CONFIG);
    }
}
