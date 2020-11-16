package com.robertx22.age_of_exile.event_hooks.my_events;

import com.robertx22.age_of_exile.a_libraries.curios.MyCurioUtils;
import com.robertx22.age_of_exile.capability.entity.EntityCap;
import com.robertx22.age_of_exile.saveclasses.unit.GearData;
import com.robertx22.age_of_exile.uncommon.effectdatas.AttackInformation;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CollectGearEvent {

    public static List<GearData> getAllGear(AttackInformation event, LivingEntity entity, EntityCap.UnitData unitdata) {
        List<GearData> list = new ArrayList<>();

        Boolean hasWeapon = false;

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            GearData data = getDataFor(slot, entity, unitdata);
            list.add(data);

            if (data.slot == EquipmentSlot.MAINHAND) {
                if (data.gear != null && data.gear.GetBaseGearType()
                    .isWeapon()) {
                    hasWeapon = true;
                }
            }
        }
        if (!hasWeapon) {
            if (event != null && event.weaponData != null) {
                // add weapon damage of throw weapons
                list.add(new GearData(event.weapon, EquipmentSlot.MAINHAND, unitdata));
                hasWeapon = true;
            }
        }
        if (entity instanceof PlayerEntity) {
            MyCurioUtils.getAllSlots((PlayerEntity) entity)
                .forEach(x -> {
                    GearData data = new GearData(x, null, unitdata);
                    list.add(data);
                });
        }
        return list;

    }

    static GearData getDataFor(EquipmentSlot slot, LivingEntity en, EntityCap.UnitData data) {
        ItemStack stack = en.getEquippedStack(slot);
        return new GearData(stack, slot, data);
    }

}
