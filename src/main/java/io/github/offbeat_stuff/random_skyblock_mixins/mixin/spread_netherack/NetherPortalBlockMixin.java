package io.github.offbeat_stuff.random_skyblock_mixins.mixin.spread_netherack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.offbeat_stuff.random_skyblock_mixins.SpreadNetherack;
import net.minecraft.block.BlockState;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;

@Mixin(NetherPortalBlock.class)
public class NetherPortalBlockMixin {
  @Inject(method = "randomTick", at = @At(value = "HEAD"))
  private void spreadNetherack(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random,
      CallbackInfo ci) {
    if (!world.getDimension().natural())
      return;
    SpreadNetherack.trySpreadAt(world, pos, random);
  }
}
