package models.beverages;

import lombok.Builder;
import lombok.Getter;
import models.Quantity;
import models.ingredients.Ingredient;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Builder
@Getter
public class HotWater implements Beverage {

  public List<String> getIngredients() {
    return Collections.emptyList();
  }

  public Boolean validate(Map<String, Ingredient> globalIngredients) {
    return false;
  }
  public void updateGlobalIngredients(Quantity quantity) {
    System.out.println("HotWater is prepared");
  }

}
