package io.github.offbeat_stuff.random_skyblock_mixins;

import java.util.List;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CaveVines;
import net.minecraft.unmapped.C_rgeelgbr;
import net.minecraft.util.Holder;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.feature.util.ConfiguredFeatureUtil;
import net.minecraft.world.gen.foliage.JungleFoliagePlacer;
import net.minecraft.world.gen.foliage.RandomSpreadFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.treedecorator.LeavesVineTreeDecorator;
import net.minecraft.world.gen.treedecorator.TrunkVineTreeDecorator;
import net.minecraft.world.gen.trunk.GiantTrunkPlacer;
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

  public static Holder<ConfiguredFeature<TreeFeatureConfig, ?>> MEGA_AZALEA_TREE = ConfiguredFeatureUtil.register(
      "mega_azalea_tree",
      Feature.TREE,
      new TreeFeatureConfig.Builder(
          BlockStateProvider.of(Blocks.OAK_LOG),
          new GiantTrunkPlacer(7, 4, 0),
          new WeightedBlockStateProvider(
              DataPool.<BlockState>builder().add(Blocks.AZALEA_LEAVES.getDefaultState(), 3)
                  .add(Blocks.FLOWERING_AZALEA_LEAVES.getDefaultState(), 1)),
          new RandomSpreadFoliagePlacer(
              UniformIntProvider.create(3, 4), ConstantIntProvider.ZERO,
              UniformIntProvider.create(3, 4), 250),
          new TwoLayersFeatureSize(1, 1, 2))
          .dirtProvider(BlockStateProvider.of(Blocks.ROOTED_DIRT))
          .forceDirt()
          .decorators(ImmutableList.of(new C_rgeelgbr(
              0.7F,
              1,
              0,
              BlockStateProvider.of(Blocks.CAVE_VINES.getDefaultState().with(CaveVines.BERRIES, Boolean.valueOf(true))),
              2,
              List.of(Direction.DOWN))))
          .build());

  @Override
  public void onInitialize(ModContainer mod) {
    LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());
  }
}
