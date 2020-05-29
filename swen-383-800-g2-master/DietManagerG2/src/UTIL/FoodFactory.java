package UTIL;

import Model.ModelFood.BasicFood;
import Model.ModelFood.Food;
import Model.ModelFood.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FoodFactory {

    private static Foods foodList = Foods.getFoods();
    
    
    /**
     * Creates a recipe or a basic food
     * @param data food data
     * @return basic food or recipe
     */
    public static Food create(ArrayList data){

        Food newFood;

        // gets the first value in a format b or r
        char type = data.get(0).toString().charAt(0);

        switch (type){
            case 'b':
                
                // creates new basic food
                String name = (String) data.get(1);
                double calories =  Double.parseDouble(data.get(2).toString());
                double fat =  Double.parseDouble(data.get(3).toString());
                double carb =  Double.parseDouble(data.get(4).toString());
                double protein =  Double.parseDouble(data.get(5).toString());

                newFood = new BasicFood(name,calories,fat,carb, protein); // add attributes

                foodList.addFood(newFood); // adds the basic food to the list
                return newFood; // returns new food just in case
                
            case 'r':

                // creates new Recipe
                Map<String, Double> map = new HashMap<>();

                // gets name
                String recipeName = data.get(1).toString();

                String foodName = null;
                double numberOf;

                // first two are type and name
                for(int i = 2; i < data.size(); i++){
                    if( i%2 == 0){
                        foodName = data.get(i).toString();
                    } else{
                        // for even numbers is a value and adds to list
                        numberOf = Double.parseDouble( data.get(i).toString());
                        map.put(foodName,numberOf);
                    }
                }
                newFood = new Recipe(recipeName, map);

                foodList.addFood(newFood); // adds new recipe to the list
                return newFood; // returns new recipe
                
            default:
                return null;
        } // end switch
    } // end create
} // end FoodFactory
