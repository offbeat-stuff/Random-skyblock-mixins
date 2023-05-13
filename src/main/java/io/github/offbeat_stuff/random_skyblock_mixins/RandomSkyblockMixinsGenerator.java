package io.github.offbeat_stuff.random_skyblock_mixins;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.quiltmc.qsl.recipe.api.builder.ShapedRecipeBuilder;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.DefaultResourceConditions;
import net.minecraft.client.item.TooltipContext.Default;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeCategory;

public class RandomSkyblockMixinsGenerator implements DataGeneratorEntrypoint {

  @Override
  public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
    var pack = fabricDataGenerator.createPack();
    pack.addProvider(GrindstoneRecipeProvider::new);
  }

  private static class GrindstoneRecipeProvider extends FabricRecipeProvider {

    private Item[] slabs = { Items.STONE_SLAB, Items.SANDSTONE_SLAB, Items.QUARTZ_SLAB,
        Items.RED_SANDSTONE_SLAB, Items.BRICK_SLAB };
    private Item[] planks = { Items.MANGROVE_PLANKS, Items.WARPED_PLANKS, Items.CRIMSON_PLANKS };

    private GrindstoneRecipeProvider(FabricDataOutput output) {
      super(output);
    }

    @Override
    public void generateRecipes(Consumer<RecipeJsonProvider> consumer) {

      ShapedRecipeJsonFactory.create(RecipeCategory.DECORATIONS, Items.GRINDSTONE)
          .input('#', Ingredient.ofItems(slabs))
          .input('|', Ingredient.ofItems(planks))
          .input('I', Items.STICK)
          .pattern("I#I")
          .pattern("| |")
          .criterion("has_stone_slab",
              conditionsFromItemPredicates(ItemPredicate.Builder.create()
                  .items(slabs)
                  .build()))
          .criterion("has_certain_planks",
              conditionsFromItemPredicates(ItemPredicate.Builder.create()
                  .items(planks)
                  .build()))
          .offerTo(consumer);
    }

  }
}
