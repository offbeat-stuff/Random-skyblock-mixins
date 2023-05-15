package io.github.offbeat_stuff.random_skyblock_mixins.mixin.renewable_mycelium;

import java.util.Set;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.HugeMushroomFeature;
import net.minecraft.world.gen.feature.HugeMushroomFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.util.random.RandomGenerator;

@Mixin(HugeMushroomFeature.class)
public class HugeMushroomFeatureMixin {

  private void generateMycelium(StructureWorldAccess level, RandomGenerator random, BlockPos pos) {
    var decorator = new AlterGroundTreeDecorator(BlockStateProvider.of(Blocks.MYCELIUM));
    decorator.generate(new TreeDecorator.Placer(
        level,
        (blockPos, blockState) -> level.setBlockState(blockPos, blockState, Block.NOTIFY_ALL),
        random,
        Set.of(pos),
        Set.of(),
        Set.of()));
  }

  @Inject(method = "place", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILSOFT)
  private void generateMycelium(
      FeatureContext<HugeMushroomFeatureConfig> context,
      CallbackInfoReturnable<Boolean> cir,
      StructureWorldAccess level,
      BlockPos pos,
      RandomGenerator random) {
    generateMycelium(level, random, pos);
  }

}
