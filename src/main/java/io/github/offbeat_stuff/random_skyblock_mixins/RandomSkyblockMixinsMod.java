package io.github.offbeat_stuff.random_skyblock_mixins;

import java.util.List;
import java.util.Set;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.registry.api.event.RegistryEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CaveVines;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.JungleFoliagePlacer;
import net.minecraft.world.gen.foliage.RandomSpreadFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.treedecorator.AttachedToLeavesTreeDecorator;
import net.minecraft.world.gen.treedecorator.LeavesVineTreeDecorator;
import net.minecraft.world.gen.treedecorator.TrunkVineTreeDecorator;
import net.minecraft.world.gen.trunk.GiantTrunkPlacer;
import net.minecraft.world.gen.trunk.MegaJungleTrunkPlacer;

public class RandomSkyblockMixinsMod implements ModInitializer {
  // This logger is used to write text to the console and the log file.
  // It is considered best practice to use your mod name as the logger's name.
  // That way, it's clear which mod wrote info, warnings, and errors.
  public static final Logger LOGGER = LoggerFactory.getLogger("Random Skyblock Mixins Mod");
  public static final String NAMESPACE = "random_skyblock_mixins";

  private static final Identifier MEGA_JUNGLE_TREE_GRASS = new Identifier(NAMESPACE, "mega_jungle_tree_grass");
  public static final RegistryKey<ConfiguredFeature<?, ?>> MEGA_JUNGLE_TREE_GRASS_FEATURE = RegistryKey
      .of(RegistryKeys.CONFIGURED_FEATURE, MEGA_JUNGLE_TREE_GRASS);

  private static final Identifier MEGA_AZALEA_TREE = new Identifier(NAMESPACE, "mega_azalea_tree");
  public static final RegistryKey<ConfiguredFeature<?, ?>> MEGA_AZALEA_TREE_FEATURE = RegistryKey
      .of(RegistryKeys.CONFIGURED_FEATURE, MEGA_AZALEA_TREE);

  @Override
  public void onInitialize(ModContainer mod) {
    LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());
    RegistryEvents.DYNAMIC_REGISTRY_SETUP.register(context -> {
      context.withRegistries(registeries -> {
        var configuredRegistry = registeries.get(RegistryKeys.CONFIGURED_FEATURE);

        var jungleFeature = new ConfiguredFeature<>(Feature.TREE, new TreeFeatureConfig.Builder(
            BlockStateProvider.of(Blocks.JUNGLE_LOG),
            new MegaJungleTrunkPlacer(10, 2, 19),
            BlockStateProvider.of(Blocks.JUNGLE_LEAVES),
            new JungleFoliagePlacer(ConstantIntProvider.create(2),
                ConstantIntProvider.create(0), 2),
            new TwoLayersFeatureSize(1, 1, 2))
            .decorators(
                ImmutableList.of(TrunkVineTreeDecorator.INSTANCE,
                    new LeavesVineTreeDecorator(0.25F),
                    new SpreadGrassTreeDecorator()))
            .build());
        Registry.register(configuredRegistry, MEGA_JUNGLE_TREE_GRASS_FEATURE,
            jungleFeature);

        var azaleaFeature = new ConfiguredFeature<>(Feature.TREE, new TreeFeatureConfig.Builder(
            BlockStateProvider.of(Blocks.OAK_LOG),
            new GiantTrunkPlacer(6, 4, 0),
            new WeightedBlockStateProvider(
                DataPool.<BlockState>builder()
                    .add(Blocks.AZALEA_LEAVES
                        .getDefaultState(), 3)
                    .add(Blocks.FLOWERING_AZALEA_LEAVES
                        .getDefaultState(), 1)),
            new RandomSpreadFoliagePlacer(
                ConstantIntProvider.create(3), ConstantIntProvider.ZERO,
                UniformIntProvider.create(2, 3), 200),
            new TwoLayersFeatureSize(1, 1, 2))
            .dirtProvider(BlockStateProvider.of(Blocks.ROOTED_DIRT))
            .forceDirt()
            .decorators(ImmutableList.of(new AttachedToLeavesTreeDecorator(
                0.4F,
                1,
                0,
                BlockStateProvider.of(
                    Blocks.CAVE_VINES.getDefaultState()
                        .with(CaveVines.BERRIES,
                            Boolean.valueOf(true))),
                3,
                List.of(Direction.DOWN))))
            .build());
        Registry.register(configuredRegistry, MEGA_AZALEA_TREE_FEATURE,
            azaleaFeature);

      }, Set.of(RegistryKeys.CONFIGURED_FEATURE));
    });
  }
}
