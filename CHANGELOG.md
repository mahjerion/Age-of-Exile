### v1.7.8

* fixed ruby ring giving wrong stat
* corrupted golem regens a bit less health below 50% hp

### v1.7.7

* increased mob hp
* vit gives more hp but less regen
* will gives more ms but slightly less regen
* fix restoration to magic shield stat not working on spell effects
* jewelry is a lot more durable now
* you no longer drop weapons you're holding if you're not able to use it (you can use axes again!)
* some general perk and runeword balance

### v.1.7.6
* hotfix stuff like lava dmg breaking whole armor on lvl 50 because of enviro damage scaling by disabling enviro damage scaling by default
(I'll fix it for real in a bit, but this fix is urgent)
* updated curios and cardinal
* mainhand weapon now also dropped if too low level
(i thought this would be annoying so i didn't do it, but i saw many players thinking they are able to use the high lvl weapon, and i can't have that)

### v1.7.5
* emergency bandaid crash fix

### v.1.7.4
+ relic rarity now can't drop on 0 favor either.
+ enabled xp on low favor
+ fix blood mage not showing correct mana
+ blood mages now have 0 mana
+ fix 0 cast time spells spending twice the mana they need
+ fix foods healing WAY more than they should

### v.1.7.3
+ improved auto compat setup time by about 2 or 3 times
+ favor gained on chest loot is now affected by level distance penalties
+ increased default lvl distance penalty
+ added more level penalty configs

### v1.7.2
+ fix total spell/talent points calculation

### v.1.7.1
+ forgot to remove unused library
+ downgraded cardinal version to 2.6.0. as curios doesn't seem to work with 2.7.0 of cardinal.

## v.1.7.0

### Talents rework:
In short, I brought back the big talent tree instead of separating it per spell schools.
Also, spell and talent points are now separate.
Added back nearly all the "game changer" perks like blood mage etc. 

### Core stat rework!
Stats like intelligence, strength and dexterity had a problem. They were used as requirements for gear. 
That means you picked them based on what gear you want to wear, not based on what which ones you wanted.
There was also a lack of variety so.. I added: Wisdom, Vitality and Willpower.

So we have:

- Vitality (Health + regen )
- Willpower (Magic shield + regen)
- Wisdom (Mana + regen)

- Strength (Melee attack)
- Intelligence (Caster attack)
- Dexterity (Ranger attack)

The actual stats they give aren't final but this is the general outline.
Now they give actual stats instead of multiplying your existing values. 
In return, I reduced player's base stats, as now your attributes determine that mostly.
Note, I'll probably have to set up limits or diminishing returns somehow later.

+ Added different damage types, In code I call them Attack Play Style: Melee, Ranged, Magic.
These work based on weapon type and spells. So a ranger spell that shoots arrows is ranged type.
Wands are magic type, swords melee type etc.
Added stats for those Attack play styles too. Primary source: int, dex, str.

### Crackdown on mob farming:

#### Experimental Feature: Azuna's Favor:
- you gain favor by opening loot chests in the world
- you lose favor by looting items from mobs or crafting them
- at 0 favor, many penalties apply:
    1) uniques can't drop
    2) Runes, gems, currencies etc can't drop
    3) Mythical and Legendary items can't drop
    4) 0 Exp gain
    5) All gears that do drop can't be salvaged!
This all applies to crafted items too.

In short, with 0 favor, you will not be given anything besides the base necessities.
You will be allowed to get some low rarity gear to fight with, that's it.

This is hopefully my final implementation of an anti mob farm system.
In essence, there should be zero need to farm mobs, as you should be trying to explore and find chests instead.
If this system works, that means I can finally work on other things. As mob farms have plagued the mod since inception.

I'll be working on a Dungeon mod update that adds more dungeons with loot chests in the meantime.

Other misc changes:
- iron golems and polar bears now dont drop loot
- npcs and animals now dont drop loot either (they had a very small chance)
- angerable mobs (those that don't attack you until you attack them, now have reduced loot and exp.)
All except endermen. Endermen are the only tough angerable mobs in vanilla.
You really had to farm animals that drop 100 times less loot?? I underestimated you guys.
- new update coming to Balance of Exile mod: remove ALL exp and loot from mobs spawned by mob spawners.

### Misc:
+ increased default stat scaling by a ton. Should be closer to mine and slash scaling now.
+ Reverted rarities to mine and slash ones. That means epic, legendary and mythic are back! I also added antique rarity.
Antiques are basically easier to get but worse relics.
+ compatible item datapack customization fields are now optional and have defaults
+ compatible item now uses gear rarity group datapacks instead of min and max rarity rank fields. 
The ranks were confusing, so they're gone. Now each rarity can tell if some other rarity is a higher version of itself or not.
The default if it's not inputted is all rarities besides unique.
+ durability tooltip now says unbreakable if item is unbreakable.
+ added more hp to npcs and less to animals. You don't have to spend so much effort slaughtering cows now.
+ gear name tooltips are now on 2 lines sometimes to reduce tooltip length
+ new affixes: magic find/item quantity affixes on jewelry and more.
+ spawner blocks now drop loot when mined! Apparently the stick doesn't work good enough, so I'm bringing carrots too.
+ fix talent tree being laggy, especially on datapacks that make bigger trees
+ fix hp gui rendering over F3 text
+ added high level area warning
+ environmental damage now scales to level
+ magic shield now reduces environmental damage instead of not reducing and additionally losing itself when target is damaged
In short, it's a needed buff. 
+ sync mana and magic shield better
+ plate and leather armor now has health as base stat, health affixes reduced
+ dodge max %: 80 > 90
+ balanced gear, core stats and base player stats in a way that hopefully doesn't leave gearless players completely hopeless.
Specifically as core stats like vitality now give flat health, it means players have a base defense now. 
Especially magic shield users, as willpower gives flat magic shield.
+ gear level requirements now actually stop you from wearing that gear.
+ fix keep gear config not working
+ high lvl mob's levels are now hidden from you
+ added area level to gui
+ magic essences gone. Now replaced with tiered salvaged dusts (name subject to change)
Based on gear level that was salvaged, you get a dust of that tier.
Those dusts are now used in crafting the mats needed to craft gear.
In other words, you need to salvage lvl 30 gear to be able to craft level 30 gear.
+ removed salvage config, now uses salvage output datapacks
+ tweaked level and xp formula. should be harder now
+ xp gain now shown on actionbar instead of chat message
+ added custom crate item. This is replacement for the old rarity loot crates. 
Now you can choose any rarity you want, even those added with datapacks.
+ added experimental zoom functionality to talent screen
+ added total damage stat
+ added non crit damage stat
+ added converter datapack stat (used in "% of magic shield converted to health" stat)
+ improved talent tree load time by about 50 times
+ armor and dodge now use a diminishing return formula
+ armor and dodge are now based on attacking enemy level. 
In short, 90% dodge chance against a higher level enemy is less than 90% dodge chance.
If it's high enough level, it could be 5% instead.
This not being the case already is what probably made it possible to battle high level mobs before.
+ added elemental dodge stat
+ fix dodge not having sound effect
+ added spell points per lvl config
+ spell and talent points configs are now based on total per max lvl instead of per lvl.

B: known bug, poison can kill you now.
