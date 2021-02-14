package com.Wind__Runner.GreekMyths.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class OreGenConfig {
    public static ForgeConfigSpec.IntValue silver_ore_chance;
    public static ForgeConfigSpec.IntValue copper_ore_chance;
    public static ForgeConfigSpec.IntValue tin_ore_chance;

    public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client){
        server.comment("Ore Generation Config");

        silver_ore_chance = server
                .comment("Maximum number of silver viens in a single chunk.")
                .defineInRange("oreGen.silver_ore_chance", 30,1, 10000);

        copper_ore_chance = server
                .comment("Maximum number of copper viens in a single chunk.")
                .defineInRange("oreGen.copper_ore_chance", 30,1, 10000);

        tin_ore_chance = server
                .comment("Maximum number of copper viens in a single chunk.")
                .defineInRange("oreGen.copper_ore_chance", 30,1, 10000);
    }
}
