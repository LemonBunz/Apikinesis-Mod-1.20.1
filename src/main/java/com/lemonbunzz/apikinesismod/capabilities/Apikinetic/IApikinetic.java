package com.lemonbunzz.apikinesismod.capabilities.Apikinetic;

import net.minecraft.nbt.CompoundTag;

public interface IApikinetic {
    int getEnergyPoint();
    void setEnergyPoint(int value);

    boolean isApikinetic();
    void  setApikinetic(boolean value);

}
