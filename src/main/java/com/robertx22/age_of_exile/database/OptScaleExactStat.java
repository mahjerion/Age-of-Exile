package com.robertx22.age_of_exile.database;

import com.robertx22.age_of_exile.capability.entity.EntityCap;
import com.robertx22.age_of_exile.database.data.StatModifier;
import com.robertx22.age_of_exile.database.data.stats.Stat;
import com.robertx22.age_of_exile.database.registry.SlashRegistry;
import com.robertx22.age_of_exile.saveclasses.gearitem.gear_bases.IApplyableStats;
import com.robertx22.age_of_exile.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.age_of_exile.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.age_of_exile.saveclasses.item_classes.tooltips.TooltipStatInfo;
import com.robertx22.age_of_exile.saveclasses.item_classes.tooltips.TooltipStatWithContext;
import com.robertx22.age_of_exile.uncommon.enumclasses.ModType;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class OptScaleExactStat implements IApplyableStats, ITooltipList, IByteBuf<OptScaleExactStat> {
    public static OptScaleExactStat SERIALIZER = new OptScaleExactStat();

    public float first = 0;
    public float second = 0;
    public String stat;
    public String type;

    public transient Stat transientstat;

    public boolean scaleToLevel = false;

    private OptScaleExactStat() {
    }

    @Override
    public OptScaleExactStat getFromBuf(PacketByteBuf buf) {
        OptScaleExactStat data = new OptScaleExactStat();
        data.first = buf.readFloat();
        data.second = buf.readFloat();
        data.stat = buf.readString(100);
        data.type = buf.readString(50);
        return data;
    }

    @Override
    public void toBuf(PacketByteBuf buf) {
        buf.writeFloat(first);
        buf.writeFloat(second);
        buf.writeString(stat, 100);
        buf.writeString(type, 50);
    }

    public OptScaleExactStat(float first, Stat stat, ModType type) {
        this.first = first;
        this.stat = stat.GUID();
        this.type = type.name();
        this.transientstat = stat;
    }

    public OptScaleExactStat(float first, float second, Stat stat, ModType type) {
        this.first = first;
        this.second = second;
        this.stat = stat.GUID();
        this.type = type.name();
        this.transientstat = stat;
    }

    @Override
    public List<Text> GetTooltipString(TooltipInfo info) {
        Stat stat = getStat();
        TooltipStatInfo statInfo = new TooltipStatInfo(this, info);
        return new ArrayList<>(stat.getTooltipList(new TooltipStatWithContext(statInfo, null, null)));
    }

    public OptScaleExactStat scale() {
        this.scaleToLevel = true;
        return this;
    }

    public Stat getStat() {
        return SlashRegistry.Stats()
            .get(stat);
    }

    public ModType getModType() {
        return ModType.fromString(type);
    }

    public StatModifier toStatModifier() {
        Stat stat = SlashRegistry.Stats()
            .get(this.stat);
        if (stat.UsesSecondValue()) {
            return new StatModifier(first, first, second, second, stat, getModType());
        } else {
            return new StatModifier(first, first, stat, getModType());
        }

    }

    public void applyStats(EntityCap.UnitData data, int lvl) {
        toStatModifier().ToExactStat(100, scaleToLevel ? lvl : 1)
            .applyStats(data);
    }

    @Override
    public void applyStats(EntityCap.UnitData data) {
        if (scaleToLevel) {
            toStatModifier().ToExactStat(100, data.getLevel())
                .applyStats(data);
        } else {
            toStatModifier().ToExactStat(100, 1)
                .applyStats(data);
        }
    }

}
