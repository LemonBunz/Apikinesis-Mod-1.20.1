package com.lemonbunzz.apikinesismod.registries.mod_registries;


import com.lemonbunzz.apikinesismod.ApikinesisMod;
import com.lemonbunzz.apikinesismod.skill.ReleaseBee;
import com.lemonbunzz.apikinesismod.skill.TameBee;
import com.lemonbunzz.apikinesismod.skill.skill_manager.AbstractSkill;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;

import net.minecraftforge.registries.*;

import java.util.function.Supplier;

public class ModSkills {

    public static final ResourceKey<Registry<AbstractSkill>> SKILL_REGISTER_KEY =
            ResourceKey.createRegistryKey(new ResourceLocation(ApikinesisMod.MODID, "skills"));

    private static final DeferredRegister<AbstractSkill> SKILLS =
            DeferredRegister.create(SKILL_REGISTER_KEY, ApikinesisMod.MODID);

    public static final Supplier<IForgeRegistry<AbstractSkill>> SKILL_REGISTRY = SKILLS.makeRegistry(() ->
            new RegistryBuilder<AbstractSkill>()
                    .disableSaving()
                    .disableOverrides());


    public  static void register(IEventBus eventBus) {
        SKILLS.register(eventBus);
    }


    public static final RegistryObject<AbstractSkill> TAME_SKILL =
            SKILLS.register("tame_bee", TameBee::new);

    public static final RegistryObject<AbstractSkill> RELEASE_BEES =
            SKILLS.register("release_bee", ReleaseBee::new);
}


