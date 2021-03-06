package com.robertx22.age_of_exile.aoe_data.database.base_gear_types.adders;

import com.robertx22.age_of_exile.aoe_data.base.DataGenKey;
import com.robertx22.age_of_exile.aoe_data.database.base_gear_types.BaseGearBuilder;
import com.robertx22.age_of_exile.aoe_data.database.gear_slots.GearSlots;
import com.robertx22.age_of_exile.database.data.StatModifier;
import com.robertx22.age_of_exile.database.data.gear_types.bases.BaseGearType;
import com.robertx22.age_of_exile.database.data.gear_types.bases.BaseGearType.SlotTag;
import com.robertx22.age_of_exile.database.data.gear_types.bases.TagList;
import com.robertx22.age_of_exile.database.data.level_ranges.LevelRange;
import com.robertx22.age_of_exile.database.data.stats.types.resources.magic_shield.MagicShield;
import com.robertx22.age_of_exile.database.registry.ISlashRegistryInit;
import com.robertx22.age_of_exile.mmorpg.ModRegistry;
import com.robertx22.age_of_exile.saveclasses.gearitem.gear_bases.StatRequirement;
import com.robertx22.age_of_exile.uncommon.enumclasses.ModType;
import net.minecraft.entity.EquipmentSlot;

import java.util.HashMap;

import static com.robertx22.age_of_exile.uncommon.utilityclasses.SlotUtils.multiOf;
import static net.minecraft.entity.EquipmentSlot.FEET;

public class BaseClothArmors implements ISlashRegistryInit {

    public static HashMap<LevelRange, DataGenKey<BaseGearType>> BOOTS;
    public static HashMap<LevelRange, DataGenKey<BaseGearType>> PANTS;
    public static HashMap<LevelRange, DataGenKey<BaseGearType>> CHESTS;
    public static HashMap<LevelRange, DataGenKey<BaseGearType>> HELMETS;

    static float min = 4;
    static float max = 8;

    @Override
    public void registerAll() {

        BOOTS = BaseGearBuilder.of(GearSlots.BOOTS, "cloth_boots", "Shoes", ModRegistry.GEAR_ITEMS.CLOTH_BOOTS)
            .req(new StatRequirement().intelligence(0.5f))
            .tags(new TagList(SlotTag.cloth, SlotTag.boots, SlotTag.armor_family, SlotTag.magic_shield_stat, SlotTag.intelligence))
            .baseStat(new StatModifier(multiOf(FEET) * min, multiOf(FEET) * max, MagicShield.getInstance(), ModType.FLAT))
            .addMageLevelRanges()
            .build();

        PANTS = BaseGearBuilder.of(GearSlots.PANTS, "cloth_pants", "Leggings", ModRegistry.GEAR_ITEMS.CLOTH_PANTS)
            .req(new StatRequirement().intelligence(0.5f))
            .tags(new TagList(SlotTag.cloth, SlotTag.pants, SlotTag.armor_family, SlotTag.magic_shield_stat, SlotTag.intelligence))
            .baseStat(new StatModifier(multiOf(EquipmentSlot.LEGS) * min, multiOf(EquipmentSlot.LEGS) * max, MagicShield.getInstance(), ModType.FLAT))
            .addMageLevelRanges()
            .build();

        CHESTS = BaseGearBuilder.of(GearSlots.CHEST, "cloth_chest", "Robe", ModRegistry.GEAR_ITEMS.CLOTH_CHESTS)
            .req(new StatRequirement().intelligence(0.5f))
            .tags(new TagList(SlotTag.cloth, SlotTag.chest, SlotTag.armor_family, SlotTag.magic_shield_stat, SlotTag.intelligence))
            .baseStat(new StatModifier(multiOf(EquipmentSlot.CHEST) * min, multiOf(EquipmentSlot.CHEST) * max, MagicShield.getInstance(), ModType.FLAT))
            .addMageLevelRanges()
            .build();

        HELMETS = BaseGearBuilder.of(GearSlots.HELMET, "cloth_helmet", "Hat", ModRegistry.GEAR_ITEMS.CLOTH_HELMETS)
            .req(new StatRequirement().intelligence(0.5f))
            .tags(new TagList(SlotTag.cloth, SlotTag.helmet, SlotTag.armor_family, SlotTag.magic_shield_stat, SlotTag.intelligence))
            .baseStat(new StatModifier(multiOf(EquipmentSlot.HEAD) * min, multiOf(EquipmentSlot.HEAD) * max, MagicShield.getInstance(), ModType.FLAT))
            .addMageLevelRanges()
            .build();
    }
}
