package com.lemonbunzz.apikinesismod.capabilities.ControlledBee;

import java.util.UUID;

public interface IControlledBee {
    boolean getIsControlled();
    void setIsControlled(boolean value);

    UUID getControlledBy();
    void getControlledBy(UUID newOwnerUuid);
}
