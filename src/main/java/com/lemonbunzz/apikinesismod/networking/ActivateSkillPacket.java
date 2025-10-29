package com.lemonbunzz.apikinesismod.networking;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ActivateSkillPacket {
    private String skillID;

    public ActivateSkillPacket() {}
    public ActivateSkillPacket(String skillID) { this.skillID = skillID; }
    public static void encode(ActivateSkillPacket pkt, FriendlyByteBuf buf) {
        buf.writeUtf(pkt.skillID);
    }
    public static ActivateSkillPacket decode(FriendlyByteBuf buf) {
        return new ActivateSkillPacket(buf.readUtf(32767));
    }
    public static void handle(ActivateSkillPacket pkt, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();

        });
        ctx.get().setPacketHandled(true);
    }
}
