package com.lemonbunzz.apikinesismod.networking;

import com.lemonbunzz.apikinesismod.ApikinesisMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

@SuppressWarnings("'ResourceLocation(java.lang.String, java.lang.String)' is deprecated since version 1.20.6 and marked for removal")
public class ModidPacketHandler  {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(ApikinesisMod.MODID, "client_input"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );


    private static int packetId = 0;
    public static void registerPackets() {
        CHANNEL.registerMessage(
                packetId++,
                SkillCastPacket.class,
                SkillCastPacket::encode,
                SkillCastPacket::decode,
                SkillCastPacket::handle
        );

        //TODO: REGISTER MORE HERE!!!!!!!!
    }
}
