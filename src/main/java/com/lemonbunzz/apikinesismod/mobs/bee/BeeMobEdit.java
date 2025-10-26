package com.lemonbunzz.apikinesismod.mobs.bee;


import com.lemonbunzz.apikinesismod.ApikinesisMod;
import com.lemonbunzz.apikinesismod.capabilities.Apikinetic.ApikineticCapability;
import com.lemonbunzz.apikinesismod.capabilities.Apikinetic.ApikineticData;
import com.lemonbunzz.apikinesismod.capabilities.ControlledBee.ControlledBeeCapability;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = ApikinesisMod.MODID)
public class BeeMobEdit {
    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Bee bee) {

            removeGoalWithNameContaining(bee.targetSelector,"BeeHurtByOtherGoal");

            alterBeeBehavior(bee);

            Set<WrappedGoal> goalsCopy = new HashSet<>(bee.targetSelector.getAvailableGoals());
            for (WrappedGoal wrapped : goalsCopy) {
                Goal goal = wrapped.getGoal();
                System.out.println(goal.getClass().getSimpleName());
            }
        }
    }

    @SubscribeEvent
    public static void onEntityDeath(EntityLeaveLevelEvent event) {
        if (event.getEntity() instanceof Bee bee) {

        }
    }

    private static void alterBeeBehavior(Bee bee) {
        bee.goalSelector.addGoal(1, new BeeFollowApikineticGoal(bee, 1.0D, 5.0F, 2.0F));
        //bee.targetSelector.addGoal(1, (new BeeHurtByApikineticTargetGoal(bee)).setAlertOthers());
    }

    public static void removeGoalWithNameContaining(GoalSelector selector, String containsName) {
        Set<WrappedGoal> goalsCopy  = new HashSet<>(selector.getAvailableGoals());
        for (WrappedGoal wrapped : goalsCopy) {
            Goal goal = wrapped.getGoal();
            if (goal.getClass().getSimpleName().contains(containsName)) {
                selector.removeGoal(goal);
                System.out.println("[Debugging] "+goal.getClass().getSimpleName());
            }
        }
    }
}

class BeeFollowApikineticGoal extends Goal {
    private final Bee bee;
    private Player target;
    private final double speed;
    private final float minDist;
    private final float maxDist;

    public BeeFollowApikineticGoal(Bee bee, double speed, float minDist, float maxDist) {
        this.bee = bee;
        this.speed = speed;
        this.minDist = minDist;
        this.maxDist = maxDist;
    }

    @Override
    public boolean canUse() {
        // Find the nearest Apikinetic player
        List<Player> players = bee.level().getEntitiesOfClass(Player.class, bee.getBoundingBox().inflate(16));
        for (Player thisPlayer : players) {
            boolean isApikinetic = thisPlayer.getCapability(ApikineticCapability.APIKINETIC)
                    .map(ApikineticData::isApikinetic)
                    .orElse(false);

            if (isApikinetic) {

                boolean isControlledByPlayer = bee.getCapability(ControlledBeeCapability.CONTROLLED)
                        .map(controlled -> controlled.getControlledBy() != null
                                && controlled.getControlledBy().equals(thisPlayer.getUUID()))
                        .orElse(false);

                if (isControlledByPlayer) {
                    this.target = thisPlayer;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void tick() {
        if (target != null && bee.distanceToSqr(target) > (minDist * minDist)) {
            bee.getNavigation().moveTo(target, speed);
        }
    }

    @Override
    public boolean canContinueToUse() {
        return target != null && bee.distanceToSqr(target) < (maxDist * maxDist);
    }
}


class BeeHurtByApikineticTargetGoal extends HurtByTargetGoal {
    private final Bee bee;
    BeeHurtByApikineticTargetGoal(Bee pMob)
    {
        super(pMob);
        this.bee = pMob;
    }
    @Override
    public boolean canContinueToUse() {
        return this.bee.isAngry() && super.canContinueToUse();
    }
    @Override
    protected void alertOther(Mob pMob, LivingEntity pTarget) {
        if (pMob instanceof Bee && this.mob.hasLineOfSight(pTarget)) {
            pMob.setTarget(pTarget);
        }
    }
}
