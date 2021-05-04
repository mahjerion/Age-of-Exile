package com.robertx22.age_of_exile.uncommon.enumclasses;

import com.robertx22.age_of_exile.database.data.perks.StatAttribute;

public enum AttackPlayStyle {

    melee(StatAttribute.STR) {
        @Override
        public AttackType getAttackType() {
            return AttackType.attack;
        }
    },
    ranged(StatAttribute.DEX) {
        @Override
        public AttackType getAttackType() {
            return AttackType.attack;
        }
    },
    magic(StatAttribute.INT) {
        @Override
        public AttackType getAttackType() {
            return AttackType.spell;
        }
    };

    public StatAttribute attribute;

    AttackPlayStyle(StatAttribute attribute) {
        this.attribute = attribute;
    }

    public abstract AttackType getAttackType();
}