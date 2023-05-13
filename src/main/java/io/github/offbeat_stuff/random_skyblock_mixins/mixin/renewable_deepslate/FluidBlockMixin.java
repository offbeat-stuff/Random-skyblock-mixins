package io.github.offbeat_stuff.random_skyblock_mixins.mixin.renewable_deepslate;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.dimension.DimensionTypes;

@Mixin(FluidBlock.class)
public abstract class FluidBlockMixin {

  @Shadow
  private void playExtinguishSound(WorldAccess world, BlockPos pos) {

  };

  // Replaces a fluid block with deepslate or end stone when it receives fluid
  // from a neighboring block.
  @Inject(method = "receiveNeighborFluids", at = @At(value = "INVOKE", target = "Lnet/minecraft/fluid/FluidState;isSource()Z"), cancellable = true)
  private void receiveFluidToDeepslate(World world, BlockPos pos, BlockState state,
      CallbackInfoReturnable<Boolean> cir) {
    if (world.getFluidState(pos).isSource())
      return;

    // Choose the block to replace the fluid with, based on the current dimension.
    var block = Blocks.COBBLED_DEEPSLATE;

    if (((World) world).getDimensionKey().equals(DimensionTypes.THE_END))
      block = Blocks.END_STONE;
    else if (pos.getY() >= 0)
      return;

    world.setBlockState(pos, block.getDefaultState());
    this.playExtinguishSound(world, pos);
    cir.setReturnValue(false);
    cir.cancel();
  }
}
