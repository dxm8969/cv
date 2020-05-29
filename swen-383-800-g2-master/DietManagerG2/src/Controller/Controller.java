package Controller;

import IOE.ResourceHandler;
import Model.ModelIntake.DailyIntake;
import UTIL.Foods;

import java.util.ArrayList;
import java.util.Map;

public class Controller {

    Foods foods;
    ResourceHandler resourceHandler;
    DailyIntake dailyIntake;

    public Controller() {
        foods = Foods.getFoods();
        resourceHandler = new ResourceHandler();
        dailyIntake = new DailyIntake();

        loadIntakes();

        
    }

    public void loadIntakes() {

        //  public void addToIntake(String day, String name)
        ArrayList<Object> arrayIntakeData = resourceHandler.readIntake();

        for (Object newIntake : arrayIntakeData) {
            ArrayList<Object> newIntakeArray = (ArrayList) newIntake;

            String date = newIntakeArray.get(0).toString();
            String calories = newIntakeArray.get(2).toString();
            String weight = newIntakeArray.get(1).toString();

            // creates a intake for each day
            dailyIntake.createIntake(date, calories, weight);

            Map<String, Double> foodMap = (Map) newIntakeArray.get(3);

            for (String foodName : foodMap.keySet()) {
                dailyIntake.getIntake(date).addFood(foodName);
            }
          //  System.out.println(dailyIntake.getIntake(date));
        }
    }

    /**
     * Gets info about a Model.ModelFood.BasicFood or Model.ModelFood.Recipe
     * from UTIL.Foods
     */
    public ArrayList getFoodInfo(String name) {
        return foods.getFood(name).getInfo();
    }

    /**
     * Adds a Model.ModelFood.BasicFood or Model.ModelFood.Recipe to UTIL.Foods
     * using UTIL.FoodFactory
     *
     * @param type the type of food
     * @param data food data
     */
    public void addFood(char type, String data) {

        // sends new food to resourceHandler
        String fullData = String.format("%s, %s", type, data);
        resourceHandler.saveData(fullData);

        //   foods.addFood(type, data);
    }

    public boolean existsFood(String name) {
        return foods.hasFood(name);
    }

    /**
     * Adds a Model.ModelFood.BasicFood or Model.ModelFood.Recipe to the
     * Model.ModelIntake.DailyIntake
     * @param day which day
     * @param name name of food
     */
    public void addToIntake(String day, String name) {
        // Check if the food already exists

        double count = 1;

        // adds a food to intake
        dailyIntake.getIntake(day).addFood(name);

    }

    public void saveIntake(String day) {

        // get all information about intakes for that day
        ArrayList<Object> arrayIntakeData = dailyIntake.getIntake(day).getData();

        // save weight and desiered calories
        // desiered calories
        resourceHandler.saveIntake(day, 'c', "", arrayIntakeData.get(0).toString());
        //   public void saveIntake(String day, char type, String foodName, String count) {
        // desiered weight 
        resourceHandler.saveIntake(day, 'w', "", arrayIntakeData.get(1).toString());

        ArrayList<String> foodNames = (ArrayList) arrayIntakeData.get(6);

        // yyyy,mm,dd,f,name, count   
        for (String foodName : foodNames) {
            resourceHandler.saveIntake(day, 'f', foodName, "1");
        }

    }

    /**
     * Gets intake info for a desired day from Model.ModelIntake.DailyIntake
     *
     * @param day
     */
    public ArrayList<Object> getIntakeInfo(String day) {

        return dailyIntake.get(day);
    }

    /**
     * Gets all of the food item names from Foods
     *
     * @return ArrayList of food names
     */
    public ArrayList getAllFoods() {
        return foods.getAllFoods();
    }
}
