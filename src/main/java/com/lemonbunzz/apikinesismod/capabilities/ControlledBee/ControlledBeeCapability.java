package com.lemonbunzz.apikinesismod.capabilities.ControlledBee;

import com.lemonbunzz.apikinesismod.ApikinesisMod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ApikinesisMod.MODID)
public class ControlledBeeCapability {
    public static Capability<ControlledBeeData> CONTROLLED = null;

    @SubscribeEvent
    @SuppressWarnings({"'ResourceLocation(java.lang.String, java.lang.String)' is deprecated since version 1.20.6 and marked for removal"})
    public static void attachControlledBeeCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Bee) {
            CONTROLLED = CapabilityManager.get(new CapabilityToken<>() {});

            event.addCapability(new ResourceLocation(
                    ApikinesisMod.MODID, "controlled_bee_data"),
                    new ControlledBeeProvider()
            );
        }
    }

//    @SubscribeEvent
//    public static void onPlayerClone(PlayerEvent.Clone event) {
//        event.getOriginal().getCapability(ControlledBeeCapability.IS_CONTROLLED).ifPresent(oldData -> {
//            event.getEntity().getCapability(ControlledBeeCapability.IS_CONTROLLED).ifPresent(newData -> {
//                CompoundTag tag = new CompoundTag();
//                ((ControlledBeeData) oldData).saveNBTData(tag);
//                ((ControlledBeeData) newData).loadNBTData(tag);
//            });
//        });
//    }
}