package models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.inject.Singleton;
import lombok.Getter;
import models.beverages.Beverage;
import models.ingredients.GingerSyrup;
import models.ingredients.GreenMixture;
import models.ingredients.HotMilkIngredient;
import models.ingredients.HotWaterIngredient;
import models.ingredients.Ingredient;
import models.ingredients.SugarSyrup;
import models.ingredients.TeaLeavesSyrup;

import java.util.HashMap;
import java.util.Map;

import static common.Utils.getLongValue;

@Getter
public class Quantity {

  private Map<String, Ingredient> ingredients;
  private static volatile Quantity totalQuantity =null;
  private Quantity()
  {
    //Singleton Class hence private constructor
  }

  // static method to create instance of Singleton class
  public static Quantity getTotalQuantity()
  {

    // To ensure only one instance is created
    if (totalQuantity == null)
    {
      Map<String, Ingredient> ingredients  = new HashMap<>();
      totalQuantity = new Quantity();
      totalQuantity.ingredients = ingredients;
    }
    return totalQuantity;
  }

  // IMP : This function will validate and upgrade the ingredients w.r.t given beverage
  public synchronized void validateAndUpdateTotalQuantity(Beverage beverage) {
    Quantity remainingQuantity = Quantity.getTotalQuantity();
    if(beverage.validate(remainingQuantity.getIngredients()))
      beverage.updateGlobalIngredients(remainingQuantity);
  }

  //This function will process ingredients from total_items_quantity and store it in a totalQuantity singleton object
  public void parseAndSetIngredients(HashMap<String, Object> quantityMap) {
    Map<String, Ingredient> ingredients = Quantity.getTotalQuantity().getIngredients();

    quantityMap.forEach((k,v) -> {
      Long value = getLongValue(v);
      //TODO: Initialize constants
      if(k.equals("hot_water")) {
        if(ingredients.containsKey("hot_water")) {
          Long existingHotWater =ingredients.get("hot_water").getQuantity();
          ingredients.get("hot_water").setQuantity(existingHotWater + value);
        } else {
          ingredients.put("hot_water", HotWaterIngredient.builder().quantity(value).build());
        }
      }

      else if(k.equals("hot_milk")) {
        if(ingredients.containsKey("hot_milk")) {
          Long existingHotMilk =ingredients.get("hot_milk").getQuantity();
          ingredients.get("hot_milk").setQuantity(existingHotMilk + value);
        } else {
          ingredients.put("hot_milk", HotMilkIngredient.builder().quantity(value).build());
        }
      }

      else if(k.equals("ginger_syrup")) {
        if(ingredients.containsKey("ginger_syrup")) {
          Long existingGingerSyrup =ingredients.get("ginger_syrup").getQuantity();
          ingredients.get("ginger_syrup").setQuantity(existingGingerSyrup + value);
        } else {
          ingredients.put("ginger_syrup", GingerSyrup.builder().quantity(value).build());
        }
      }

      else if(k.equals("sugar_syrup")) {
        if(ingredients.containsKey("sugar_syrup")) {
          Long existingSugarSyrup = ingredients.get("sugar_syrup").getQuantity();
          ingredients.get("sugar_syrup").setQuantity(existingSugarSyrup + value);
        } else {
          ingredients.put("sugar_syrup", SugarSyrup.builder().quantity(value).build());
        }
      }

      else if(k.equals("tea_leaves_syrup")) {
        if(ingredients.containsKey("tea_leaves_syrup")) {
          Long existingLeavesSyrup = ingredients.get("tea_leaves_syrup").getQuantity();
          ingredients.get("tea_leaves_syrup").setQuantity(existingLeavesSyrup + value);
        } else {
          ingredients.put("tea_leaves_syrup", TeaLeavesSyrup.builder().quantity(value).build());
        }

      }

      else if (k.equals("green_mixture")) {
        if(ingredients.containsKey("green_mixture")) {
          Long existingLeavesSyrup = ingredients.get("green_mixture").getQuantity();
          ingredients.get("green_mixture").setQuantity(existingLeavesSyrup + value);
        } else {
          ingredients.put("green_mixture", GreenMixture.builder().quantity(value).build());
        }
      }
    });

    //Set Ingredients
    Quantity.getTotalQuantity().setIngredients(ingredients);
  }

  public void setIngredients(Map<String, Ingredient> ingredients) {
    this.ingredients = ingredients;
  }
}
