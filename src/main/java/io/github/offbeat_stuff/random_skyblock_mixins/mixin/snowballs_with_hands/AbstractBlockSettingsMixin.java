package io.github.offbeat_stuff.random_skyblock_mixins.mixin.snowballs_with_hands;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Material;

@Mixin(AbstractBlock.Settings.class)
public class AbstractBlockSettingsMixin {
  @Shadow
  boolean toolRequired;
  @Shadow
  Material material;

  @Inject(at = @At("RETURN"), method = "requiresTool")
  private void changeRequiresTool(CallbackInfoReturnable<AbstractBlock.Settings> cir) {
    if (material.equals(Material.SNOW_LAYER))
      toolRequired = false;
  }

}
