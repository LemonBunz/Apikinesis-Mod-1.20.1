package com.lemonbunzz.apikinesismod.capabilities.Apikinetic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class ApikineticControlledBees {
    private List<BeeData> controllableBees;
    private int maxCap;

    public ApikineticControlledBees(int max) {
        this.controllableBees = new ArrayList<>();
        this.maxCap = max;
    }

    public boolean canControlMoreBees() {
        return controllableBees.size() < maxCap;
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

    public void clearAll() {
        controllableBees.clear();
    }
}

class BeeData {
    private UUID uuid;

    public BeeData(UUID newUuid) {
        this.uuid = newUuid;
    };

    public UUID getUuid() {
        return uuid;
    }
}
