package Model.ModelFood;

import java.util.ArrayList;
import java.util.Map;

public class Recipe implements Food {

    private String name;

    private double sumCalories = 0;
    private double sumFat = 0;
    private double sumCarbs = 0;
    private double sumProtein = 0;

    // Name of a Food it contains and it's quantity
    private Map<String, Double> recipeList;

    /**
     * Default constructor
     */
    public Recipe() {
    }

    /**
     * Creates an instance of recipe
     * @param name Recipe name
     * @param recipeList List of Food ant it's quantity it holds
     */
    public Recipe(String name, Map<String, Double> recipeList) {
        this.name = name;
        this.recipeList = recipeList;
    }

    @Override
    /**
     * Saves all data about a recipe in arrayList
     */
    public ArrayList getInfo() {
        ArrayList recipeArrayList = new ArrayList<String>();

        recipeArrayList.add(name); // adds name 

        // Adds ingredients and their quantity
        for (Map.Entry<String, Double> entry : recipeList.entrySet()) {
            recipeArrayList.add(entry.getKey());
            recipeArrayList.add(entry.getValue());
        }
        return recipeArrayList;
    }

    /**
     * Returns all ingredients and their quantity 
     * @return map 
     */
    public Map getRecipeList() {
        return recipeList;
    }

    @Override
    /**
     * Returns recipe name
     */
    public String getName() {
        return name;
    }

    /**
     * Set recipe name
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }
}
