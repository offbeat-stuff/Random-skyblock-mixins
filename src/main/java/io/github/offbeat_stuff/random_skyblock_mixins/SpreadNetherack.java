package io.github.offbeat_stuff.random_skyblock_mixins;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.AxisDirection;
import net.minecraft.util.random.RandomGenerator;

public class SpreadNetherack {
  public static void trySpreadAt(ServerWorld world, BlockPos posI, RandomGenerator random) {
    var pos = new BlockPos(posI);

    if (world.isClient)
      return;

    // check Portal height
    var downTo = portalBlocksInDirection(world, pos, Direction.DOWN);
    var upTo = portalBlocksInDirection(world, pos, Direction.UP);

    var height = downTo + upTo + 1;

    pos = pos.down(downTo);

    // we have bottom pos lets go
    var axis = world.getBlockState(pos).get(NetherPortalBlock.AXIS);
    var rightDir = Direction.from(axis, AxisDirection.POSITIVE);

    var rightTo = portalBlocksInDirection(world, pos, rightDir);
    var leftTo = portalBlocksInDirection(world, pos, rightDir.getOpposite());

    var width = rightTo + leftTo + 1;

    var cryingObsidian = 0;
    var obsidian = 0;

    // count all crying obsidian and obsidian
    {
      var leftMost = pos.down().offset(axis, -leftTo - 1).mutableCopy();
      switch (checkStateAt(world, leftMost)) {
        case 0:
          cryingObsidian++;
          break;
        case 1:
          obsidian++;
          break;
        default:
          return;
      }
      for (int i = -1; i < width; i++) {
        leftMost.move(rightDir);
        switch (checkStateAt(world, leftMost)) {
          case 0:
            cryingObsidian++;
            break;
          case 1:
            obsidian++;
            break;
          default:
            return;
        }
      }
      for (int i = -1; i < height; i++) {
        leftMost.move(Direction.UP);
        switch (checkStateAt(world, leftMost)) {
          case 0:
            cryingObsidian++;
            break;
          case 1:
            obsidian++;
            break;
          default:
            return;
        }
      }
      for (int i = -1; i < width; i++) {
        leftMost.move(rightDir.getOpposite());
        switch (checkStateAt(world, leftMost)) {
          case 0:
            cryingObsidian++;
            break;
          case 1:
            obsidian++;
            break;
          default:
            return;
        }
      }
      for (int i = 0; i < height; i++) {
        leftMost.move(Direction.DOWN);
        switch (checkStateAt(world, leftMost)) {
          case 0:
            cryingObsidian++;
            break;
          case 1:
            obsidian++;
            break;
          default:
            return;
        }
      }
    }

    if (cryingObsidian * 3 > obsidian || cryingObsidian * 5 < obsidian)
      return;

    var gold = 0;
    var leftMost = pos.up(height + 1).offset(axis, -leftTo - 1).mutableCopy();

    for (int i = -2; i < width; i++) {
      if (world.getBlockState(leftMost).isOf(Blocks.GOLD_BLOCK))
        gold++;
      leftMost = leftMost.move(rightDir);
    }

    if (gold < 2)
      return;

    for (int i = 0; i < 10; i++) {
      if (random.nextInt(10) != 0)
        return;
      var f = random.nextFloat();
      var k = 1 / 6F;
      if (f < k) {
        pos = pos.up();
      } else if (f < 2 * k) {
        pos = pos.down();
      } else if (f < 3 * k) {
        pos = pos.east();
      } else if (f < 4 * k) {
        pos = pos.west();
      } else if (f < 5 * k) {
        pos = pos.south();
      } else {
        pos = pos.north();
      }

      if (world.getBlockState(pos).isOf(Blocks.WATER)) {
        if (random.nextInt(10) == 0) {
          world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NO_REDRAW);
          world.setBlockState(pos, Blocks.LAVA.getDefaultState(), Block.NOTIFY_ALL);
        }
        return;
      }

      if (world.getBlockState(pos).isOf(Blocks.NETHERRACK))
        continue;

      if (world.getBlockState(pos).isOf(Blocks.STONE)) {
        world.setBlockState(pos, Blocks.NETHERRACK.getDefaultState(), Block.NOTIFY_ALL);
        continue;
      }

      if (!world.isAir(pos))
        return;
    }
  }

  private static Integer checkStateAt(ServerWorld world, BlockPos pos) {
    var state = world.getBlockState(pos);
    if (state.isOf(Blocks.CRYING_OBSIDIAN))
      return 0;
    if (state.isOf(Blocks.OBSIDIAN))
      return 1;
    return 2;
  }

  private static Integer portalBlocksInDirection(ServerWorld world, BlockPos pos, Direction dir) {
    var tempPos = pos.mutableCopy();
    var r = 0;
    while (true) {
      tempPos.move(dir);
      if (!world.getBlockState(tempPos).isOf(Blocks.NETHER_PORTAL))
        return r;
      r++;
    }
  }
}
