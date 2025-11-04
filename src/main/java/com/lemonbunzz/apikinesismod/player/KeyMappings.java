package com.lemonbunzz.apikinesismod.player;


import com.lemonbunzz.apikinesismod.ApikinesisMod;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = ApikinesisMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = net.minecraftforge.api.distmarker.Dist.CLIENT)
public class KeyMappings {
    public static final String CATEGORY = "key.categories.apikinesismod";
    public static KeyMapping PREVIOUS_SKILL;
    public static KeyMapping NEXT_SKILL;
    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        PREVIOUS_SKILL= new KeyMapping(
               "key.apikinesismod.previous_skill",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                CATEGORY
        );
        NEXT_SKILL= new KeyMapping(
                "key.apikinesismod.next_skill",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_H,
                CATEGORY
        );

        event.register(PREVIOUS_SKILL);
        event.register(NEXT_SKILL);
    }
}
