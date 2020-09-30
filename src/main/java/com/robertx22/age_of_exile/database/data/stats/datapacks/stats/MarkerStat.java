package com.robertx22.age_of_exile.database.data.stats.datapacks.stats;

import com.robertx22.age_of_exile.database.data.stats.datapacks.base.DatapackStat;
import com.robertx22.age_of_exile.database.data.stats.name_regex.StatNameRegex;

// does nothing, just used by other things, like the "hasStat" spell condition
public class MarkerStat extends DatapackStat {

    public static String SER_ID = "marker";

    transient String locname;
    transient String desc;

    public MarkerStat(String locname, String desc) {
        super(SER_ID);
        this.locname = locname;
        this.desc = desc;
    }

    public MarkerStat() {
        super(SER_ID);
    }

    @Override
    public StatNameRegex getStatNameRegex() {
        return StatNameRegex.JUST_NAME;
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public String locDescForLangFile() {
        return desc;
    }

    @Override
    public String locNameForLangFile() {
        return locname;
    }

}
