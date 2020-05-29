package Model.ModelIntake;

import Model.ModelFood.BasicFood;
import Model.ModelFood.Food;
import Model.ModelFood.Recipe;
import UTIL.Foods;

import java.util.ArrayList;
import java.util.Map;

public class Intake {

    // local variables
    ArrayList arr = new ArrayList<Foods>();
    Foods foods = Foods.getFoods();
    Integer weight;
    Double desiredCalories;

    // initializ sum of nutritients
    double sumCalories = 0;
    double sumFat = 0;
    double sumCarbs = 0;
    double sumProtein = 0;

    /**
     * Constructor with parameters
     *
     * @param weight weight of the person
     * @param desiredCalories number of calories the person wants to eat
     */
    public Intake(Integer weight, Double desiredCalories) {
        this.weight = weight;
        this.desiredCalories = desiredCalories;
    }

    /**
     * Default constructor that sets default values
     */
    public Intake() {
        weight = 68;
        desiredCalories = 2000.0;
    }

    /**
     * Gets all the data about the intake
     *
     * @return arrayList with data
     */
    public ArrayList<Object> getData() {

        ArrayList<Object> info = new ArrayList<>();

        // adds data to arrayList
        info.add(Double.toString(this.getDesiredCalories()));
        info.add(Integer.toString(this.getWeight()));
        info.add(Double.toString(this.getCalories()));
        info.add(Double.toString(this.getFat()));
        info.add(Double.toString(this.getProtein()));
        info.add(Double.toString(this.getCarbs()));

        ArrayList<String> foodNames = new ArrayList<>();
        ArrayList<Food> foods = this.getFoods();
        // adds all food namse in intake
        for (Food food : foods) {
            foodNames.add(food.getName());
        }

        info.add(foodNames);

        return info;
    }

    /**
     * Adds a food to intake
     *
     * @param name name of food
     */
    public void addFood(String name) {
        Food newEntry = foods.getFood(name);

        getRecipeData(newEntry, 1.0);

        arr.add(newEntry);

    }// end method

    public void getRecipeData(Food newFood, Double quantity) {

        // adds nutritious values to sums
        if (newFood instanceof BasicFood) {

            BasicFood foodInfo = (BasicFood) newFood;

            sumCalories += (foodInfo.getCalories() * quantity);
            sumCarbs += (foodInfo.getCarb() * quantity);
            sumFat += (foodInfo.getFat() * quantity);
            sumProtein += (foodInfo.getProtein() * quantity);

        } else {

            // Get data of a new food and call this method again
            Recipe newRecipe = (Recipe) newFood;

            // multiply quantity
            Map<String, Double> recipeList = newRecipe.getRecipeList();
            recipeList.replaceAll((k, v) -> v * quantity);

            for (Map.Entry<String, Double> newRecipeFood : recipeList.entrySet()) {

                // gets the food from key 
                Food newEntry = foods.getFood(newRecipeFood.getKey());

                // gets quantity from value 
                Double newQuantity = newRecipeFood.getValue();

                getRecipeData(newEntry, newQuantity); // calling metod for next food
            }
        }
    }

    /**
     * Gets the foods from intake
     *
     * @return foods
     */
    public ArrayList getFoods() {
        return arr;
    }// end method

    /**
     * Gets the the sum of all calories consumed
     *
     * @return sum of all calories consumed
     */
    public double getCalories() {
        return sumCalories;
    }

    public double getFat() {
        return sumFat;
    }

    public double getCarbs() {
        return sumCarbs;
    }

    public double getProtein() {
        return sumProtein;
    }

    /**
     * Getter for weight
     *
     * @return weight
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * Getter for calories
     *
     * @return desiredCalories
     */
    public Double getDesiredCalories() {
        return desiredCalories;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setDesiredCalories(Double desiredCalories) {
        this.desiredCalories = desiredCalories;
    }
}// end of class
