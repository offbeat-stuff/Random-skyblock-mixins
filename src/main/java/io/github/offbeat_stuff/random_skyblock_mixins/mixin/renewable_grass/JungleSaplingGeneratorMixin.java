package io.github.offbeat_stuff.random_skyblock_mixins.mixin.renewable_grass;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.offbeat_stuff.random_skyblock_mixins.RandomSkyblockMixinsMod;
import net.minecraft.block.sapling.JungleSaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;

@Mixin(JungleSaplingGenerator.class)
public class JungleSaplingGeneratorMixin {

  @Inject(method = "getLargeTreeFeature", at = @At(value = "HEAD"), cancellable = true)
  private void changeLargeTreeFeature(CallbackInfoReturnable<RegistryKey<ConfiguredFeature<?, ?>>> cir) {
    cir.setReturnValue(RandomSkyblockMixinsMod.MEGA_JUNGLE_TREE_GRASS_FEATURE);
    cir.cancel();
  }
}
