package com.Wind__Runner.GreekMyths.config;

import com.Wind__Runner.GreekMyths.GreekMyths;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.io.File;

@Mod.EventBusSubscriber(modid = GreekMyths.MOD_ID)
public class Config {
    private static final ForgeConfigSpec.Builder serverBuilder = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec serverConfig;

    private static final ForgeConfigSpec.Builder clientBuilder = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec clientConfig;

    static {
        OreGenConfig.init(serverBuilder, clientBuilder);
        serverConfig = serverBuilder.build();
        clientConfig = clientBuilder.build();
    }

    public static void loadConfig(ForgeConfigSpec config, String path) {
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig(file);
    }
}
