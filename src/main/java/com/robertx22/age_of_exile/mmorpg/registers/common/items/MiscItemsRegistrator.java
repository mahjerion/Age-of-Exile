package com.robertx22.age_of_exile.mmorpg.registers.common.items;

import com.robertx22.age_of_exile.database.base.CreativeTabs;
import com.robertx22.age_of_exile.database.registrators.LevelRanges;
import com.robertx22.age_of_exile.mmorpg.ModRegistry;
import com.robertx22.age_of_exile.vanilla_mc.items.SimpleMatItem;
import com.robertx22.age_of_exile.vanilla_mc.items.gemrunes.RuneWordItem;
import com.robertx22.age_of_exile.vanilla_mc.items.misc.*;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import java.util.Arrays;
import java.util.List;

public class MiscItemsRegistrator extends BaseItemRegistrator {

    public IdentifyTomeItem IDENTIFY_TOME = item(new IdentifyTomeItem(), "identify_tome");

    public List<SalvagedDustItem> getDusts() {
        return Arrays.asList(T0_DUST, T1_DUST, T2_DUST, T3_DUST, T4_DUST);
    }

    public SalvagedDustItem T0_DUST = item(new SalvagedDustItem("T0 Salvaged Dust", 0, LevelRanges.STARTER));
    public SalvagedDustItem T1_DUST = item(new SalvagedDustItem("T1 Salvaged Dust", 1, LevelRanges.LOW));
    public SalvagedDustItem T2_DUST = item(new SalvagedDustItem("T2 Salvaged Dust", 2, LevelRanges.MIDDLE));
    public SalvagedDustItem T3_DUST = item(new SalvagedDustItem("T3 Salvaged Dust", 3, LevelRanges.HIGH));
    public SalvagedDustItem T4_DUST = item(new SalvagedDustItem("T4 Salvaged Dust", 4, LevelRanges.ENDGAME));

    CustomLootCrateItem CUSTOM_CRATE = item(new CustomLootCrateItem(), "custom_crate");

    LootCrateItem COMMON_CRATE = item(new LootCrateItem(LootCrateItem.LootCrateType.COMMON), "loot_crate/common");
    LootCrateItem MAGIC_CRATE = item(new LootCrateItem(LootCrateItem.LootCrateType.MAGIC), "loot_crate/magic");
    LootCrateItem RARE_CRATE = item(new LootCrateItem(LootCrateItem.LootCrateType.RARE), "loot_crate/rare");
    LootCrateItem RELIC_CRATE = item(new LootCrateItem(LootCrateItem.LootCrateType.RELIC), "loot_crate/relic");
    LootCrateItem UNIQUE_CRATE = item(new LootCrateItem(LootCrateItem.LootCrateType.UNIQUE), "loot_crate/unique");
    LootCrateItem RUNE_CRATE = item(new LootCrateItem(LootCrateItem.LootCrateType.RUNE), "loot_crate/rune");
    LootCrateItem GEM_CRATE = item(new LootCrateItem(LootCrateItem.LootCrateType.GEM), "loot_crate/gem");

    public VanillaExperienceChestItem VANILLA_EXP_CHEST = item(new VanillaExperienceChestItem(), "loot_crate/ench_exp_bag");

    public LootTableItem LOOT_TABLE_ITEM = item(new LootTableItem(), "loot_table_chest");

    public Item NEWBIE_GEAR_BAG = item(new ItemNewbieGearBag(), "newbie_gear_bag");
    public Item INCRESE_MOB_RARITY = item(new ItemIncreaseRarityNearestEntity(), "increase_rarity_nearest_entity");
    public Item RUNEWORD = item(new RuneWordItem(), "runeword");

    public Item INFUSED_IRON = item(new SimpleMatItem(), "mat/infused_iron");
    public Item CRYSTALLIZED_ESSENCE = item(new SimpleMatItem(), "mat/crystallized_essence");
    public Item GOLDEN_ORB = item(new SimpleMatItem(), "mat/golden_orb");
    public Item MYTHIC_ESSENCE = item(new SimpleMatItem(), "mat/mythic_essence");

    public Item HEART_OF_IMAGINATION = item(new HeartOfImaginationItem(), "mat/modify_part");

    public ResetStatPointsItem RESET_STATS_POTION = item(new ResetStatPointsItem());
    public ResetAllPerksItem RESET_ALL_PERKS = item(new ResetAllPerksItem());
    public ResetAllSpellsItem RESET_ALL_SPELLS = item(new ResetAllSpellsItem());
    public AddResetPerkPointsItem ADD_RESET_PERK_POINTS = item(new AddResetPerkPointsItem());

    public Item GEAR_MODIFY = blockItem(ModRegistry.BLOCKS.GEAR_MODIFY);
    public Item GEAR_REPAIR = blockItem(ModRegistry.BLOCKS.GEAR_REPAIR);
    public Item GEAR_SALVAGE = blockItem(ModRegistry.BLOCKS.GEAR_SALVAGE);
    public Item GEAR_SOCKET = blockItem(ModRegistry.BLOCKS.SOCKET_STATION);

    static Item.Settings stationProp = new Item.Settings().group(CreativeTabs.MyModTab);

    <T extends Block> Item blockItem(T block) {
        return item(new BlockItem(block, stationProp), Registry.BLOCK.getId(block)
            .getPath());
    }

}
