package com.lemonbunzz.apikinesismod.skill;

import com.lemonbunzz.apikinesismod.ApikinesisMod;
import com.lemonbunzz.apikinesismod.skill.skill_manager.AbstractSkill;
import com.lemonbunzz.apikinesismod.skill.skill_manager.SkillType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class TameBee extends AbstractSkill {
    private final ResourceLocation spellID = ResourceLocation.fromNamespaceAndPath(ApikinesisMod.MODID, "tame_bee");

    public TameBee()
    {
        this.skillType = SkillType.ACTIVE;
        this.skillName = "Tame Bee";
        this.skillDescription = "Allows you to tame bees".describeConstable();
    }

    @Override
    public void onCast(Level world, ServerPlayer player, LivingEntity target) {
        System.out.println("Released bee"+player.getUUID());
    }

    @Override
    public boolean shouldCastingContinue() {
        return true;
    }
}
