package com.robertx22.age_of_exile.database.registrators;

import com.robertx22.age_of_exile.database.data.spells.spell_classes.bases.BaseSpell;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.divine.HealingAuraSpell;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.divine.HolyFlowerSpell;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.divine.PurifyingFiresSpell;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.divine.SpearOfJudgementSpell;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.divine.buffs.BraverySpell;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.divine.buffs.TrickerySpell;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.divine.buffs.WizardrySpell;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.fire.*;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.hunting.*;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.nature.*;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.ocean.*;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.storm.LightningTotemSpell;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.storm.ThunderDashSpell;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.storm.ThunderspearSpell;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.storm.ThunderstormSpell;
import com.robertx22.age_of_exile.database.registry.ISlashRegistryInit;

import java.util.ArrayList;
import java.util.List;

public class Spells implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<BaseSpell> All = new ArrayList<BaseSpell>() {
            {
                {
                    //DONE
                    add(FrostballSpell.getInstance());
                    add(WhirlpoolSpell.getInstance());
                    add(TidalWaveSpell.getInstance());
                    add(BlizzardSpell.getInstance());
                    add(PoisonBallSpell.getInstance());
                    add(ThunderstormSpell.getInstance());
                    add(FireballSpell.getInstance());
                    add(ThrowFlamesSpell.getInstance());
                    add(ThornArmorSpell.getInstance());
                    add(PoisonedWeaponsSpell.getInstance());
                    add(MagmaFlowerSpell.getInstance());
                    add(HolyFlowerSpell.getInstance());
                    add(ThornBushSpell.getInstance());
                    add(HeartOfIceSpell.getInstance());
                    add(HealingAuraSpell.getInstance());
                    add(BlazingInfernoSpell.getInstance());
                    add(LightningTotemSpell.getInstance());
                    add(MultiShotSpell.getInstance());
                    add(ArrowBarrageSpell.getInstance());

                    /// UNFINISHED
                    add(BraverySpell.getInstance());
                    add(TrickerySpell.getInstance());
                    add(WizardrySpell.getInstance());

                    add(PurifyingFiresSpell.getInstance());

                    add(NatureBalmSpell.getInstance());

                    add(ThunderspearSpell.getInstance());
                    add(ThunderDashSpell.getInstance());

                    add(VolcanoSpell.getInstance());
                    add(FireBombsSpell.getInstance());

                    add(RecoilShotSpell.getInstance());
                    add(ImbueSpell.getInstance());

                    // tough ones
                    add(SpearOfJudgementSpell.getInstance());
                    add(ArrowStormSpell.getInstance());
                    add(GorgonsGazeSpell.getInstance());

                }
            }

        };

        All.forEach(x -> x.registerToSlashRegistry());

    }
}
