package io.github.offbeat_stuff.random_skyblock_mixins.mixin.renewable_deepslate;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;

@Mixin(LavaFluid.class)
public abstract class LavaFluidMixin {
  @Shadow
  private void playExtinguishEvent(WorldAccess world, BlockPos pos) {

  };

  @Inject(method = "flow", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getDefaultState()Lnet/minecraft/block/BlockState;"), cancellable = true)
  private void generateDeepslate(WorldAccess world, BlockPos pos, BlockState state, Direction direction,
      FluidState fluidState, CallbackInfo ci) {
    if (pos.getY() < 0) {
      world.setBlockState(pos, Blocks.DEEPSLATE.getDefaultState(), Block.NOTIFY_ALL);
      this.playExtinguishEvent(world, pos);
      ci.cancel();
    }
  }
}
