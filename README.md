# Renewables

> Grass -> by growing a mega jungle tree surrounding grass placed on dirt

> Netherack (+Nylium) -> by making a floating nether portal, it will generate with 4 netherack as extension + there is a 1 in 10 chance of it being a nylium (for corresponding biome)

> glow berries -> by growing a mega 2x2 azalea tree

> piglins in the overworld by hitting pigs with a lightning bolt pigs will convert into piglins

> Arctic foxes spawn with snowball instead of feather,foxes spawn with sweet berries instead of wheat in their mouth

> Endstone -> will generate through a cobblestone generator in the end

</br>

# Implemented currently
- [x] Foxes (berries + snowball)
- [x] Grass spread through large jungle trees
- [x] caffeine config (for disabling mixins using a config)
- [x] faster wandering trader spawn (max chance increased from 7.5% to 15%) (spawnDelay decreased from 20 minutes to 5 minutes) (so a trader should spawn in 30 minutes on averages, vanilla is 4 hours)
- [x] 2x2 azalea trees (little stable)
- [x] snow dropping snowballs without requiring tools
- [x] piglins spawning when pigs are hit by lightning

# Todo
- [ ] Implement custom FoliagePlacer for 2x2 azalea tree so on growing you dont find glow berries on ground instantly

# Usage
- Note: All features are disabled by default and features cannot be toggled ingame

Mixins can be enabled by editing `.minecraft/config/randomskyblockmixins.properties` file

Here is a sample config with everything enabled

```java
mixin.renewable_deepslate=true
mixin.skyblock_wandering_trader_trades=true
mixin.snowballs_with_hands=true
mixin.overworld_piglins=true
mixin.renewable_grass=true
mixin.renewable_netherack_and_nylium=true
mixin.foxes_with_berries=true
mixin.faster_wandering_trader_spawn=true
mixin.large_azalea_tree=true
```
