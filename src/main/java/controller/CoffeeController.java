package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.inject.Singleton;
import exceptions.CoffeeMachineException;
import models.Quantity;
import models.beverages.Beverage;
import service.CoffeeService;


import java.util.HashMap;
import java.util.List;


@Singleton
public class CoffeeController {

  public CoffeeService coffeeService = new CoffeeService();
  private static CoffeeController coffeeController = null;
  private CoffeeController() { }

  public static CoffeeController getCoffeeController()
  {
    // To ensure only one instance is created
    if (coffeeController == null)
    {
      coffeeController = new CoffeeController();
    }
    return coffeeController;
  }

  public void processBeverages(JsonObject processInput) throws CoffeeMachineException {
    //TODO: Validation of processInput format can be done here using json schema validation
    if(processInput.has("machine")) {
      try {
        Integer outlets = processInput.getAsJsonObject("machine").getAsJsonObject("outlets").getAsJsonPrimitive("count_n").getAsInt();
        coffeeService.setOutlets(outlets);

        HashMap<String, Object> quantityMap = new Gson().fromJson(processInput.getAsJsonObject("machine").getAsJsonObject("total_items_quantity").toString(), HashMap.class);
        Quantity.getTotalQuantity().parseAndSetIngredients(quantityMap);
        List<Beverage> beverages = coffeeService.parseAndgetBeverages(processInput);
        coffeeService.processBeverageList(beverages);
      }
      catch(Exception e) {
        throw new CoffeeMachineException("Internal Error in Coffee Machine. Please Check after some time");
      }
    }
  }

  // This will add ingredients in the existing ingredients or create new Ingredient
  public  void addIngredients(JsonObject addIngredients) {
    HashMap<String, Object> quantityMap = new Gson().fromJson(addIngredients.getAsJsonObject("total_items_quantity").toString(), HashMap.class);
    Quantity.getTotalQuantity().parseAndSetIngredients(quantityMap);
  }


}
