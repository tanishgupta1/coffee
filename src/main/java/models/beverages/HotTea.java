package models.beverages;

import lombok.Builder;
import lombok.Getter;
import models.Quantity;
import models.ingredients.GingerSyrup;
import models.ingredients.HotMilkIngredient;
import models.ingredients.HotWaterIngredient;
import models.ingredients.Ingredient;
import models.ingredients.SugarSyrup;
import models.ingredients.TeaLeavesSyrup;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static common.Constants.HOT_WATER;

@Builder
@Getter
public class HotTea implements Beverage {
  HotWaterIngredient hotWater;
  GingerSyrup gingerSyrup;
  SugarSyrup sugarSyrup;
  TeaLeavesSyrup teaLeavesSyrup;
  HotMilkIngredient hotMilkIngredient;

  public List<String> getIngredients() {
    return Collections.emptyList();
  }

  public static HotTea getObject(Long hotWaterIngredient, Long gingerSyrup, Long sugarSyrup, Long teaLeavesSyrup, Long hotMilk) {
    return HotTea.builder().hotWater(HotWaterIngredient.builder().quantity(hotWaterIngredient).build())
                   .gingerSyrup(GingerSyrup.builder().quantity(gingerSyrup).build())
                   .sugarSyrup(SugarSyrup.builder().quantity(sugarSyrup).build())
                   .teaLeavesSyrup(TeaLeavesSyrup.builder().quantity(teaLeavesSyrup).build())
                   .hotMilkIngredient(HotMilkIngredient.builder().quantity(hotMilk).build())
                   .build();
  }

  public Boolean validate(Map<String, Ingredient> globalIngredients) {
    // Handle null values for
    if(!globalIngredients.containsKey(HOT_WATER.getName())) {
      System.out.println("Hot Tea cannot be prepared because hot water is not available");
      return false;
    } else if(!globalIngredients.containsKey("ginger_syrup")) {
      System.out.println("Hot Tea cannot be prepared because ginger_syrup is not available");
      return false;
    } else if(!globalIngredients.containsKey("sugar_syrup")) {
      System.out.println("Hot Tea cannot be prepared because sugar_syrup is not available");
      return false;
    } else if(!globalIngredients.containsKey("tea_leaves_syrup")) {
      System.out.println("Hot Tea cannot be prepared because tea_leaves_syrup is not available");
      return false;
    } else if(!globalIngredients.containsKey("hot_milk")) {
      System.out.println("Hot Tea cannot be prepared because hot_milk is not available");
      return false;
    }

    Long hotWaterRemaining = globalIngredients.get("hot_water").getQuantity() - this.getHotWater().getQuantity();
    Long gingerSyrupRemaining =  globalIngredients.get("ginger_syrup").getQuantity() - this.getGingerSyrup().getQuantity();
    Long sugarSyrupRemaining = globalIngredients.get("sugar_syrup").getQuantity() - this.getSugarSyrup().getQuantity();
    Long teaLeavesSyrupRemaining = globalIngredients.get("tea_leaves_syrup").getQuantity() - this.getTeaLeavesSyrup().getQuantity();
    Long hotMilkRemaining = globalIngredients.get("hot_milk").getQuantity() - this.getHotMilkIngredient().getQuantity();

    if (hotWaterRemaining < 0) {
      System.out.println("Hot Tea cannot be prepared because item hot_water is 0");
      return false;
    } else if (gingerSyrupRemaining < 0) {
      System.out.println("Hot Tea cannot be prepared because item ginger_syrup is 0");
      return false;
    } else if (sugarSyrupRemaining < 0) {
      System.out.println("Hot Tea cannot be prepared because item sugar_syrup is 0");
      return false;
    } else if (teaLeavesSyrupRemaining < 0) {
      System.out.println("Hot Tea cannot be prepared because item tea_leaves_syrup is 0");
      return false;
    } else if (hotMilkRemaining < 0) {
      System.out.println("Hot Tea cannot be prepared because item hot_milk is 0");
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

      globalIngredients.get("hot_milk").setQuantity(
              globalIngredients.get("hot_milk").getQuantity() - this.getHotMilkIngredient().getQuantity());


    System.out.println("Hot Tea is prepared");
  }

}
