package com.lemonbunzz.apikinesismod.networking;

import com.lemonbunzz.apikinesismod.capabilities.Apikinetic.ApikineticCapability;
import com.lemonbunzz.apikinesismod.capabilities.ControlledBee.ControlledBeeCapability;
import com.lemonbunzz.apikinesismod.mobs.bee.BeeData;
import com.lemonbunzz.apikinesismod.registries.mod_registries.ModSkills;
import com.lemonbunzz.apikinesismod.skill.skill_manager.AbstractSkill;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SkillCastPacket {
    ;
    private String skillID;

    public SkillCastPacket(String skillID) { this.skillID = skillID; }

    public static void encode(SkillCastPacket pkt, FriendlyByteBuf buf) {
        buf.writeUtf(pkt.skillID);
    }

    public static SkillCastPacket decode(FriendlyByteBuf buf) {
        return new SkillCastPacket(buf.readUtf(32767));
    }

    public static void handle(SkillCastPacket pkt, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            assert player != null;
            ServerLevel world = player.serverLevel();

            for (AbstractSkill skill :  ModSkills.SKILL_REGISTRY.get().getValues()) {
                System.out.println(skill.getSkillName());
            }

        });

        ctx.get().setPacketHandled(true);
    }
}

//            player.getCapability(ApikineticCapability.APIKINETIC).ifPresent(data -> {
//                for (BeeData beeData : data.getAvailableBees().getControllableBees()) {
//                    Entity bee = world.getEntity(beeData.getUuid());
//                    assert bee != null;
//
//                    bee.getCapability(ControlledBeeCapability.CONTROLLED).ifPresent(beeCapData -> {
//                        System.out.println(beeCapData.IsControlled() + " " + beeCapData.getControlledBy().toString());
//                        beeCapData.setControlledBy(null);
//                        beeCapData.setIsControlled(false);
//                    });
//                }
//
//                data.getAvailableBees().clearAllControllableBees();
//                System.out.println(data.getAvailableBees().getControllableBees().size());
//            });