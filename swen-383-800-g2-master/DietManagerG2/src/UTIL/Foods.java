package UTIL;

import Model.ModelFood.Food;

import java.util.ArrayList;

public class Foods  {

    //extends ArrayList<Food>
    
    // array list of all Model.ModelFood.BasicFood and Recipes from the database

    ArrayList<Model.ModelFood.Food> arrayListFoods = new ArrayList<>();

    // single instance of this class
    private static Foods foodList;

    /**
     * Default constructor 
     */
    private Foods(){
     // System.out.println("Creates an empty food list");
    }
    
    /**
     * Exposes the unique instance of UTIL.Foods
     * @return the unique instance of UTIL.Foods
     */
    public static synchronized Foods getFoods(){
        if(foodList == null){
            foodList = new Foods();
        }
        return foodList;
    }
    
    /**
     * Gets specific food based on its name
     * @param name name of the food
     * @return food
     */
    public Food getFood(String name){

        // gets specific food based on its name
        for(Food food: arrayListFoods){

            // compare food name with name
            if(food.getName().equals(name)){
                return food; // return food if found
            }
        }
        return null;// no food found
    }
    
    /**
     * Gets all of the food items names
     * @return ArrayList of foods names
     */
    public ArrayList getAllFoods() {
        
        ArrayList<String> foodNames = new ArrayList<>();
        
        // gets name for eash food
        for(Food food: arrayListFoods){
            foodNames.add(food.getName());
        }
        
        // returns foods names
        return foodNames; 
    }
    
    /**
     * Adds new food to the list 
     * @param food  new food
     */
    public void addFood(Food food){
        arrayListFoods.add(food);
    
    }
    
    public boolean hasFood(String name){
     for(Food food: arrayListFoods){
            // compare food name with name
            if(food.getName().equals(name)){
                return true; // return food if found
            }
        }
     return false; // there is no such food in the list
    }
}
