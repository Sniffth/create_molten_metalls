package com.sniffth.molten_metals.content;

import com.sniffth.molten_metals.CreateMoltenMetals;
import com.tterrag.registrate.util.entry.FluidEntry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Locale;

import static com.sniffth.molten_metals.CreateMoltenMetals.REGISTRATE;

public class AllMoltenMetals {
    public enum MetalType {
        IRON, GOLD, COPPER, ZINC, NETHERITE, BRASS;

        private final String name;
        private final FluidEntry<BaseFlowingFluid.Flowing> fluidEntry;

        MetalType() {
            this.name = this.name().toLowerCase(Locale.ROOT);
            this.fluidEntry = REGISTRATE.fluid("molten_" + name,
                            CreateMoltenMetals.rl("block/fluid/molten_" + name),
                            CreateMoltenMetals.rl("block/fluid/molten_" + name + "_flowing"))
                    .properties(b -> b.viscosity(1500).density(500))
                    .fluidProperties(p -> p.levelDecreasePerBlock(2).tickRate(25).slopeFindDistance(3).explosionResistance(100f))
                    .source(BaseFlowingFluid.Source::new)
                    .block()
                    .build()
                    .bucket()
                    .onRegister(AllMoltenMetals::registerFluidDispenseBehavior)
                    .build()
                    .register();
        }

        public FluidEntry<BaseFlowingFluid.Flowing> getFluid() {
            return fluidEntry;
        }

        public String getConventionTag() {
            return "c:" + this.name + "_ingots";
        }
    }

    public enum MetalTypeCompat {
        BISMUTH, BRONZE, ELECTRUM, LEAD, NECROMIUM, SILVER, STEEL, TIN;

        private final String name;
        private final FluidEntry<BaseFlowingFluid.Flowing> fluidEntry;

        MetalTypeCompat() {
            this.name = this.name().toLowerCase(Locale.ROOT);
            this.fluidEntry = REGISTRATE.fluid("molten_" + name,
                            CreateMoltenMetals.rl("block/fluid/molten_" + name),
                            CreateMoltenMetals.rl("block/fluid/molten_" + name + "_flowing"))
                    .properties(b -> b.viscosity(1500).density(500))
                    .fluidProperties(p -> p.levelDecreasePerBlock(2).tickRate(25).slopeFindDistance(3).explosionResistance(100f))
                    .source(BaseFlowingFluid.Source::new)
                    .block()
                    .build()
                    .bucket()
                    .onRegister(AllMoltenMetals::registerFluidDispenseBehavior)
                    .build()
                    .register();
        }

        public FluidEntry<BaseFlowingFluid.Flowing> getFluid() {
            return fluidEntry;
        }

        public String getConventionTag() {
            return "c:" + this.name + "_ingots";
        }
    }

    public static void registerFluidInteractions() {
        MetalType[] metals = MetalType.values();
        for (var metal1 : metals) {
            for (var metal2 : metals) {
                if (metal1 != metal2) {
                    FluidInteractionRegistry.addInteraction(
                            metal1.getFluid().get().getFluidType(),
                            new FluidInteractionRegistry.InteractionInformation(
                                    metal2.getFluid().get().getFluidType(),
                                    fluidState -> AllBlocks.SLAG_BLOCK.getDefaultState()
                            )
                    );
                }
            }
        }
        MetalTypeCompat[] metals_compat = MetalTypeCompat.values();
        for (var metal1 : metals_compat) {
            for (var metal2 : metals_compat) {
                if (metal1 != metal2) {
                    FluidInteractionRegistry.addInteraction(
                            metal1.getFluid().get().getFluidType(),
                            new FluidInteractionRegistry.InteractionInformation(
                                    metal2.getFluid().get().getFluidType(),
                                    fluidState -> AllBlocks.SLAG_BLOCK.getDefaultState()
                            )
                    );
                }
            }
        }
        for (var metal1 : metals) {
            for (var metal2 : metals_compat) {
                FluidInteractionRegistry.addInteraction(
                        metal1.getFluid().get().getFluidType(),
                        new FluidInteractionRegistry.InteractionInformation(
                                metal2.getFluid().get().getFluidType(),
                                fluidState -> AllBlocks.SLAG_BLOCK.getDefaultState()
                        )
                );
            }
        }
    }

    private static final DispenseItemBehavior DEFAULT = new DefaultDispenseItemBehavior();
    private static final DispenseItemBehavior DISPENSE_FLUID = new DefaultDispenseItemBehavior(){
        @Override
        protected @NonNull ItemStack execute(BlockSource pSource, ItemStack pStack) {
            DispensibleContainerItem dispensibleContainerItem = (DispensibleContainerItem) pStack.getItem();
            BlockPos pos = pSource.pos().relative(pSource.state().getValue(DispenserBlock.FACING));
            Level level = pSource.level();
            if (dispensibleContainerItem.emptyContents(null, level, pos, null, pStack)) {
                return new ItemStack(Items.BUCKET);
            }
            return DEFAULT.dispense(pSource, pStack);
        }
    };

    private static void registerFluidDispenseBehavior(BucketItem bucket) {
        DispenserBlock.registerBehavior(bucket, DISPENSE_FLUID);
    }

    public static void register(IEventBus eventBus) {
        int size = MetalType.values().length;
    }
}
