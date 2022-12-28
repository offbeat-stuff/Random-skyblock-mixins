package io.github.offbeat_stuff.random_skyblock_mixins.mixin.one_time_lava;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.task.GiveGiftsToHeroTask;
import net.minecraft.entity.ai.brain.task.LookTargetUtil;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;

@Mixin(GiveGiftsToHeroTask.class)
public class GiveGiftsToHeroTaskMixin {

  @Inject(at = @At("HEAD"), method = "giveGifts", cancellable = true)
  private void giveLavaBucket(VillagerEntity villager, LivingEntity recipient, CallbackInfo cir) {
    if (villager.getVillagerData().getProfession().equals(VillagerProfession.WEAPONSMITH)
        && recipient instanceof ServerPlayerEntity player && !player.getAdvancementTracker()
            .getProgress(player.server.getAdvancementLoader().get(new Identifier("minecraft", "story/lava_bucket")))
            .isDone()
        && villager.getRandom().nextInt(5) == 0) {
      LookTargetUtil.throwStack(villager, Items.LAVA_BUCKET.getDefaultStack(), recipient.getPos());
      cir.cancel();
    }
  }
}
