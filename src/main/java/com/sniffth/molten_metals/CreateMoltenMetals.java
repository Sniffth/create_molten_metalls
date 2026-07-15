package com.sniffth.molten_metals;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipModifier;
import com.sniffth.molten_metals.content.AllBlocks;
import com.sniffth.molten_metals.content.AllItems;
import com.sniffth.molten_metals.content.AllMoltenMetals;
import com.sniffth.molten_metals.content.CreativeTab;
import net.createmod.catnip.lang.FontHelper;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(CreateMoltenMetals.MODID)
public class CreateMoltenMetals {
    public static final String MODID = "molten_metals";
    public static final CreateRegistrate REGISTRATE =
            CreateRegistrate.create(CreateMoltenMetals.MODID)
                    .defaultCreativeTab((ResourceKey<CreativeModeTab>) null)
                    .setTooltipModifierFactory(item -> new ItemDescription.Modifier(item, FontHelper.Palette.STANDARD_CREATE)
                            .andThen(TooltipModifier.mapNull(KineticStats.create(item)))
                    );
    public static final Logger LOGGER = LogUtils.getLogger();

    public CreateMoltenMetals(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        REGISTRATE.registerEventListeners(modEventBus);

        NeoForge.EVENT_BUS.register(this);
        AllMoltenMetals.register(modEventBus);
        AllBlocks.register(modEventBus);
        AllItems.register(modEventBus);
        CreativeTab.register(modEventBus);

        modEventBus.addListener(this::addCreative);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    public static ResourceLocation rl(String path){
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }


    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        AllMoltenMetals.registerFluidInteractions();
    }

    public static CreateRegistrate registrate() {
        return REGISTRATE;
    }
}