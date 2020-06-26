import exceptions.CoffeeMachineException;
import models.Quantity;
import models.beverages.Beverage;
import models.beverages.BlackTea;
import models.beverages.Coffee;
import models.beverages.GreenTea;
import models.beverages.HotTea;
import org.junit.Before;
import org.junit.Test;
import service.CoffeeService;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CoffeeServiceTest {
  CoffeeService coffeeService = new CoffeeService();
  Beverage hotTea, blackTea, coffee, greenTea;
  List<Beverage> beverages;

  Quantity quantity;
  @Before
  public void init()  {
    quantity = Quantity.getTotalQuantity();
    hotTea = HotTea.getObject(200L, 100L, 10L, 10L, 30L);
    blackTea = BlackTea.getObject(30L, 30L,50L, 30L);
    coffee = Coffee.getObject(100L, 30L, 400L, 50L, 30L);
    greenTea = GreenTea.getObject(100L, 30L, 50L, 30L);
    beverages = Arrays.asList((Beverage)hotTea, (Beverage)blackTea, (Beverage)coffee, (Beverage)greenTea);

    coffeeService.setOutlets(3);
  }

  @Test
  public void processBeveragesAsync_Successfully() throws CoffeeMachineException {
    coffeeService.processBeverageList(beverages);
  }

  @Test
  public void validate_HotTea() {
    Boolean validateHotTea = hotTea.validate(quantity.getIngredients());
    assertEquals(validateHotTea, false);
  }

  @Test
  public void validate_BlackTea() {
    Boolean validateBlackTea = blackTea.validate(quantity.getIngredients());
    assertEquals(validateBlackTea, false);
  }

  @Test
  public void validate_GreenTea() {
    Boolean validateGreenTea = greenTea.validate(quantity.getIngredients());
    assertEquals(validateGreenTea, false);
  }

  @Test
  public void validate_Coffee() {
    Boolean validateCoffee = coffee.validate(quantity.getIngredients());
    assertEquals(validateCoffee, false);
  }

}
