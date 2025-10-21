package com.lemonbunzz.apikinesismod.capabilities.Apikinetic;

import com.lemonbunzz.apikinesismod.ApikinesisMod;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ApikinesisMod.MODID)
public class EnergyRegenHandler {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        player.getCapability(ApikineticCapability.APIKINETIC).ifPresent(data -> {
            int energy = data.getEnergyPoint();
            if (player.tickCount % 20 == 0 && data.isApikinetic()) { // max energy
                int ep = data.getEnergyPoint();
                int max = data.getMaxEnergyPoint();
                int regen = data.getEnergyRegenRate();

                if (ep < max) {
                    data.setEnergyPoint(Math.min(energy + regen, max));
                }
            }
        });
    }
}
