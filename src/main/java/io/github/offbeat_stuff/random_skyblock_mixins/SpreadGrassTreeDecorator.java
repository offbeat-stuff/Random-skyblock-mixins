package io.github.offbeat_stuff.random_skyblock_mixins;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecorator;

public class SpreadGrassTreeDecorator extends AlterGroundTreeDecorator {

  public SpreadGrassTreeDecorator() {
    super(BlockStateProvider.of(Blocks.GRASS_BLOCK));
  }

  @Override
  public void generate(TreeDecorator.Placer ctx) {
    ArrayList<BlockPos> list = Lists.newArrayList();
    ObjectArrayList<BlockPos> list2 = ctx.getRootPositions(); // Context.roots()
    ObjectArrayList<BlockPos> list3 = ctx.getLogPositions(); // Context.logs()

    // get the stuff that is at lowest (either logs or combined or roots)

    if (list2.isEmpty()) {
      list.addAll(list3);
    } else if (!list3.isEmpty() && ((BlockPos) list2.get(0)).getY() == ((BlockPos) list3.get(0)).getY()) {
      list.addAll(list3);
      list.addAll(list2);
    } else {
      list.addAll(list2);
    }

    if (!list.isEmpty()) {
      int i = ((BlockPos) list.get(0)).getY();
      // get all the stuff at lowest place using filter
      list.stream().filter(pos -> pos.getY() == i).forEach(pos -> {
        for (int x = -1; x <= 1; x++) {
          for (int y = -1; y <= 1; y++) {
            for (int z = -1; z <= 1; z++) {
              if (x == 0 && y == 0 && z == 0)
                continue;
              handlePos(ctx, pos.add(x, y, z), 0);
            }
          }
        }

        // ctx.m_nouqmltl().nextInt(64); // ctx.random().nextInt(64);
      });
    }
  }

  private void handlePos(TreeDecorator.Placer ctx, BlockPos pos, int distance) {
    if (!testPosForConversionToGrass(ctx, pos.down()))
      return;
    placeBlockAt(ctx, pos.down());
    if (distance > 5)
      return;
    if (ctx.getRandom().nextInt(2) != 0) // this is actually calling a random function so there is 2/3 chance of it to
                                         // stop the spreading
      return;
    for (int x = -1; x <= 1; x++) {
      for (int y = -1; y <= 1; y++) {
        for (int z = -1; z <= 1; z++) {
          if (x == 0 && z == 0) // not spread vertically
            continue;
          handlePos(ctx, pos.add(x, y, z), distance + 1);
        }
      }
    }
  }

  private boolean isGrass(BlockState state) {
    return state.isOf(Blocks.GRASS);
  }

  private boolean isDirt(BlockState state) {
    return state.isOf(Blocks.DIRT);
  }

  private boolean testPosForConversionToGrass(TreeDecorator.Placer ctx, BlockPos pos) {
    return ctx.getWorld().testBlockState(pos, this::isDirt)
        && ctx.getWorld().testBlockState(pos.up(), this::isGrass);
  }

  private void placeBlockAt(TreeDecorator.Placer ctx, BlockPos pos) {
    ctx.replace(pos, Blocks.GRASS_BLOCK.getDefaultState()); // set block
    ctx.replace(pos.up(), Blocks.AIR.getDefaultState()); // set block
  }
}
