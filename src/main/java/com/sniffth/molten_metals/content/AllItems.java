package com.sniffth.molten_metals.content;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.sniffth.molten_metals.CreateMoltenMetals;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;

public class AllItems {
    private static final CreateRegistrate REGISTRATE = CreateMoltenMetals.registrate();

    public static final ItemEntry<Item> SLAG = REGISTRATE.item("slag", Item::new).register();
    public static final ItemEntry<Item> SLAG_NUGGET = REGISTRATE.item("slag_nugget", Item::new).register();
    public static final ItemEntry<Item> BRONZE = REGISTRATE.item("bronze_ingot", Item::new).tag(
            ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "ingots")),
            ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "ingots/bronze"))).register();
    public static final ItemEntry<Item> TIN = REGISTRATE.item("tin_ingot", Item::new).tag(
            ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "ingots")),
            ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "ingots/tin"))).register();
    public static final ItemEntry<Item> ELECTRUM = REGISTRATE.item("electrum_ingot", Item::new).tag(
            ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "ingots")),
            ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "ingots/electrum"))).register();
    public static final ItemEntry<Item> STEEL = REGISTRATE.item("steel_ingot", Item::new).tag(
            ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "ingots")),
            ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "ingots/steel"))).register();
    public static final ItemEntry<Item> LEAD = REGISTRATE.item("lead_ingot", Item::new).tag(
            ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "ingots")),
            ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "ingots/lead"))).register();
    public static final ItemEntry<Item> SILVER = REGISTRATE.item("silver_ingot", Item::new).tag(
            ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "ingots")),
            ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "ingots/silver"))).register();

    public static void register(IEventBus eventBus) {}
}
