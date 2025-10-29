package com.lemonbunzz.apikinesismod.player;


import com.lemonbunzz.apikinesismod.ApikinesisMod;
import com.lemonbunzz.apikinesismod.networking.ModidPacketHandler;
import com.lemonbunzz.apikinesismod.skills.TameBee;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ApikinesisMod.MODID, value = Dist.CLIENT)
public class ClientInputHandler {
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) { // Only call code once as the tick event is called twice every tick
            while (KeyMappings.ACTIVATE_POWER.consumeClick()) {
                //ModidPacketHandler.INSTANCE.sendToServer();
            }
        }
    }
}
