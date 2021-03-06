package com.robertx22.age_of_exile.loot.blueprints.bases;

import com.robertx22.age_of_exile.config.forge.ModConfig;
import com.robertx22.age_of_exile.loot.blueprints.GearBlueprint;
import com.robertx22.age_of_exile.loot.blueprints.ItemBlueprint;
import com.robertx22.age_of_exile.uncommon.utilityclasses.RandomUtils;
import net.minecraft.util.math.MathHelper;

public class LevelPart extends BlueprintPart<Integer, ItemBlueprint> {

    public boolean LevelRange = true;

    public int LevelVariance = 3;

    public int minLevel = 1;

    public int maxLevel = Integer.MAX_VALUE;

    public int number;

    public LevelPart(ItemBlueprint blueprint) {
        super(blueprint);
    }

    @Override
    protected Integer generateIfNull() {

        if (blueprint instanceof GearBlueprint) {
            GearBlueprint gearb = (GearBlueprint) blueprint;
            return RandomUtils.RandomRange(gearb.gearItemSlot.get()
                .getLevelRange()
                .getMinLevel(), gearb.gearItemSlot.get()
                .getLevelRange()
                .getMaxLevel());
        }

        int finalLvl = number;

        if (LevelRange) {
            finalLvl = RandomUtils.RandomRange(number - LevelVariance, number + LevelVariance);
        }

        finalLvl = MathHelper.clamp(finalLvl, minLevel, maxLevel);

        return MathHelper.clamp(finalLvl, this.minLevel, ModConfig.get().Server.MAX_LEVEL);
    }
}