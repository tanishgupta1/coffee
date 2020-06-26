package service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import exceptions.CoffeeMachineException;
import lombok.Getter;
import lombok.Setter;
import models.Quantity;
import models.beverages.Beverage;
import models.beverages.BlackTea;
import models.beverages.Coffee;
import models.beverages.GreenTea;
import models.beverages.HotTea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static common.Utils.allOf;
import static common.Utils.getLongValue;

@Getter
@Setter
public class CoffeeService {
  public Integer outlets;

  public List<Beverage> parseAndgetBeverages(JsonObject processInput) {
    //SET BEVERAGES
    List<Beverage> beverages = new ArrayList<>();

    HashMap<String, Object> beverageMap = new Gson().fromJson(processInput.getAsJsonObject("machine").getAsJsonObject("beverages").toString(), HashMap.class);
    beverageMap.forEach((k,v) -> {

      HashMap<String, Object> beverageObject = new Gson().fromJson(v.toString(), HashMap.class);

      if(k.equals("hot_tea")) {
        beverages.add(HotTea.getObject(getLongValue(beverageObject.get("hot_water")),
                getLongValue(beverageObject.get("ginger_syrup")),
                getLongValue(beverageObject.get("sugar_syrup")),
                getLongValue(beverageObject.get("tea_leaves_syrup")),
                getLongValue(beverageObject.get("hot_milk"))));

      } else if(k.equals("hot_coffee")) {
        beverages.add(Coffee.getObject(getLongValue(beverageObject.get("hot_water")),
                getLongValue(beverageObject.get("ginger_syrup")),
                getLongValue(beverageObject.get("sugar_syrup")),
                getLongValue(beverageObject.get("tea_leaves_syrup")),
                getLongValue(beverageObject.get("hot_milk"))));

      } else if (k.equals("black_tea")) {
        beverages.add(BlackTea.getObject(getLongValue(beverageObject.get("hot_water")),
                getLongValue(beverageObject.get("ginger_syrup")),
                getLongValue(beverageObject.get("sugar_syrup")),
                getLongValue(beverageObject.get("tea_leaves_syrup"))));

      } else if(k.equals("green_tea")) {
        beverages.add(GreenTea.getObject(getLongValue(beverageObject.get("hot_water")),
                getLongValue(beverageObject.get("ginger_syrup")),
                getLongValue(beverageObject.get("sugar_syrup")),
                getLongValue(beverageObject.get("green_mixture"))));

      }
    });
    return beverages;
  }

  // It will process beverage list batchwise through N outlets simultaneously
  public void processBeverageList(List<Beverage> beverages) throws CoffeeMachineException  {
    int beverageSize = beverages.size();
    ArrayList<Beverage> processList;

    for(int i = 0; i < beverageSize;i = i + outlets) {
      processList = new ArrayList<>();
      for(int j = i; j < i+outlets;j++) {
        if( j < beverageSize )
          processList.add(beverages.get(j));
      }
      processBeveragesAsync(processList)
              .toCompletableFuture().join();

    }
  }

  //IMP: This will process beverages from N outlets parallely
  private CompletionStage<Void> processBeveragesAsync(List<Beverage> beverages) throws CoffeeMachineException {
    Quantity quantity = Quantity.getTotalQuantity();
    List<CompletionStage<Void>> processBeverages = new ArrayList<>();

    beverages.forEach(beverage -> {
      processBeverages.add(CompletableFuture.supplyAsync(() -> {
        quantity.validateAndUpdateTotalQuantity(beverage);
        return null;
      }));
    });

    return allOf(processBeverages);
  }



}
