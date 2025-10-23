package com.lemonbunzz.apikinesismod.capabilities.Apikinetic;

import net.minecraft.nbt.CompoundTag;

public class  ApikineticData implements IApikinetic {
    private boolean isApikinetic = false;
    private int energyPoint = 100;
    private int maxEnergyPoint = 100;
    private int energyRegenRate = 10; //energy% per second
    private ApikineticControlledBees availableBees = new ApikineticControlledBees(5);
    //Apikinetic Status
    public boolean isApikinetic() {
        return this.isApikinetic;
    }
    public void setApikinetic(boolean value){
        this.isApikinetic = value;
    }

    //Energy Manager
    public int getEnergyPoint(){
        return energyPoint;
    }
    public void setEnergyPoint(int value) {
        this.energyPoint = value;
    };
    public int getMaxEnergyPoint(){
        return maxEnergyPoint;
    }
    public void setMaxEnergyPoint(int value) {
        this.maxEnergyPoint = value;
    };
    public int getEnergyRegenRate(){
        return energyRegenRate;
    }
    public void setEnergyRegenRate(int value) {
        this.energyRegenRate = value;
    };

    //Controlled Bees Controllers
    public ApikineticControlledBees getAvailableBees() { return availableBees; }

    // Save data to NBT
    public void saveNBTData(CompoundTag tag) {
        tag.putBoolean("isApikinetic", this.isApikinetic);
        tag.putInt("energyPoint", this.energyPoint);
        tag.putInt("maxEnergyPoint", this.maxEnergyPoint);
        tag.putInt("energyRegenRate", this.energyRegenRate);
    }

    // Load data from NBT
    public void loadNBTData(CompoundTag tag) {
        isApikinetic = tag.getBoolean("isApikinetic");
        energyPoint = tag.getInt("energyPoint");
        maxEnergyPoint = tag.getInt("energyPoint");
        energyRegenRate = tag.getInt("energyPoint");
    }
}


