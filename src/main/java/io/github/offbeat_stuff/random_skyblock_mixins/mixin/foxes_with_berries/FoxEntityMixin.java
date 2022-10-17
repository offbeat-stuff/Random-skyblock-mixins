package io.github.offbeat_stuff.random_skyblock_mixins.mixin.foxes_with_berries;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;

@Mixin(FoxEntity.class)
public abstract class FoxEntityMixin extends FoxEntity {
  private FoxEntityMixin(EntityType<? extends FoxEntity> type, World world) {
    super(type, world);
  }

  @Inject(method = "initEquipment", at = @At(value = "HEAD"), cancellable = true)
  private void changeEquipment(RandomGenerator random, LocalDifficulty difficulty, CallbackInfo ci) {
    if (random.nextFloat() < 0.2F) {
      float f = random.nextFloat();
      ItemStack lv;

      if (f < 0.05F) {
        lv = new ItemStack(Items.EMERALD);
      } else if (f < 0.2F) {
        lv = new ItemStack(Items.EGG);
      } else if (f < 0.4F) {
        lv = random.nextBoolean() ? new ItemStack(Items.RABBIT_FOOT) : new ItemStack(Items.RABBIT_HIDE);
      } else if (f < 0.6F) {
        lv = new ItemStack(Items.SWEET_BERRIES); // was Wheat
      } else if (f < 0.8F) {
        lv = new ItemStack(Items.LEATHER);
      } else {
        lv = new ItemStack(this.getFoxType().equals(FoxEntity.Type.RED) ? Items.FEATHER : Items.SNOWBALL); // Feather
      }

      this.equipStack(EquipmentSlot.MAINHAND, lv);
    }
    ci.cancel();
  }
}
