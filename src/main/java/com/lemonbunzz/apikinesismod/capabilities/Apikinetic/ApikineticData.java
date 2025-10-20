package com.lemonbunzz.apikinesismod.capabilities.Apikinetic;

import net.minecraft.nbt.CompoundTag;

public class  ApikineticData implements IApikinetic {
    private boolean isApikinetic = false;
    private int energyPoint = 100;

    public int getEnergyPoint(){
        return energyPoint;
    }
    public void setEnergyPoint(int value) {
        this.energyPoint = value;
    };

    public boolean isApikinetic() {
        return this.isApikinetic;
    }
    public void setApikinetic(boolean value){
        this.isApikinetic = value;
    }

    // Save data to NBT
    public void saveNBTData(CompoundTag tag) {
        tag.putBoolean("isApikinetic", this.isApikinetic);
        tag.putInt("energyPoint", this.energyPoint);
    }

    // Load data from NBT
    public void loadNBTData(CompoundTag tag) {
        isApikinetic = tag.getBoolean("isApikinetic");
        energyPoint = tag.getInt("energyPoint");
    }
}
