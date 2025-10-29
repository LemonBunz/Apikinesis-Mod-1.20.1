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
    public static final String CATEGORY = "key.categories.skill";
    public static KeyMapping ACTIVATE_POWER;

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        ACTIVATE_POWER = new KeyMapping(
               "key.apikinesismod.activate_selected_skill",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_G, // Default key is G
                CATEGORY
        );

        event.register(ACTIVATE_POWER);
    }
}
