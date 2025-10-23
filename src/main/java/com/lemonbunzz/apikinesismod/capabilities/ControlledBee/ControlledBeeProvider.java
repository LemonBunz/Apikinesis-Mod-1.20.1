package com.lemonbunzz.apikinesismod.capabilities.ControlledBee;

import com.lemonbunzz.apikinesismod.capabilities.Apikinetic.ApikineticCapability;
import com.lemonbunzz.apikinesismod.capabilities.Apikinetic.ApikineticData;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class ControlledBeeProvider implements ICapabilitySerializable<CompoundTag> {
    private final ControlledBeeData controlledBeeData = new ControlledBeeData();
    private final LazyOptional<ControlledBeeData> optional = LazyOptional.of(() -> controlledBeeData);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == ControlledBeeCapability.CONTROLLED ? optional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        controlledBeeData.saveNBTData(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        controlledBeeData.loadNBTData(nbt);
    }
}