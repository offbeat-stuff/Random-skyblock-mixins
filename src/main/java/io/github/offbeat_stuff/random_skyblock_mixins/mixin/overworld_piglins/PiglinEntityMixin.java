package io.github.offbeat_stuff.random_skyblock_mixins.mixin.overworld_piglins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.PiglinEntity;

@Mixin(PiglinEntity.class)
public class PiglinEntityMixin {

  @Inject(method = "mobTick", at = @At(value = "HEAD"))
  private void stopCheating(CallbackInfo ci) {
    var piglin = (Entity) ((Object) this);
    if (piglin.getWorld().getDimension().isPiglinSafe()) {
      piglin.removeScoreboardTag("OVERWORLD_PIGLIN");
    }
  }
}
