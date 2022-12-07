package io.github.offbeat_stuff.random_skyblock_mixins.mixin.renewable_netherack_and_nylium;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.registry.Holder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.PortalForcer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

@Mixin(PortalForcer.class)
public class PortalForcerMixin {
  @Shadow
  @Final
  private ServerWorld world;

  private BlockState getExtensionBlock(Holder<Biome> biome) {
    if (biome.isRegistryKey(BiomeKeys.CRIMSON_FOREST) && world.getRandom().nextInt(10) == 0)
      return Blocks.CRIMSON_NYLIUM.getDefaultState();
    if (biome.isRegistryKey(BiomeKeys.WARPED_FOREST) && world.getRandom().nextInt(10) == 0)
      return Blocks.WARPED_NYLIUM.getDefaultState();
    return Blocks.NETHERRACK.getDefaultState();
  }

  @Redirect(method = "createPortal", at = @At(value = "INVOKE", target = "net/minecraft/server/world/ServerWorld.setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)Z", ordinal = 0))
  private boolean changeExtensionBlock(ServerWorld world, BlockPos pos, BlockState state) {
    if (state.isOf(Blocks.OBSIDIAN))
      return world.setBlockState(pos, getExtensionBlock(world.getBiome(pos)));
    return world.setBlockState(pos, state);
  }
}
