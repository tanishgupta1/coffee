package models.beverages;

import models.Quantity;
import models.ingredients.Ingredient;

import java.util.List;
import java.util.Map;

public interface Beverage {

  // TODO: To validate what all ingredients are required
  List<String> getIngredients();

  //To validate if current beverage has sufficient ingredients
  Boolean validate(Map<String, Ingredient> globalIngredients);

  //To update global ingredients available
  void updateGlobalIngredients(Quantity quantity);
}
