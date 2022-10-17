package io.github.offbeat_stuff.random_skyblock_mixins;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.Blocks;
import net.minecraft.util.Holder;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.feature.util.ConfiguredFeatureUtil;
import net.minecraft.world.gen.foliage.JungleFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.LeavesVineTreeDecorator;
import net.minecraft.world.gen.treedecorator.TrunkVineTreeDecorator;
import net.minecraft.world.gen.trunk.MegaJungleTrunkPlacer;

public class RandomSkyblockMixinsMod implements ModInitializer {
  // This logger is used to write text to the console and the log file.
  // It is considered best practice to use your mod name as the logger's name.
  // That way, it's clear which mod wrote info, warnings, and errors.
  public static final Logger LOGGER = LoggerFactory.getLogger("Random Skyblock Mixins Mod");

  public static Holder<ConfiguredFeature<TreeFeatureConfig, ?>> MEGA_JUNGLE_TREE_GRASS = ConfiguredFeatureUtil
      .register("mega_jungle_tree_grass", Feature.TREE,
          new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.JUNGLE_LOG), new MegaJungleTrunkPlacer(10, 2, 19),
              BlockStateProvider.of(Blocks.JUNGLE_LEAVES),
              new JungleFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 2),
              new TwoLayersFeatureSize(1, 1, 2))
              .decorators(
                  ImmutableList.of(
                      TrunkVineTreeDecorator.INSTANCE,
                      new LeavesVineTreeDecorator(0.25F),
                      new SpreadGrassTreeDecorator()))
              .build());

  @Override
  public void onInitialize(ModContainer mod) {
    LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());
  }
}
