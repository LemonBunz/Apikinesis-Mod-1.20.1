package com.lemonbunzz.apikinesismod.capabilities.Apikinetic;

import com.lemonbunzz.apikinesismod.ApikinesisMod;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;



@Mod.EventBusSubscriber(modid = ApikinesisMod.MODID)
public class ApikineticCapability {
    public static Capability<ApikineticData> APIKINETIC = null;

    @SubscribeEvent
    public static void attachApikineticCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            APIKINETIC = CapabilityManager.get(new CapabilityToken<>() {});

            event.addCapability(new ResourceLocation(ApikinesisMod.MODID, "apikinetic_data"), new ApikineticProvider());
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        event.getOriginal().getCapability(ApikineticCapability.APIKINETIC).ifPresent(oldData -> {
            event.getEntity().getCapability(ApikineticCapability.APIKINETIC).ifPresent(newData -> {
                CompoundTag tag = new CompoundTag();
                ((ApikineticData) oldData).saveNBTData(tag);
                ((ApikineticData) newData).loadNBTData(tag);
            });
        });
    }
}



