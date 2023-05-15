# **Warning**
* Attention: All the features are disabled by default.

# Final planned renewable resource methods

- Grass Block: by growing a mega jungle tree surrounding grass placed on dirt.
- Netherack (+Nylium): a floating nether portal generates with 4 netherack as an extension, and there is a 1 in 10 chance of it being a nylium (for the corresponding biome).
- Glow Berries: by growing a mega 2x2 azalea tree will produce glow berries.
- Piglins: by striking pigs with a lightning bolt in the overworld, they will convert into piglins.
- Netherite: by trading with piglins in the overworld (through lightning) before they convert.
- Sweet Berries (+Snowball): Arctic foxes spawn with snowballs instead of feathers, and foxes spawn with sweet berries instead of wheat in their mouths.
- Endstone: Endstone can be generated through a cobblestone generator in the End.
- Deepslate: By Using a cobblestone generator below y=0.
- Mycelium: By growing huge mushroom in Mushroom Fields biome.
- Snowball: By breaking them with hands (make sense right).
- Lava (One time only): By getting Hotv gift from **Weaponsmith**.
- Having more bedrock parity for more renewables like coffee beans,grindstone etc.

# Implemented currently
- [x] Foxes (berries + snowball)
- [x] Grass spread through large jungle trees
- [x] caffeine config (for disabling mixins using a config)
- [x] faster wandering trader spawn (max chance increased from 7.5% to 15%) (spawnDelay decreased from 20 minutes to 5 minutes) (so a trader should spawn in 30 minutes on averages, vanilla is 4 hours)
- [x] 2x2 azalea trees (little stable)
- [x] snow dropping snowballs without requiring tools
- [x] piglins spawning when pigs are hit by lightning
- [x] one time lava trade

# Todo
- [ ] Implement custom FoliagePlacer for 2x2 azalea tree so on growing you dont find glow berries on ground instantly
- [ ] Implement datapack to enable netherite trading
- [ ] Add bedrock parity for grindstone,coffee beans etc.
- [ ] Renewable Mycelium

# Usage
- Note: All features are disabled by default and features cannot be toggled ingame

Mixins can be enabled by editing `.minecraft/config/randomskyblockmixins.properties` file

Here is a sample config with everything enabled

```java
mixin.renewable_mycelium=true
mixin.skyblock_wandering_trader_trades=true
mixin.one_time_lava=true
mixin.snowballs_with_hands=true
mixin.renewable_deepslate_and_end_stone=true
mixin.overworld_piglins=true
mixin.renewable_grass=true
mixin.renewable_netherack_and_nylium=true
mixin.foxes_with_berries=true
mixin.faster_wandering_trader_spawn=true
mixin.large_azalea_tree=true
```
