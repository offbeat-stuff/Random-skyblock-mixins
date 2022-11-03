package io.github.offbeat_stuff.random_skyblock_mixins.mixin.crying_portals;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.dimension.AreaHelper;

@Mixin(AbstractFireBlock.class)
public class AbstractFireBlockMixin {

  @Shadow
  private static boolean isOverworldOrNether(World world) {
    return false;
  }

  @Inject(method = "shouldLightPortalAt", at = @At(value = "HEAD"), cancellable = true)
  private static void useCryingPortals(World world, BlockPos pos, Direction direction,
      CallbackInfoReturnable<Boolean> cir) {
    if (!isOverworldOrNether(world)) {
      cir.setReturnValue(false);
    } else {
      BlockPos.Mutable mutable = pos.mutableCopy();
      boolean res = false;

      for (Direction direction2 : Direction.values()) {
        mutable.set(pos).move(direction2);
        if (world.getBlockState(mutable).isOf(Blocks.OBSIDIAN)
            || world.getBlockState(mutable).isOf(Blocks.CRYING_OBSIDIAN)) {
          res = true;
          break;
        }
      }

      if (!res) {
        cir.setReturnValue(false);
      } else {
        Direction.Axis axis = direction.getAxis().isHorizontal()
            ? direction.rotateYCounterclockwise().getAxis()
            : Direction.Type.HORIZONTAL.randomAxis(world.random);
        cir.setReturnValue(AreaHelper.getNewPortal(world, pos, axis).isPresent());
      }
    }
    cir.cancel();
  }
}
