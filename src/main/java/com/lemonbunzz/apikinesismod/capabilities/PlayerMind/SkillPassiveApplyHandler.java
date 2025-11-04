package com.lemonbunzz.apikinesismod.capabilities.PlayerMind;


import com.lemonbunzz.apikinesismod.ApikinesisMod;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ApikinesisMod.MODID)
public class SkillPassiveApplyHandler {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        //activate passive skill

    }
}
