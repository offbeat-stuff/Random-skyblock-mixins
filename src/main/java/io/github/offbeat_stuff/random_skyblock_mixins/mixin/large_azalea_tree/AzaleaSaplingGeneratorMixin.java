package io.github.offbeat_stuff.random_skyblock_mixins.mixin.large_azalea_tree;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import io.github.offbeat_stuff.random_skyblock_mixins.RandomSkyblockMixinsMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.sapling.AzaleaSaplingGenerator;
import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Holder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;

@Mixin(AzaleaSaplingGenerator.class)
public class AzaleaSaplingGeneratorMixin extends SaplingGenerator {
  @Override
  public boolean generate(ServerWorld world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state,
      RandomGenerator random) {
    for (int i = 0; i >= -1; --i) {
      for (int j = 0; j >= -1; --j) {
        if (LargeTreeSaplingGenerator.canGenerateLargeTree(state, world, pos, i, j)) {
          return this.generateLargeTree(world, chunkGenerator, pos, state, random, i, j);
        }
      }
    }
    return super.generate(world, chunkGenerator, pos, state, random);
  }

  public boolean generateLargeTree(ServerWorld world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state,
      RandomGenerator random, int x, int z) {
    Holder<? extends ConfiguredFeature<?, ?>> lv = RandomSkyblockMixinsMod.MEGA_AZALEA_TREE;
    if (lv == null) {
      return false;
    } else {
      ConfiguredFeature<?, ?> lv2 = (ConfiguredFeature<?, ?>) lv.value();
      BlockState lv3 = Blocks.AIR.getDefaultState();
      world.setBlockState(pos.add(x, 0, z), lv3, Block.NO_REDRAW);
      world.setBlockState(pos.add(x + 1, 0, z), lv3, Block.NO_REDRAW);
      world.setBlockState(pos.add(x, 0, z + 1), lv3, Block.NO_REDRAW);
      world.setBlockState(pos.add(x + 1, 0, z + 1), lv3, Block.NO_REDRAW);
      if (lv2.generate(world, chunkGenerator, random, pos.add(x, 0, z))) {
        return true;
      } else {
        world.setBlockState(pos.add(x, 0, z), state, Block.NO_REDRAW);
        world.setBlockState(pos.add(x + 1, 0, z), state, Block.NO_REDRAW);
        world.setBlockState(pos.add(x, 0, z + 1), state, Block.NO_REDRAW);
        world.setBlockState(pos.add(x + 1, 0, z + 1), state, Block.NO_REDRAW);
        return false;
      }
    }
  }

  @Shadow
  @Override
  protected Holder<? extends ConfiguredFeature<?, ?>> getTreeFeature(RandomGenerator arg0, boolean arg1) {
    return TreeConfiguredFeatures.AZALEA_TREE;
  }

}
