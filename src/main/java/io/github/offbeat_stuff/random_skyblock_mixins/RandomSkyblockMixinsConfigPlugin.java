package io.github.offbeat_stuff.random_skyblock_mixins;

import org.quiltmc.loader.api.QuiltLoader;
import net.caffeinemc.caffeineconfig.AbstractCaffeineConfigMixinPlugin;
import net.caffeinemc.caffeineconfig.CaffeineConfig;

public class RandomSkyblockMixinsConfigPlugin extends AbstractCaffeineConfigMixinPlugin {

  @Override
  protected CaffeineConfig createConfig() {
    return CaffeineConfig.builder("RandomSkyblockMixins")
        .addMixinOption("renewable_mycelium", false)
        .addMixinOption("skyblock_wandering_trader_trades", false)
        .addMixinOption("one_time_lava", false)
        .addMixinOption("snowballs_with_hands", false)
        .addMixinOption("renewable_deepslate_and_end_stone", false)
        .addMixinOption("overworld_piglins", false)
        .addMixinOption("renewable_grass", false)
        .addMixinOption("renewable_netherack_and_nylium", false)
        .addMixinOption("foxes_with_berries", false)
        .addMixinOption("faster_wandering_trader_spawn", false)
        .addMixinOption("large_azalea_tree", false)
        // .withInfoUrl("https://example.org")
        .build(QuiltLoader.getConfigDir().resolve("randomskyblockmixins.properties"));
  }

  @Override
  protected String mixinPackageRoot() {
    return "io.github.offbeat_stuff.random_skyblock_mixins.mixin.";
  }
}
