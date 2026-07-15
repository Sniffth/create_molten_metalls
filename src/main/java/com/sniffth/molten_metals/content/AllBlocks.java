package com.sniffth.molten_metals.content;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.sniffth.molten_metals.CreateMoltenMetals;
import com.sniffth.molten_metals.custom.MoldBlock;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.bus.api.IEventBus;

import java.util.EnumMap;
import java.util.Map;

import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;

public class AllBlocks {
    private static final CreateRegistrate REGISTRATE = CreateMoltenMetals.registrate();

    public static final BlockEntry<MoldBlock> INGOT_MOLD = REGISTRATE.block("ingot_mold", MoldBlock::new)
            .initialProperties(() -> Blocks.STONE_SLAB)
            .properties(p -> p.mapColor(MapColor.TERRACOTTA_BLACK))
            .loot((lt, block) -> lt.add(block, LootTable.lootTable().withPool(LootPool.lootPool()
                    .when(ExplosionCondition.survivesExplosion())
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(block.asItem())))))
            .transform(pickaxeOnly())
            .simpleItem().register();

    public static final BlockEntry<MoldBlock> CERAMIC_INGOT_MOLD = REGISTRATE.block("ceramic_ingot_mold", MoldBlock::new)
            .initialProperties(() -> Blocks.STONE_SLAB)
            .properties(p -> p.mapColor(MapColor.TERRACOTTA_BLACK))
            .loot((lt, block) -> lt.add(block, LootTable.lootTable().withPool(LootPool.lootPool()
                    .when(ExplosionCondition.survivesExplosion())
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(block.asItem())))))
            .transform(pickaxeOnly())
            .simpleItem().register();

    public static final BlockEntry<MoldBlock> UNFIRED_CERAMIC_INGOT_MOLD = REGISTRATE.block("unfired_ceramic_ingot_mold", MoldBlock::new)
            .initialProperties(() -> Blocks.CLAY)
            .properties(p -> p.mapColor(MapColor.TERRACOTTA_BLACK))
            .loot((lt, block) -> lt.add(block, LootTable.lootTable().withPool(LootPool.lootPool()
                    .when(ExplosionCondition.survivesExplosion())
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(block.asItem())))))
            .simpleItem().register();

    public static final BlockEntry<Block> SLAG_BLOCK = REGISTRATE.block("slag_block", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p.mapColor(DyeColor.BLACK))
            .loot((lt, block) -> lt.add(block, LootTable.lootTable().withPool(LootPool.lootPool()
                    .when(ExplosionCondition.survivesExplosion())
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(block.asItem())))))
            .simpleItem().register();

    public static final Map<AllMoltenMetals.MetalType, BlockEntry<MoldBlock>> METAL_MOLDS = new EnumMap<>(AllMoltenMetals.MetalType.class);
    public static final Map<AllMoltenMetals.MetalType, BlockEntry<MoldBlock>> METAL_MOLDS_CERAMIC = new EnumMap<>(AllMoltenMetals.MetalType.class);
    public static final Map<AllMoltenMetals.MetalTypeCompat, BlockEntry<MoldBlock>> METAL_MOLDS_COMPAT = new EnumMap<>(AllMoltenMetals.MetalTypeCompat.class);
    public static final Map<AllMoltenMetals.MetalTypeCompat, BlockEntry<MoldBlock>> METAL_MOLDS_CERAMIC_COMPAT = new EnumMap<>(AllMoltenMetals.MetalTypeCompat.class);

    public static void register(IEventBus eventBus) {
        for (AllMoltenMetals.MetalType metal : AllMoltenMetals.MetalType.values()) {
            String name = metal.name().toLowerCase(java.util.Locale.ROOT);
            String registryName = "molten_" + name + "_ingot_mold";
            String registryNameCeramic = "molten_" + name + "_ceramic_ingot_mold";

            BlockEntry<MoldBlock> entry = REGISTRATE.block(registryName, MoldBlock::new)
                    .initialProperties(() -> Blocks.STONE_SLAB)
                    .properties(p -> p.mapColor(MapColor.TERRACOTTA_BLACK))
                    .loot((lt, block) -> lt.add(block, LootTable.lootTable().withPool(LootPool.lootPool()
                            .when(ExplosionCondition.survivesExplosion())
                            .setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(block.asItem())))))
                    .transform(pickaxeOnly())
                    .simpleItem().register();

            BlockEntry<MoldBlock> entryCeramic = REGISTRATE.block(registryNameCeramic, MoldBlock::new)
                    .initialProperties(() -> Blocks.STONE_SLAB)
                    .properties(p -> p.mapColor(MapColor.TERRACOTTA_BLACK))
                    .loot((lt, block) -> lt.add(block, LootTable.lootTable().withPool(LootPool.lootPool()
                            .when(ExplosionCondition.survivesExplosion())
                            .setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(block.asItem())))))
                    .transform(pickaxeOnly())
                    .simpleItem().register();

            METAL_MOLDS.put(metal, entry);
            METAL_MOLDS_CERAMIC.put(metal, entryCeramic);
        }

        for (AllMoltenMetals.MetalTypeCompat metal : AllMoltenMetals.MetalTypeCompat.values()) {
            String name = metal.name().toLowerCase(java.util.Locale.ROOT);
            String registryName = "molten_" + name + "_ingot_mold";
            String registryNameCeramic = "molten_" + name + "_ceramic_ingot_mold";

            BlockEntry<MoldBlock> entry = REGISTRATE.block(registryName, MoldBlock::new)
                    .initialProperties(() -> Blocks.STONE_SLAB)
                    .properties(p -> p.mapColor(MapColor.TERRACOTTA_BLACK))
                    .loot((lt, block) -> lt.add(block, LootTable.lootTable().withPool(LootPool.lootPool()
                            .when(ExplosionCondition.survivesExplosion())
                            .setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(block.asItem())))))
                    .transform(pickaxeOnly())
                    .simpleItem().register();

            BlockEntry<MoldBlock> entryCeramic = REGISTRATE.block(registryNameCeramic, MoldBlock::new)
                    .initialProperties(() -> Blocks.STONE_SLAB)
                    .properties(p -> p.mapColor(MapColor.TERRACOTTA_BLACK))
                    .loot((lt, block) -> lt.add(block, LootTable.lootTable().withPool(LootPool.lootPool()
                            .when(ExplosionCondition.survivesExplosion())
                            .setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(block.asItem())))))
                    .transform(pickaxeOnly())
                    .simpleItem().register();

            METAL_MOLDS_COMPAT.put(metal, entry);
            METAL_MOLDS_CERAMIC_COMPAT.put(metal, entryCeramic);
        }
    }

    public static BlockEntry<MoldBlock> getMetalMold(AllMoltenMetals.MetalType type) {
        return METAL_MOLDS.get(type);
    }

    public static BlockEntry<MoldBlock> getCeramicMetalMold(AllMoltenMetals.MetalType type) {
        return METAL_MOLDS_CERAMIC.get(type);
    }
}
