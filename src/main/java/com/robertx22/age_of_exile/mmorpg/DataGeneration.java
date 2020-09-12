package com.robertx22.age_of_exile.mmorpg;

import com.robertx22.age_of_exile.database.registry.SlashRegistryType;
import com.robertx22.age_of_exile.datapacks.curio_tags.GenerateCurioDataJsons;
import com.robertx22.age_of_exile.datapacks.generators.RecipeGenerator;
import com.robertx22.age_of_exile.datapacks.lang_file.CreateLangFile;
import com.robertx22.age_of_exile.datapacks.loaders.GearRarityLoader;
import com.robertx22.age_of_exile.datapacks.models.ItemModelManager;
import com.robertx22.age_of_exile.datapacks.modpack_helper_lists.ModpackerHelperLists;
import net.minecraft.resource.ReloadableResourceManager;

public class DataGeneration {

    public static void generateAll() {

        if (!MMORPG.RUN_DEV_TOOLS) {
            return;
        }

        new RecipeGenerator().run();

        ModpackerHelperLists.generate();

        CreateLangFile.create();
        GenerateCurioDataJsons.generate();
        ItemModelManager.INSTANCE.generateModels();

    }

    public static void registerLoaders(ReloadableResourceManager manager) {

        SlashRegistryType.getInRegisterOrder()
            .forEach(x -> {
                if (x.getLoader() != null) {
                    manager.registerListener(x.getLoader());
                }
            });

        manager.registerListener(new GearRarityLoader());
    }

}
