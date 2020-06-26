package models.beverages;

import lombok.Builder;
import lombok.Getter;
import models.Quantity;
import models.ingredients.GingerSyrup;
import models.ingredients.HotWaterIngredient;
import models.ingredients.Ingredient;
import models.ingredients.SugarSyrup;
import models.ingredients.TeaLeavesSyrup;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Builder
@Getter
public class BlackTea implements Beverage {
  HotWaterIngredient hotWater;
  GingerSyrup gingerSyrup;
  SugarSyrup sugarSyrup;
  TeaLeavesSyrup teaLeavesSyrup;

  public List<String> getIngredients() { return Collections.emptyList(); }

  public static BlackTea getObject(Long hotWaterIngredient, Long gingerSyrup, Long sugarSyrup, Long teaLeavesSyrup) {
    return BlackTea.builder().hotWater(HotWaterIngredient.builder().quantity(hotWaterIngredient).build())
            .gingerSyrup(GingerSyrup.builder().quantity(gingerSyrup).build())
            .sugarSyrup(SugarSyrup.builder().quantity(sugarSyrup).build())
            .teaLeavesSyrup(TeaLeavesSyrup.builder().quantity(teaLeavesSyrup).build())
            .build();
  }

  public Boolean validate(Map<String, Ingredient> globalIngredients) {
    // Handle null values for
    if(!globalIngredients.containsKey("hot_water")) {
      System.out.println("Black Tea cannot be prepared because hot water is not available");
      return false;
    } else if(!globalIngredients.containsKey("ginger_syrup")) {
      System.out.println("Black Tea cannot be prepared because ginger_syrup is not available");
      return false;
    } else if(!globalIngredients.containsKey("sugar_syrup")) {
      System.out.println("Black Tea cannot be prepared because sugar_syrup is not available");
      return false;
    } else if(!globalIngredients.containsKey("tea_leaves_syrup")) {
      System.out.println("Black Tea cannot be prepared because tea_leaves_syrup is not available");
      return false;
    }

    Long hotWaterRemaining = globalIngredients.get("hot_water").getQuantity() - this.getHotWater().getQuantity();
    Long gingerSyrupRemaining =  globalIngredients.get("ginger_syrup").getQuantity() - this.getGingerSyrup().getQuantity();
    Long sugarSyrupRemaining = globalIngredients.get("sugar_syrup").getQuantity() - this.getSugarSyrup().getQuantity();
    Long teaLeavesSyrupRemaining = globalIngredients.get("tea_leaves_syrup").getQuantity() - this.getTeaLeavesSyrup().getQuantity();

    if (hotWaterRemaining < 0) {
      System.out.println("Black Tea cannot be prepared because item hot_water is 0");
      return false;
    } else if (gingerSyrupRemaining < 0) {
      System.out.println("Black Tea cannot be prepared because item ginger_syrup is 0");
      return false;
    } else if (sugarSyrupRemaining < 0) {
      System.out.println("Black Tea cannot be prepared because item sugar_syrup is 0");
      return false;
    } else if (teaLeavesSyrupRemaining < 0) {
      System.out.println("Black Tea cannot be prepared because item tea_leaves_syrup is 0");
      return false;
    }

    return true;
  }

  public void updateGlobalIngredients(Quantity quantity) {
    Map<String, Ingredient> globalIngredients = quantity.getIngredients();
    globalIngredients.get("hot_water").setQuantity(
            globalIngredients.get("hot_water").getQuantity() - this.getHotWater().getQuantity());

    globalIngredients.get("ginger_syrup").setQuantity(
            globalIngredients.get("ginger_syrup").getQuantity() - this.getGingerSyrup().getQuantity());

    globalIngredients.get("sugar_syrup").setQuantity(
            globalIngredients.get("sugar_syrup").getQuantity() - this.getSugarSyrup().getQuantity());

    globalIngredients.get("tea_leaves_syrup").setQuantity(
            globalIngredients.get("tea_leaves_syrup").getQuantity() - this.getTeaLeavesSyrup().getQuantity());

    System.out.println("Black Tea is prepared");
  }


}
