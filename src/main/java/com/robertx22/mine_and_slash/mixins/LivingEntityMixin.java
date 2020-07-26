package com.robertx22.mine_and_slash.mixins;

import com.robertx22.exiled_lib.events.base.ExileEvents;
import com.robertx22.mine_and_slash.mixin_methods.ArmorRedMethod;
import com.robertx22.mine_and_slash.mixin_methods.OnDamageMethod;
import com.robertx22.mine_and_slash.mixin_methods.OnMobDeathDrops;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/*
  Nothing is done to environmental damage
  By default, entity damage ignores vanilla armor (configurable)
  */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "onKilledBy", at = @At("HEAD"))
    public void on$ondeath(LivingEntity adversary, CallbackInfo ci) {
        LivingEntity victim = (LivingEntity) (Object) this;
        OnMobDeathDrops.mobOnDeathDrop(victim);
    }

    @ModifyVariable(method = "damage", at = @At("HEAD"), argsOnly = true)
    public float onmy$damage(float amount, DamageSource source) {
        if ((Object) this instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) (Object) this;
            return OnDamageMethod.on$damage(amount, source, entity);
        }
        return amount;
    }

    @Inject(method = "tick()V", at = @At("HEAD"))
    public void on$tick(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        ExileEvents.LIVING_ENTITY_TICK.callEvents(x -> x.onTick(entity));
    }

    @Inject(
        method = "applyArmorToDamage(Lnet/minecraft/entity/damage/DamageSource;F)F",
        at = @At("HEAD"),
        cancellable = true
    )
    public void onArmorReduction(DamageSource source, float damage, CallbackInfoReturnable<Float> ci) {
        LivingEntity en = (LivingEntity) (Object) this;
        ArmorRedMethod.onArmorReduction(source, damage, ci, en);
    }
}
