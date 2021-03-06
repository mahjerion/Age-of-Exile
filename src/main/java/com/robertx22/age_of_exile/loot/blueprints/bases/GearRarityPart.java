package com.robertx22.age_of_exile.loot.blueprints.bases;

import com.robertx22.age_of_exile.database.data.groups.GearRarityGroups;
import com.robertx22.age_of_exile.database.data.rarities.GearRarity;
import com.robertx22.age_of_exile.database.data.stats.types.loot.TreasureQuality;
import com.robertx22.age_of_exile.database.registry.RarityRegistryContainer;
import com.robertx22.age_of_exile.database.registry.SlashRegistry;
import com.robertx22.age_of_exile.loot.LootInfo;
import com.robertx22.age_of_exile.loot.blueprints.GearBlueprint;
import com.robertx22.age_of_exile.uncommon.utilityclasses.RandomUtils;

import java.util.List;

public class GearRarityPart extends BlueprintPart<GearRarity, GearBlueprint> {

    RarityRegistryContainer<GearRarity> container;

    public List<GearRarity> possible = SlashRegistry.GearRarityGroups()
        .get(GearRarityGroups.NON_UNIQUE_ID)
        .getRarities();

    public float chanceForHigherRarity = 0;

    public void setupChances(LootInfo info) {
        this.chanceForHigherRarity = SlashRegistry.Tiers()
            .get(this.blueprint.tier.get() + "").chance_for_higher_drop_rarity;

        if (info.playerData != null) {
            if (info.lootOrigin == LootInfo.LootOrigin.CHEST) {
                chanceForHigherRarity += info.playerData.getUnit()
                    .getCalculatedStat(TreasureQuality.getInstance())
                    .getAverageValue();
            }
        }

        if (info.favorRank != null) {
            possible.removeIf(x -> info.favorRank.excludedRarities.stream()
                .anyMatch(e -> e.equals(x.GUID())));
        }

    }

    public GearRarityPart(GearBlueprint blueprint) {
        super(blueprint);
        this.container = blueprint.getRarityContainer();

    }

    @Override
    protected GearRarity generateIfNull() {

        GearRarity rar = RandomUtils.weightedRandom(possible);

        if (rar.Rank() < container.maxNonUnique()
            .Rank() && RandomUtils.roll(chanceForHigherRarity)) {
            if (rar.hasHigherRarity() && possible.contains(rar.getHigherRarity())) {
                rar = rar.getHigherRarity();
            }
        }

        return rar;
    }

    public void setSpecificRarity(int rank) {
        this.set(container.get(rank));
    }

}


