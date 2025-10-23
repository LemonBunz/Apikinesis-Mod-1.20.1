package com.lemonbunzz.apikinesismod.mobs.bee;


import com.lemonbunzz.apikinesismod.ApikinesisMod;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = ApikinesisMod.MODID)
public class BeeMobEdit {

    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Bee bee) {
            //alterBeeBehavior(bee);
        }
    }

    @SubscribeEvent
    public static void onEntityDeath(EntityLeaveLevelEvent event) {
        if (event.getEntity() instanceof Bee bee) {

        }
    }

    private static void alterBeeBehavior(Bee bee) {
        bee.goalSelector.addGoal(1, new BeeFollowApikineticGoal(bee, 1.0D, 5.0F, 2.0F));
    }

    public static void removeGoalWithNameContaining(Bee bee, String containsName) {
        Set<WrappedGoal> goalsCopy = new HashSet<>(bee.goalSelector.getAvailableGoals());
        for (WrappedGoal wrapped : goalsCopy) {
            Goal goal = wrapped.getGoal();
            if (goal.getClass().getSimpleName().contains(containsName)) {
                bee.goalSelector.removeGoal(goal);
            }
        }
    }
}

class ControlledBeeData {

}