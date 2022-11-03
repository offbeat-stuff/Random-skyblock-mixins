package io.github.offbeat_stuff.random_skyblock_mixins.mixin.crying_portals;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.dimension.AreaHelper;

@Mixin(value = AreaHelper.class)
public class AreaHelperMixin {
  @Inject(method = "m_uenhrluo(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Z", at = @At(value = "TAIL"), cancellable = true)
  private static void isPortalBlock(BlockState blockState, BlockView blockGetter, BlockPos blockPos,
      CallbackInfoReturnable<Boolean> cir) {
    if (blockState.isOf(Blocks.CRYING_OBSIDIAN)) {
      cir.setReturnValue(true);
      cir.cancel();
    }
  }
}
