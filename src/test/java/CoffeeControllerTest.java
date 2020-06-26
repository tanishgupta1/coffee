import com.google.gson.Gson;
import com.google.gson.JsonObject;
import controller.CoffeeController;
import exceptions.CoffeeMachineException;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class CoffeeControllerTest {

  CoffeeController coffeeController = CoffeeController.getCoffeeController();
  JsonObject processInput;

  @Before
    public void init()  {
      processInput = getJsonObject("sample_coffee.json");
    }

  @Test
  public void processCoffee_ValidParams_Successfully() throws CoffeeMachineException {
    coffeeController.processBeverages(processInput);
  }

  @Test(expected = CoffeeMachineException.class)
  public void processCoffee_InValidParams_throwsException() throws CoffeeMachineException {

    coffeeController.processBeverages(getJsonObject("sample_coffee_wrong_input.json"));
  }


  private JsonObject getJsonObject(String resourceName) {
    try {
      InputStream in =
              this.getClass().getClassLoader().getResourceAsStream(resourceName);
      return new Gson()
                     .fromJson(IOUtils.toString(in, Charset.defaultCharset()), JsonObject.class);
    } catch (IOException e) {
      return new JsonObject();
    }

  }

}
