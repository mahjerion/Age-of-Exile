package com.robertx22.age_of_exile.mmorpg.registers.common.items;

import com.robertx22.age_of_exile.player_skills.items.protection_tablets.BlankTabletItem;
import com.robertx22.age_of_exile.player_skills.items.protection_tablets.BlankTabletTier;
import com.robertx22.age_of_exile.player_skills.items.protection_tablets.ProtectionTabletItem;
import com.robertx22.age_of_exile.player_skills.items.protection_tablets.TabletTypes;

import java.util.ArrayList;
import java.util.List;

public class TabletItemRegister extends BaseItemRegistrator {

    public List<ProtectionTabletItem> ALL_TABLETS = new ArrayList<>();

    public BlankTabletItem BLANK_TABLET = item(new BlankTabletItem(BlankTabletTier.NORMAL), "tablet/blank_tablet0");
    public BlankTabletItem RARE_BLANK_TABLET = item(new BlankTabletItem(BlankTabletTier.SUPREME), "tablet/blank_tablet1");

    public ProtectionTabletItem ANTI_FIRE = tablet(new ProtectionTabletItem(TabletTypes.ANTI_FIRE));
    public ProtectionTabletItem ANTI_HUNGER = tablet(new ProtectionTabletItem(TabletTypes.ANTI_HUNGER));
    public ProtectionTabletItem ANTI_GEAR_BREAK = tablet(new ProtectionTabletItem(TabletTypes.ANTI_GEAR_BREAK));
    public ProtectionTabletItem ANTI_DEATH = tablet(new ProtectionTabletItem(TabletTypes.ANTI_DEATH));

    ProtectionTabletItem tablet(ProtectionTabletItem c) {
        ALL_TABLETS.add(c);
        return item(c);
    }
}