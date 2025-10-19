package com.lemonbunzz.apikinesismod.item;

import com.lemonbunzz.apikinesismod.ApikinesisMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModToolsCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB,  ApikinesisMod.MODID);

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }
    //TODO: this is my first to-do
    public static final RegistryObject<CreativeModeTab> EQUIPMENT_TAB = TABS.register("example_tab",
            () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> new ItemStack(ModItems.BEE_STAFF.get()))
            .displayItems((parameters, output) -> {

                output.accept(ModItems.BEE_STAFF.get());
                //add stuff here
            }).build());
}
