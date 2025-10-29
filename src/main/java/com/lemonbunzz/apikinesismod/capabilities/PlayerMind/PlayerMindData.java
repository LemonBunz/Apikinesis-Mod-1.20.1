package com.lemonbunzz.apikinesismod.capabilities.PlayerMind;

import com.lemonbunzz.apikinesismod.skills.skillmanager.AbstractSkill;

import java.util.HashMap;
import java.util.Map;

public class PlayerMindData implements IPlayerMind {
    private Map<String, AbstractSkill> skills = new HashMap<>();

    public Map<String, AbstractSkill> getSkills() {
        return skills;
    };


}
