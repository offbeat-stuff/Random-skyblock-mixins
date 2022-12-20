package io.github.offbeat_stuff.random_skyblock_mixins.mixin.skyblock_wandering_trader_trades;

import org.spongepowered.asm.mixin.Mixin;

import com.google.common.collect.ImmutableMap;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffers;

@Mixin(WanderingTraderEntity.class)
public class WanderingTraderEntityMixin {

  private static Int2ObjectMap<TradeOffers.Factory[]> copyToFastUtilMap(
      ImmutableMap<Integer, TradeOffers.Factory[]> map) {
    return new Int2ObjectOpenHashMap<TradeOffers.Factory[]>(map);
  }

  private static final Int2ObjectMap<TradeOffers.Factory[]> WANDERING_TRADER_TRADES = copyToFastUtilMap(
      ImmutableMap.of(
          1,
          new TradeOffers.Factory[] {
              new TradeOffers.SellItemFactory(Items.SEA_PICKLE, 2, 1, 5, 1),
              new TradeOffers.SellItemFactory(Items.FERN, 1, 1, 12, 1),
              new TradeOffers.SellItemFactory(Items.SUGAR_CANE, 1, 1, 8, 1),
              new TradeOffers.SellItemFactory(Items.KELP, 3, 1, 12, 1),
              new TradeOffers.SellItemFactory(Items.CACTUS, 3, 1, 8, 1),
              new TradeOffers.SellItemFactory(Items.BEETROOT_SEEDS, 1, 1, 12, 1),
              new TradeOffers.SellItemFactory(Items.PUMPKIN_SEEDS, 1, 1, 12, 1),
              new TradeOffers.SellItemFactory(Items.MELON_SEEDS, 1, 1, 12, 1),
              new TradeOffers.SellItemFactory(Items.ACACIA_SAPLING, 5, 1, 8, 1),
              new TradeOffers.SellItemFactory(Items.BIRCH_SAPLING, 5, 1, 8, 1),
              new TradeOffers.SellItemFactory(Items.DARK_OAK_SAPLING, 5, 1, 8, 1),
              new TradeOffers.SellItemFactory(Items.JUNGLE_SAPLING, 5, 1, 8, 1),
              new TradeOffers.SellItemFactory(Items.OAK_SAPLING, 5, 1, 8, 1),
              new TradeOffers.SellItemFactory(Items.SPRUCE_SAPLING, 5, 1, 8, 1),
              new TradeOffers.SellItemFactory(Items.MANGROVE_PROPAGULE, 5, 1, 8, 1),
              new TradeOffers.SellItemFactory(Items.BRAIN_CORAL_BLOCK, 3, 1, 8, 1),
              new TradeOffers.SellItemFactory(Items.BUBBLE_CORAL_BLOCK, 3, 1, 8, 1),
              new TradeOffers.SellItemFactory(Items.FIRE_CORAL_BLOCK, 3, 1, 8, 1),
              new TradeOffers.SellItemFactory(Items.HORN_CORAL_BLOCK, 3, 1, 8, 1),
              new TradeOffers.SellItemFactory(Items.TUBE_CORAL_BLOCK, 3, 1, 8, 1),
              new TradeOffers.SellItemFactory(Items.BROWN_MUSHROOM, 1, 1, 12, 1),
              new TradeOffers.SellItemFactory(Items.RED_MUSHROOM, 1, 1, 12, 1),
              new TradeOffers.SellItemFactory(Items.SMALL_DRIPLEAF, 1, 2, 5, 1),
              new TradeOffers.SellItemFactory(Items.SAND, 1, 8, 8, 1),
              new TradeOffers.SellItemFactory(Items.RED_SAND, 1, 4, 6, 1),
              new TradeOffers.SellItemFactory(Items.POINTED_DRIPSTONE, 1, 2, 5, 1),
              new TradeOffers.SellItemFactory(Items.MOSS_BLOCK, 1, 2, 5, 1)
          },
          2,
          new TradeOffers.Factory[] {
              new TradeOffers.SellItemFactory(Items.TROPICAL_FISH_BUCKET, 5, 1, 4, 1),
              new TradeOffers.SellItemFactory(Items.PUFFERFISH_BUCKET, 5, 1, 4, 1),
              new TradeOffers.SellItemFactory(Items.PACKED_ICE, 3, 1, 6, 1),
              new TradeOffers.SellItemFactory(Items.BLUE_ICE, 6, 1, 6, 1)
          }));

  @ModifyVariable(method = "fillRecipes", at = @At("STORE"), ordinal = 0)
  private TradeOffers.Factory[] changeFactory1(TradeOffers.Factory[] f1) {
    return WANDERING_TRADER_TRADES.get(1);
  }

  @ModifyVariable(method = "fillRecipes", at = @At("STORE"), ordinal = 1)
  private TradeOffers.Factory[] changeFactory2(TradeOffers.Factory[] f1) {
    return WANDERING_TRADER_TRADES.get(2);
  }
}
