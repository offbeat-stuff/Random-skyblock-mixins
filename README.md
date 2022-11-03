# Renewables

> Grass -> by growing a mega jungle tree surrounding grass placed on dirt

> Lava -> through wandering trader (single use trade) (through the nether portal netherack renewability but with very low probability)

> Netherack -> by making a nether portal frame with 20% to 33% crying obsidian (also need to have corners filled in) in the frame and at least half of the blocks on top (including air) being gold block on top. It will spread netherack to stone block at lower levels of the portal (overworld only) (probably will make it only work in ruined portal bounding box)

> Nylium -> by making a portal (the one for the netherack) in nether when you barter with a piglin nearby the portal it has a chance to cancel the barter and turn the netherack below it to the respective nylium of the biome (so only works in crimson or warped forest biome) (needs to be very large like at least 7x10 so (made from 38 blocks of either obsidian or crying obsidian filled with corners))

> glow berries -> by growing a mega 2x2 azalea tree

> piglins in the overworld by hitting pigs with a lightning bolt pigs will convert into piglins

> Arctic foxes spawn with snowball instead of feather,foxes spawn with sweet berries instead of wheat in their mouth

</br>

# Implemented currently
- [x] Lava (wandering trader)
- [x] Foxes
- [x] Nether portal spread lava and netherack (everywhere in overworld)
- [x] Grass spread through large jungle trees
- [x] crying obsidian portal
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
mixin.crying_portals=true
mixin.spread_netherack=true
mixin.snowballs_with_hands=true
mixin.more_wandering_trades=true
mixin.overworld_piglins=true
mixin.renewable_grass=true
mixin.foxes_with_berries=true
mixin.faster_wandering_trader_spawn=true
mixin.large_azalea_tree=true
```
