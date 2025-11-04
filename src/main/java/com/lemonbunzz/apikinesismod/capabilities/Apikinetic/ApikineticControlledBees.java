package com.lemonbunzz.apikinesismod.capabilities.Apikinetic;

import com.lemonbunzz.apikinesismod.mobs.bee.BeeData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import java.util.*;


public class ApikineticControlledBees {
    private List<BeeData> controllableBees;
    private int maxCap;

    public ApikineticControlledBees(int max) {
        this.controllableBees = new ArrayList<>();
        this.maxCap = max;
    }

    public Collection<BeeData> getControllableBees() {
        return this.controllableBees;
    }

    public boolean canControlMoreBees() {
        return this.controllableBees.size() < maxCap;
    }

    public int setMax(int value) {
        return this.maxCap = value;
    }
    public int getMax() {
        return this.maxCap;
    }
    public void register(UUID uuid) {
        //TODO: check if the bee has already been controlled by someone
        if (this.canControlMoreBees()) {
            this.controllableBees.add(new BeeData(uuid));
        }
    }

    public void clearAllControllableBees() {
        this.controllableBees.clear();
    }

    public CompoundTag saveNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("maxCap", this.maxCap);

        ListTag beeList = new ListTag();
        for (BeeData bee : this.controllableBees) {
            beeList.add(bee.saveNBT());
        }

        tag.put("controlledBees", beeList);
        return tag;
    }

    public void loadNBT(CompoundTag tag) {
        this.clearAllControllableBees();
        if (tag.contains("controlledBees", Tag.TAG_LIST)) {
            tag.getInt("maxCap");
            ListTag beeList = tag.getList("controlledBees", Tag.TAG_COMPOUND);
            for (int i = 0; i < beeList.size(); i++) {
                CompoundTag beeTag = beeList.getCompound(i);
                this.controllableBees.add(BeeData.loadNBT(beeTag));
            }
        }
    }
}

