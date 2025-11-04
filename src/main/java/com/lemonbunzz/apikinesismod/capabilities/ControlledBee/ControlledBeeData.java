package com.lemonbunzz.apikinesismod.capabilities.ControlledBee;

import net.minecraft.nbt.CompoundTag;

import java.util.UUID;

public class ControlledBeeData {
    private boolean isControlled = false;
    private UUID controlledBy = null;

    public boolean IsControlled() {
        return this.isControlled;
    }
    public void setIsControlled(boolean value) {
        this.isControlled = value;
        System.out.println("The bee is no longer controlled");
    };

    public UUID getControlledBy() {
        return this.controlledBy;
    }
    public void setControlledBy(UUID newOwnerUuid){
        this.controlledBy = newOwnerUuid;
        System.out.println("Removed the controller");
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
        this.isControlled = tag.getBoolean("isControlled");
        this.controlledBy = tag.getUUID("controlledBy");
    }
}
