package com.lemonbunzz.apikinesismod;

import com.lemonbunzz.apikinesismod.commands.ApikineticDebug;
import com.lemonbunzz.apikinesismod.networking.ModidPacketHandler;
import com.lemonbunzz.apikinesismod.registries.ModItems;
import com.lemonbunzz.apikinesismod.registries.ModToolsCreativeTabs;
import com.lemonbunzz.apikinesismod.registries.mod_registries.ModSkills;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ApikinesisMod.MODID)
public class ApikinesisMod
{
    public static final String MODID = "apikinesismod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ApikinesisMod(FMLJavaModLoadingContext context)
    {
        ModidPacketHandler.registerPackets();

        IEventBus modEventBus = context.getModEventBus();
        IEventBus forgeEventbus = MinecraftForge.EVENT_BUS;
        //Items
        ModSkills.register(modEventBus);
        ModItems.register(modEventBus);
        ModToolsCreativeTabs.register(modEventBus);


        modEventBus.addListener(ModItems::addCreative);

        forgeEventbus.addListener(ApikinesisMod::onRegisterCommands);

        MinecraftForge.EVENT_BUS.register(this);


        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        ApikineticDebug.register(event.getDispatcher());
    }
}
