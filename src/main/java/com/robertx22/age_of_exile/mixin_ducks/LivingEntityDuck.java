package com.robertx22.age_of_exile.mixin_ducks;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

// use this to do my dmg on dmg!
public interface LivingEntityDuck {

    void myApplyDamage(DamageSource source, float amount);

    int myGetCurrentExperience(PlayerEntity player);

}
