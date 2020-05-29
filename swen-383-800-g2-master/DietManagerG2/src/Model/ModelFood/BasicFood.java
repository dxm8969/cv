package Model.ModelFood;

import java.util.ArrayList;

public class BasicFood implements Food {
    private String name = "dog";
    private double calories = -1;
    private double fat = -1;
    private double carb = -1;
    private double protein = -1;


    /**
     * Default constructor
     */
    public BasicFood(){}

    /**
     * Parameterized constructor
     * @param name basicFood name
     * @param calories number of calories per serving
     * @param fat number of grams of fat
     * @param carb number of grams of carbs
     * @param protein number of grams of protein
     */
    public BasicFood(String name, double calories, double fat, double carb, double protein) {
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.carb = carb;
        this.protein = protein;
    }

    /**
     * Name getter
     * @return basicFood name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Name setter
     * @param name newName
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Calories getter
     * @return number of grams of calories per serving
     */
    public double getCalories() {
        return calories;
    }

    /**
     * Calories setter
     * @param calories new calories
     */
    public void setCalories(double calories) {
        this.calories = calories;
    }

    /**
     * Fat getter
     * @return number of grams of fat per serving
     */
    public double getFat() {
        return fat;
    }

    /**
     * Fat setter
     * @param fat new fat value
     */
    public void setFat(double fat) {
        this.fat = fat;
    }

    /**
     * Carbs getter
     * @return number of grams of carbs per serving
     */
    public double getCarb() {
        return carb;
    }

    /**
     * Carb setter
     * @param carb new carb value
     */
    public void setCarb(double carb) {
        this.carb = carb;
    }

    /**
     * Protein getter
     * @return number of grams of protein per serving
     */
    public double getProtein() {
        return protein;
    }

    /**
     * Protein setter
     * @param protein new protein value
     */
    public void setProtein(double protein) {
        this.protein = protein;
    }

    @Override
    /**
     * Saves all data about basic food and returns it
     * @return arrayList of food info
     */
    public ArrayList getInfo() {

        ArrayList<String> arrayList = new ArrayList<>();
        
         // Gets basic food info
        arrayList.add(name);
        arrayList.add(String.valueOf(calories));
        arrayList.add(String.valueOf(fat));
        arrayList.add(String.valueOf(carb));
        arrayList.add(String.valueOf(protein));
       
        return arrayList;
    }
}
