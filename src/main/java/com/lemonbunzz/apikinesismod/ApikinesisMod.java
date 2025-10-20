package com.lemonbunzz.apikinesismod;

import com.lemonbunzz.apikinesismod.commands.ApikineticDebug;
import com.lemonbunzz.apikinesismod.item.ModItems;
import com.lemonbunzz.apikinesismod.item.ModToolsCreativeTabs;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ApikinesisMod.MODID)
public class ApikinesisMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "apikinesismod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public ApikinesisMod(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        IEventBus forgeEventbus = MinecraftForge.EVENT_BUS;
        //Items
        ModItems.register(modEventBus);
        ModToolsCreativeTabs.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(ModItems::addCreative);

        forgeEventbus.addListener(ApikinesisMod::onRegisterCommands);

        //unwanted Child
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
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
