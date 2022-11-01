package io.github.offbeat_stuff.random_skyblock_mixins.mixin.overworld_piglins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

@Mixin(PigEntity.class)
public class PigEntityMixin extends AnimalEntity {

  private PigEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
    super(entityType, world);
  }

  @Inject(method = "onStruckByLightning", at = @At(value = "HEAD"), cancellable = true)
  private void changeToPiglins(ServerWorld world, LightningEntity lightning, CallbackInfo ci) {
    PiglinEntity lv = EntityType.PIGLIN.create(world);
    lv.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
    lv.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), this.getPitch());
    lv.setAiDisabled(this.isAiDisabled());
    lv.setBaby(this.isBaby());
    if (this.hasCustomName()) {
      lv.setCustomName(this.getCustomName());
      lv.setCustomNameVisible(this.isCustomNameVisible());
    }

    lv.setPersistent();
    world.spawnEntity(lv);
    this.discard();
    ci.cancel();
  }

  @Override
  @Shadow
  public PassiveEntity createChild(ServerWorld arg0, PassiveEntity arg1) {
    return null;
  }
}