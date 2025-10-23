package com.lemonbunzz.apikinesismod.capabilities.Apikinetic;

import net.minecraft.nbt.CompoundTag;

public interface IApikinetic {
    boolean isApikinetic();
    void  setApikinetic(boolean value);

    int getEnergyPoint();
    void setEnergyPoint(int value);
    int getMaxEnergyPoint();
    void setMaxEnergyPoint(int value);
    int getEnergyRegenRate();
    void setEnergyRegenRate(int value);
}
