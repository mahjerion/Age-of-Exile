package com.robertx22.age_of_exile.database.data.spells.components.actions;

import com.robertx22.age_of_exile.database.data.spells.components.MapHolder;
import com.robertx22.age_of_exile.database.data.spells.components.ProjectileCastHelper;
import com.robertx22.age_of_exile.database.data.spells.components.Spell;
import com.robertx22.age_of_exile.database.data.spells.contexts.SpellCtx;
import com.robertx22.age_of_exile.database.data.spells.map_fields.MapField;
import com.robertx22.age_of_exile.mmorpg.ModRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public class SummonProjectileAction extends SpellAction {

    public SummonProjectileAction() {
        super(Arrays.asList(MapField.ENTITY_NAME, MapField.PROJECTILE_COUNT, MapField.ITEM, MapField.PROJECTILE_SPEED, MapField.LIFESPAN_TICKS));
    }

    @Override
    public void tryActivate(Collection<LivingEntity> targets, SpellCtx ctx, MapHolder data) {

        Optional<EntityType<?>> projectile = EntityType.get(data.get(MapField.PROJECTILE_ENTITY));

        ProjectileCastHelper builder = new ProjectileCastHelper(data, ctx.caster, projectile.get(), ctx.calculatedSpellData);
        builder.projectilesAmount = data.get(MapField.PROJECTILE_COUNT)
            .intValue();
        builder.shootSpeed = data.get(MapField.PROJECTILE_SPEED)
            .floatValue();

        builder.apart = data.getOrDefault(MapField.PROJECTILES_APART, 75D)
            .floatValue();
        builder.cast();
    }

    public MapHolder create(Item item, Double projCount, Double speed, EntityType type, Double lifespan, boolean gravity) {
        MapHolder c = new MapHolder();
        c.put(MapField.PROJECTILE_COUNT, projCount);
        c.put(MapField.ENTITY_NAME, Spell.Builder.DEFAULT_EN_NAME);
        c.put(MapField.PROJECTILE_SPEED, speed);
        c.put(MapField.LIFESPAN_TICKS, lifespan);
        c.put(MapField.ITEM, Registry.ITEM.getId(item)
            .toString());
        c.put(MapField.GRAVITY, gravity);
        c.put(MapField.PROJECTILE_ENTITY, EntityType.getId(type)
            .toString());
        c.type = GUID();
        return c;
    }

    public MapHolder createArrow(Double projCount, Double speed, Double lifespan, boolean gravity) {
        MapHolder c = createBase(projCount, speed, lifespan, gravity);
        c.put(MapField.PROJECTILE_ENTITY, EntityType.getId(ModRegistry.ENTITIES.SIMPLE_ARROW)
            .toString());
        return c;
    }

    public MapHolder createTrident(Double projCount, Double speed, Double lifespan) {
        MapHolder c = createBase(projCount, speed, lifespan, true);
        c.put(MapField.PROJECTILE_ENTITY, EntityType.getId(ModRegistry.ENTITIES.SIMPLE_TRIDENT)
            .toString());
        return c;
    }

    private MapHolder createBase(Double projCount, Double speed, Double lifespan, boolean gravity) {
        MapHolder c = new MapHolder();
        c.put(MapField.PROJECTILE_COUNT, projCount);
        c.put(MapField.ENTITY_NAME, Spell.Builder.DEFAULT_EN_NAME);
        c.put(MapField.PROJECTILE_SPEED, speed);
        c.put(MapField.LIFESPAN_TICKS, lifespan);
        c.put(MapField.ITEM, Registry.ITEM.getId(Items.AIR)
            .toString());
        c.put(MapField.GRAVITY, gravity);
        c.type = GUID();
        return c;
    }

    @Override
    public String GUID() {
        return "projectile";
    }

}
