package com.lemonbunzz.apikinesismod.client;


import com.lemonbunzz.apikinesismod.ApikinesisMod;
import com.lemonbunzz.apikinesismod.networking.ModidPacketHandler;
import com.lemonbunzz.apikinesismod.networking.SkillCastPacket;
import com.lemonbunzz.apikinesismod.player.KeyMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ApikinesisMod.MODID, value = Dist.CLIENT)
public class ClientInputHandler {

    private static final SkillCastPacket skillCastPacket = new SkillCastPacket("dsd"); //Ignore the skillID for now

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) { // Only call code once as the tick event is called twice every tick
            while (KeyMappings.PREVIOUS_SKILL.consumeClick()) {
                ModidPacketHandler.CHANNEL.sendToServer(skillCastPacket);
            }
        }
    }
}
