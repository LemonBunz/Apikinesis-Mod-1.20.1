package com.lemonbunzz.apikinesismod.mobs.bee;

import com.lemonbunzz.apikinesismod.capabilities.Apikinetic.ApikineticCapability;
import com.lemonbunzz.apikinesismod.capabilities.Apikinetic.ApikineticData;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class BeeFollowApikineticGoal extends Goal {
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
                this.target = thisPlayer;
                return true;
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

