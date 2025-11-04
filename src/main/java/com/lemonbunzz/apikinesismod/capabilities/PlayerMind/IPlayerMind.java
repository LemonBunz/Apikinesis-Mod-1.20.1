package com.lemonbunzz.apikinesismod.capabilities.PlayerMind;


import com.lemonbunzz.apikinesismod.skill.skill_manager.AbstractSkill;

import java.util.HashMap;
import java.util.Map;

public interface IPlayerMind {
    Map<String, AbstractSkill> skills = new HashMap<>();

    Map<String, AbstractSkill> getSkills();
}
