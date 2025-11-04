package com.lemonbunzz.apikinesismod.mobs.bee;

import net.minecraft.nbt.CompoundTag;

import java.util.UUID;

public class BeeData {
    private final UUID beeUUID;

    public BeeData(UUID newBeeUuid) {
        this.beeUUID = newBeeUuid;
    };

    public UUID getUuid() {
        return this.beeUUID;
    }

    public CompoundTag saveNBT() {
        CompoundTag tag = new CompoundTag();
        if (this.beeUUID != null) tag.putUUID("beeUUID", this.beeUUID);
        return tag;
    }

    public static BeeData loadNBT(CompoundTag tag) {
        UUID uuid = tag.hasUUID("beeUUID") ? tag.getUUID("beeUUID") : null;
        return new BeeData(uuid);
    }
}
