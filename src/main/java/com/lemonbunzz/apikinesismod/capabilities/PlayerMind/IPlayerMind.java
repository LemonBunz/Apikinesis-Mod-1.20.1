package com.lemonbunzz.apikinesismod.capabilities.PlayerMind;


import com.lemonbunzz.apikinesismod.skills.skillmanager.AbstractSkill;

import java.util.HashMap;
import java.util.Map;

public interface IPlayerMind {
    Map<String, AbstractSkill> skills = new HashMap<>();

    Map<String, AbstractSkill> getSkills();
}
