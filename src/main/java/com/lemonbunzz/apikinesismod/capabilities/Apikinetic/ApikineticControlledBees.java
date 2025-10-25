package com.lemonbunzz.apikinesismod.capabilities.Apikinetic;

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
        return controllableBees;
    }

    public boolean canControlMoreBees() {
        return controllableBees.size() < maxCap;
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
            controllableBees.add(new BeeData(uuid));
        }
    }

    public void remove(UUID uuid) {
        if (!controllableBees.isEmpty()) {
            Iterator<BeeData> iterator = controllableBees.iterator();
            while (iterator.hasNext()) {
                BeeData data = iterator.next();
                if (data.getUuid().equals(uuid)) {
                    iterator.remove();
                    break;
                }
            }
        };
    }

    public void clearAllControllableBees() {
        controllableBees.clear();
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
                controllableBees.add(BeeData.loadNBT(beeTag));
            }
        }
    }
}

class BeeData {
    private UUID beeUUID;

    public BeeData(UUID newBeeUuid) {
        this.beeUUID = newBeeUuid;
    };

    public UUID getUuid() {
        return this.beeUUID;
    }

    public CompoundTag saveNBT() {
        CompoundTag tag = new CompoundTag();
        if (this.beeUUID != null) tag.putUUID("beeUUID", this.beeUUID);
        return tag;
    }

    public static BeeData loadNBT(CompoundTag tag) {
        UUID uuid = tag.hasUUID("beeUUID") ? tag.getUUID("beeUUID") : null;
        return new BeeData(uuid);
    }
}
