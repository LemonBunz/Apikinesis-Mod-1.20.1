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
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = ApikinesisMod.MODID)
public class BeeMobEdit {
    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Bee bee) {
            //Reason: Remove

            //Reason: Replace
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
        bee.goalSelector.addGoal(10, new BeeFollowApikineticGoal(bee, 1.0D, 5.0F, 2.0F));
        bee.targetSelector.addGoal(1, (new BeeHurtByApikineticTargetGoal(bee)).setAlertOthers(new Class[0]));
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
        if (target != null) {
            double distanceSq = bee.distanceToSqr(target);

            // Minimum distance to stop moving toward the player
            double safeDistance = minDist;

            // If the target is a player, add extra buffer to prevent bumping
            if (target != null) {
                safeDistance += 0.5; // 0.5 blocks buffer
            }

            if (distanceSq > (safeDistance * safeDistance)) {
                bee.getNavigation().moveTo(target, speed);
            } else {
                // Stop the bee when within safe distance
                bee.getNavigation().stop();
            }
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
    protected void alertOther(@NotNull Mob pMob, @NotNull LivingEntity pTarget) {
        if (pMob instanceof Bee && this.mob.hasLineOfSight(pTarget)) {
            //this.bee = bee that got hurt
            //pMob are the surrounding bees, not the bee that got hurt
            boolean isControlled = this.bee.getCapability(ControlledBeeCapability.CONTROLLED).map(controlled ->
                    controlled.getControlledBy() != null)
                    .orElse(false);

            boolean isTheAttackerOwner = pMob.getCapability(ControlledBeeCapability.CONTROLLED).map(controlled ->
                            controlled.getControlledBy() != pTarget.getUUID())
                    .orElse(false);

            if (!isControlled && isTheAttackerOwner) { //If the bee not is owned
                //pMob = Outsider bees
                pMob.setTarget(pTarget);
            } else {  //If the bee  is owned
                //pMob = Ally/Owned bees

                //TODO: this is temporary; ignore owner when bee is attacked;
                this.bee.setTarget(null);
                //pMob.setTarget(pTarget); --This is unnecessary but it's here anyways...
            }

        }
    }

}
