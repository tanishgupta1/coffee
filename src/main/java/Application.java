
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import controller.CoffeeController;

public class Application {
    public static final String PROCESS_BEVERAGES = "process_beverages";
    public static final String ADD_INGREDIENTS = "add_ingredients";

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Enter either process_beverages or add_ingredients command. Enter exit to terminate the process.");
            String line = br.readLine();
            if ("exit".equalsIgnoreCase(line)) {
                break;
            }

            try {
                if (line.length() > 0) {
                    translateAndRunCommand(line);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void translateAndRunCommand(String action) throws Exception {
        switch(action) {
            case PROCESS_BEVERAGES: {
                System.out.println("Enter json file path to process beverages");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String line = br.readLine();
                CoffeeController.getCoffeeController().processBeverages(getJsonObjectToProcessCoffee(line));
            }
            break;
            case ADD_INGREDIENTS: {
                System.out.println("Enter json file path to add ingredients");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String line = br.readLine();
                CoffeeController.getCoffeeController().addIngredients(getJsonObjectToProcessCoffee(line));
            }
            break;
        }

    }

    public static JsonObject getJsonObjectToProcessCoffee(String filePath) throws Exception {
        BufferedReader jsonReader = new BufferedReader(
                new FileReader(filePath));
        Gson gson = new Gson();
        Object beverageInput = gson.fromJson(jsonReader, Object.class);
        return gson.toJsonTree(beverageInput).getAsJsonObject();
    }

}
