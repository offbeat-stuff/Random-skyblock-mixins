package io.github.offbeat_stuff.random_skyblock_mixins.mixin.renewable_netherack_and_nylium;

import java.util.Optional;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.registry.Holder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockLocating;
import net.minecraft.world.PortalForcer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

@Mixin(PortalForcer.class)
public class PortalForcerMixin {
  @Shadow
  @Final
  private ServerWorld world;

  private BlockState getExtensionBlock(Holder<Biome> biome) {
    if (biome.isRegistryKey(BiomeKeys.CRIMSON_FOREST))
      return Blocks.CRIMSON_NYLIUM.getDefaultState();
    if (biome.isRegistryKey(BiomeKeys.WARPED_FOREST))
      return Blocks.WARPED_NYLIUM.getDefaultState();
    return Blocks.NETHERRACK.getDefaultState();
  }

  @Inject(method = "createPortal", at = @At(value = "CONSTANT", args = "intValue=-1", ordinal = 4), locals = LocalCapture.CAPTURE_FAILSOFT)
  private void changeExtensionBlock(BlockPos pos, Direction.Axis axis,
      CallbackInfoReturnable<Optional<BlockLocating.Rectangle>> cir, Direction direction, double d, BlockPos blockPos) {
    var mutable = new BlockPos.Mutable();
    var dir = direction.rotateYClockwise();
    for (int i = -1; i < 3; ++i) { // i coordinate parallel to portal
      for (int j = -2; j < 3; ++j) { // j coordinate perpendicular to portal
        if ((Math.abs(j) == 1 && (i == -1 || i == 2)) ||
            (Math.abs(j) == 2 && (i == 0 || i == 1))) {
          mutable.set(blockPos, direction.getOffsetX() * i + dir.getOffsetX() * j, -1,
              direction.getOffsetZ() * i + dir.getOffsetZ() * j);
          world.setBlockState(mutable, getExtensionBlock(world.getBiome(mutable)));
        }
      }
    }
  }
}
