package com.lemonbunzz.apikinesismod.capabilities.Apikinetic;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class ApikineticProvider implements ICapabilitySerializable<CompoundTag> {
    private final ApikineticData apikineticData = new ApikineticData();
    private final LazyOptional<ApikineticData> optional = LazyOptional.of(() -> apikineticData);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == ApikineticCapability.APIKINETIC ? optional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        apikineticData.saveNBTData(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        apikineticData.loadNBTData(nbt);
    }
}

