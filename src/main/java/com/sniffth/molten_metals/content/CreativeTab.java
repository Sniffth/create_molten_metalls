package com.sniffth.molten_metals.content;

import com.simibubi.create.Create;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.sniffth.molten_metals.CreateMoltenMetals;
import com.sniffth.molten_metals.custom.MoldBlock;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CreativeTab {
    private static final CreateRegistrate REGISTRATE = CreateMoltenMetals.registrate();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateMoltenMetals.MODID);

    public static final Supplier<CreativeModeTab> MAIN_TAB = CREATIVE_MODE_TAB.register("main_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(AllMoltenMetals.MetalType.IRON.getFluid().getBucket().get().asItem()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(Create.ID, "palettes"))
                    .title(Component.translatable("creative_tab.molten_metals.tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                            for (var fluid : AllMoltenMetals.MetalType.values()) {
                                output.accept(fluid.getFluid().getBucket().get());
                            }
                            output.accept(AllBlocks.SLAG_BLOCK.get());
                            output.accept(AllItems.SLAG_NUGGET.get());
                            output.accept(AllItems.SLAG.get());
                            output.accept(AllBlocks.UNFIRED_CERAMIC_INGOT_MOLD.get());
                            output.accept(AllBlocks.CERAMIC_INGOT_MOLD.get());
                            output.accept(AllBlocks.INGOT_MOLD.get());
                            for (var mold : AllBlocks.METAL_MOLDS.values()) {output.accept(mold.get());}
                            for (var mold : AllBlocks.METAL_MOLDS_CERAMIC.values()) {output.accept(mold.get());
                            }}).build());

    public static final Supplier<CreativeModeTab> COMPAT_TAB = CREATIVE_MODE_TAB.register("compat_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(AllMoltenMetals.MetalTypeCompat.BISMUTH.getFluid().getBucket().get().asItem()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(Create.ID, "palettes"))
                    .title(Component.translatable("creative_tab.molten_metals.tab_compat"))
                    .displayItems((itemDisplayParameters, output) -> {
                        for (var fluid : AllMoltenMetals.MetalTypeCompat.values()) {
                            output.accept(fluid.getFluid().getBucket().get());
                        }
                        output.accept(AllItems.SILVER.get());
                        output.accept(AllItems.TIN.get());
                        output.accept(AllItems.BRONZE.get());
                        output.accept(AllItems.STEEL.get());
                        output.accept(AllItems.LEAD.get());
                        output.accept(AllItems.ELECTRUM.get());
                        for (var mold : AllBlocks.METAL_MOLDS_COMPAT.values()) {output.accept(mold.get());}
                        for (var mold : AllBlocks.METAL_MOLDS_CERAMIC_COMPAT.values()) {output.accept(mold.get());
                        }}).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
