package com.lemonbunzz.apikinesismod.capabilities.Apikinetic;

import com.lemonbunzz.apikinesismod.capabilities.ControlledBee.ControlledBeeCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.animal.Bee;

import java.util.UUID;

public class  ApikineticData implements IApikinetic {

    private UUID player;
    private boolean isApikinetic = false;
    private int energyPoint = 100;
    private int maxEnergyPoint = 100;
    private int energyRegenRate = 10; //energy% per second
    private final ApikineticControlledBees availableBees = new ApikineticControlledBees(5);
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

    public void controlBee(Bee bee, UUID controllerUuid) {
        if (!availableBees.canControlMoreBees()) return;

        bee.getCapability(ControlledBeeCapability.CONTROLLED).ifPresent(controlled -> {
            controlled.setIsControlled(true);
            controlled.setControlledBy(controllerUuid);
            this.availableBees.register(bee.getUUID());
        });
    }

    // Save data to NBT
    public void saveNBTData(CompoundTag tag) {
        tag.putBoolean("isApikinetic", this.isApikinetic);
        tag.putInt("energyPoint", this.energyPoint);
        tag.putInt("maxEnergyPoint", this.maxEnergyPoint);
        tag.putInt("energyRegenRate", this.energyRegenRate);
        tag.put("availableBees", availableBees.saveNBT());
    }

    // Load data from NBT
    public void loadNBTData(CompoundTag tag) {
        isApikinetic = tag.getBoolean("isApikinetic");
        energyPoint = tag.getInt("energyPoint");
        maxEnergyPoint = tag.getInt("energyPoint");
        energyRegenRate = tag.getInt("energyPoint");

        if (tag.contains("availableBees", Tag.TAG_COMPOUND)) {
            availableBees.loadNBT(tag.getCompound("availableBees"));
        }
    }
}


