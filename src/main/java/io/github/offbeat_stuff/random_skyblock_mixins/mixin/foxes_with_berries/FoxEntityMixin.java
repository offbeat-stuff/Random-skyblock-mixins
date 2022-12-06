package io.github.offbeat_stuff.random_skyblock_mixins.mixin.foxes_with_berries;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;

@Mixin(FoxEntity.class)
public abstract class FoxEntityMixin extends Entity {
  private FoxEntityMixin(EntityType<? extends FoxEntity> type, World world) {
    super(type, world);
  }

  @Shadow
  public abstract FoxEntity.Variant getVariant();

  @Inject(method = "initEquipment", at = @At(value = "HEAD"), cancellable = true)
  private void changeEquipment(RandomGenerator random, LocalDifficulty difficulty, CallbackInfo ci) {
    if (random.nextFloat() < 0.2F) {
      float f = random.nextFloat();
      ItemStack itemInMouth;

      if (f < 0.05F) {
        itemInMouth = new ItemStack(Items.EMERALD);
      } else if (f < 0.2F) {
        itemInMouth = new ItemStack(Items.EGG);
      } else if (f < 0.4F) {
        itemInMouth = random.nextBoolean() ? new ItemStack(Items.RABBIT_FOOT) : new ItemStack(Items.RABBIT_HIDE);
      } else if (f < 0.6F) {
        itemInMouth = new ItemStack(Items.SWEET_BERRIES); // was Wheat
      } else if (f < 0.8F) {
        itemInMouth = new ItemStack(Items.LEATHER);
      } else {
        var type = this.getVariant();
        itemInMouth = new ItemStack(type.equals(FoxEntity.Variant.RED) ? Items.FEATHER : Items.SNOWBALL); // Feather
      }

      this.equipStack(EquipmentSlot.MAINHAND, itemInMouth);
    }
    ci.cancel();
  }
}
