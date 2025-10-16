package com.lemonbunzz.apikinesismod.Items;

import com.lemonbunzz.apikinesismod.ApikinesisMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ApikinesisMod.MODID);
    public static final RegistryObject<Item> BEE_STAFF = ITEMS.register("bee_staff", () -> new Item(new Item.Properties().stacksTo(1)));
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,  ApikinesisMod.MODID);;
    //TODO: this is my first to-do
    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ModItems.BEE_STAFF.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ModItems.BEE_STAFF.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
            }).build());

    //Add to creative tab
    @SubscribeEvent
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(BEE_STAFF);
        }
    }
}
