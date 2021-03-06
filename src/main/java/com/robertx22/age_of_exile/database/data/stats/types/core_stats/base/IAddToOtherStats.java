package com.robertx22.age_of_exile.database.data.stats.types.core_stats.base;

import com.robertx22.age_of_exile.capability.entity.EntityCap;
import com.robertx22.age_of_exile.database.data.IGUID;
import com.robertx22.age_of_exile.saveclasses.unit.InCalcStatData;

public interface IAddToOtherStats extends IGUID {

    void addToOtherStats(EntityCap.UnitData unit, InCalcStatData thisstat);

}
