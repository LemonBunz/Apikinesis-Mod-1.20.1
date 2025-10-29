package com.lemonbunzz.apikinesismod.skills;

import com.lemonbunzz.apikinesismod.skills.skillmanager.AbstractSkill;
import com.lemonbunzz.apikinesismod.skills.skillmanager.SkillType;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class TameBee extends AbstractSkill {
    public TameBee() {
        this.setSkillID("1_tame_bee_active");
        this.skillType = SkillType.ACTIVE;
        this.skillName = "Tame Bee";
        this.skillDescription = "Allows you to tame bees".describeConstable();
    }
    @Override
    public void apply(Player player) {
        System.out.println("Tamed Bee"+player.getUUID());
    }

    @Override
    public boolean shouldApplyContinue() {
        return true;
    }
}
