package com.lemonbunzz.apikinesismod.item;

import com.lemonbunzz.apikinesismod.capabilities.Apikinetic.ApikineticCapability;
import com.lemonbunzz.apikinesismod.capabilities.Apikinetic.ApikineticControlledBees;
import com.lemonbunzz.apikinesismod.capabilities.ControlledBee.ControlledBeeCapability;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ModBeeStaff extends Item {
    public ModBeeStaff(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        int epCost = 25;
        if (!level.isClientSide) {
            player.getCapability(ApikineticCapability.APIKINETIC).ifPresent(data -> {
                int ep = data.getEnergyPoint();
                ApikineticControlledBees AvailableBees = data.getAvailableBees();
                if (data.isApikinetic()) {
                    if (data.getEnergyPoint() >= epCost) {
                        Bee newBee = EntityType.BEE.create(level);
                        if (newBee != null) {
                            newBee.moveTo(player.getX(), player.getY() + 1, player.getZ(), player.getYRot(), 0);
                            level.addFreshEntity(newBee);
                            data.setEnergyPoint(ep - epCost);
                            //test section
                            data.controlBee(newBee, player.getUUID());
                            newBee.setTarget(player);
                            newBee.getCapability(ControlledBeeCapability.CONTROLLED).ifPresent(controlled -> {
                                String controlledby = controlled.getControlledBy() != null ? controlled.getControlledBy().toString() : "no owner";
                                player.sendSystemMessage(Component.literal(
                                        "[Debug] Cntrolled By " + controlledby +  " " + controlled.getIsControlled()
                                ));
                            });
                        }
                    } else {
                        player.sendSystemMessage(Component.literal("Insufficient Energy Points"));
                    }

                } else {
                    player.sendSystemMessage(Component.literal("You don't have the power to use this staff."));
                }
            });
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }
}
