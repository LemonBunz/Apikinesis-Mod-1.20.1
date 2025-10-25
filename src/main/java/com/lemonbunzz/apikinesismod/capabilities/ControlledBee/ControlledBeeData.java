package com.lemonbunzz.apikinesismod.capabilities.ControlledBee;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.animal.Bee;

import java.util.UUID;

public class ControlledBeeData {

    private boolean isControlled;
    private UUID controlledBy;

    public boolean getIsControlled() {
        return this.isControlled;
    }
    public void setIsControlled(boolean value) {
        this.isControlled = value;
    };

    public UUID getControlledBy() {
        return this.controlledBy;
    }
    public void setControlledBy(UUID newOwnerUuid){
        this.controlledBy = newOwnerUuid;
    }

    // Save data to NBT
    public void saveNBTData(CompoundTag tag) {
        if (this.isControlled) {
            tag.putBoolean("isControlled", true);
            if (this.controlledBy != null) {
                tag.putUUID("controlledBy", this.controlledBy);
            }
        }
    }

    // Load data from NBT
    public void loadNBTData(CompoundTag tag) {
        isControlled = tag.getBoolean("isControlled");
        controlledBy = tag.getUUID("controlledBy");
    }
}
