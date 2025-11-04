package com.lemonbunzz.apikinesismod.skill.skill_manager;

import com.lemonbunzz.apikinesismod.ApikinesisMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Optional;


public abstract class AbstractSkill {
    public ResourceLocation spellID = null;
    protected SkillType skillType;
    protected String skillName;
    protected Optional<String> skillDescription;

    public AbstractSkill() {}

    //getter/setters
    public SkillType getSkillType() { return this.skillType; }
    public void setSkillType(SkillType value) { this.skillType = value; }
    public String getSkillName() { return this.skillName; }
    public void setSkillName(String value) { this.skillName = value; }


    public abstract void onCast(Level world, ServerPlayer caster, LivingEntity target);

    public abstract boolean shouldCastingContinue();

    //TODO: Make a better targeting system
    public void tick(Level world, ServerPlayer player, LivingEntity target) {
        if (shouldCastingContinue()) {
            onCast(world, player, target);
        }
    }
}

//SkillType