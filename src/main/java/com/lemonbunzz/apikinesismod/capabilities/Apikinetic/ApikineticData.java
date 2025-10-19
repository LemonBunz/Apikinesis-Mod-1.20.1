package com.lemonbunzz.apikinesismod.capabilities.Apikinetic;

import net.minecraft.nbt.CompoundTag;

public class  ApikineticData implements IApikinetic {
    private boolean isApikinetic = false;
    private int energyPower = 100;

    public int getPowerEnergy(){
        return energyPower;
    }
    public void setPowerEnergy(int value) {
        this.energyPower = value;
    };

    public boolean isApikinetic() {
        return this.isApikinetic;
    }
    public void setApikinetic(boolean value){
        this.isApikinetic = value;
    }

    // Save data to NBT
    public void saveNBTData(CompoundTag tag) {
        tag.putBoolean("isApikinetic", isApikinetic);
        tag.putInt("energy", energyPower);
    }

    // Load data from NBT
    public void loadNBTData(CompoundTag tag) {
        isApikinetic = tag.getBoolean("isApikinetic");
        energyPower = tag.getInt("energyPower");
    }
}
