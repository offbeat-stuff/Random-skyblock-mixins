package io.github.offbeat_stuff.random_skyblock_mixins.mixin.faster_wandering_trader_spawn;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
// import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
// import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
// import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.WanderingTraderManager;

@Mixin(WanderingTraderManager.class)
public class WanderingTraderManagerMixin {

  @Shadow
  private int spawnTimer;
  @Shadow
  private int spawnDelay;
  @Shadow
  private int spawnChance;

  @ModifyConstant(method = "<init>", constant = @Constant(intValue = 24000))
  private int changeSpawnDelayInit(int value) {
    return 6000;
  }

  @ModifyConstant(method = "spawn", constant = @Constant(intValue = 24000))
  private int changeSpawnDelaySpawn(int value) {
    return 6000;
  }

  @ModifyConstant(method = "trySpawn", constant = @Constant(intValue = 10))
  private int changeSpawnChance(int value) {
    return 5;
  }

  // @Inject(method = "spawn", at = @At("HEAD"))
  // private void spawn(ServerWorld world, boolean spawnMonsters, boolean
  // spawnAnimals,
  // CallbackInfoReturnable<Integer> cir) {
  // if (spawnDelay > 6000) {
  // spawnDelay = 6000;
  // }
  // }
}
