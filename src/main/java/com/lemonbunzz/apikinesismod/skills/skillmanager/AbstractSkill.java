package com.lemonbunzz.apikinesismod.skills.skillmanager;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import java.util.Optional;


public abstract class AbstractSkill {
    private String skillID;
    protected SkillType skillType;
    protected String skillName;
    protected Optional<String> skillDescription;

    public AbstractSkill(String skillID, SkillType skillType, String skillName, String skillDescription) {
        this.skillID =skillID;
        this.skillType = skillType;
        this.skillName = skillName;
        this.skillDescription = skillDescription.describeConstable();
    }


    public AbstractSkill() {
    }

    //getter/setters
    public String getSkillID() { return this.skillID; }
    public void setSkillID(String value) { this.skillID = value; }
    public SkillType getSkillType() { return this.skillType; }
    public void setSkillType(SkillType value) { this.skillType = value; }
    public String getSkillName() { return this.skillName; }
    public void setSkillName(String value) { this.skillName = value; }


    public abstract void apply(Player caster);
    public void apply(Player caster, LivingEntity target) {

    };

    public abstract boolean shouldApplyContinue();

    public void tick(Player player) {
        if (shouldApplyContinue()) {
            apply(player);
        }
    }
}

//SkillType