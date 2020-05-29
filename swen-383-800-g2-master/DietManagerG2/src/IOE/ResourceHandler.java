package IOE;

import UTIL.FoodFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceHandler {

    public ResourceHandler() {
        this.getData();  // gets all data from the csv file
    }

    /**
     * Gets the data from the csv file Reads all Foods on load and calls
     * sendData method
     */
    private void getData() {

        File file = new File("resources/recipefoods.csv");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String record;
            // reads file line by line
            while ((record = bufferedReader.readLine()) != null) {
                // Reads the data proparly
                this.sendData(record);
            }
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            System.out.println("Exception: File is not found");
        } catch (IOException e) {
            System.out.println("Exception: Unable to read the file");
        }

        //   System.out.println("Resource handler getting data from the database");
    }

    /**
     * Sends that data to the UTIL.FoodFactory to create the BasicFoods or
     * Recipes
     *
     * @param data Comma separated string with data for the new object
     * @return new Food object
     */
    private void sendData(String data) {

        List<String> list = Arrays.asList(data.split("\\s*,\\s*"));
        ArrayList arrayList = new ArrayList<>(list);

        // creates either basic food or recipe
        FoodFactory.create(arrayList);
    }

    /**
     * Saves a new entry to the file/ new food
     *
     * @param data data for the new food item
     */
    public void saveData(String data) {

        // sends the data to factory to create new object
        this.sendData(data);

        // saves new food to recipefoods file
        File file = new File("resources/recipefoods.csv");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            bufferedWriter.write(String.format("\n%s", data)); // saves data to the file
            bufferedWriter.flush();

        } catch (IOException e) {
            //     e.printStackTrace();
            System.out.println("Exception in saving new food");
        }
    }

    /**
     * Saves a new entry into log file for specific day
     *
     * @param day date in format YYYY/MM/DD
     * @param foodName food name
     * @param count quantity
     */
    public void saveIntake(String day, char type, String foodName, String count) {

        String[] date = day.split("/");

        // formats a string for log file 
        String data = null;
        if (type == 'f') {
            data = String.format("%s,%s,%s,%s,%s,%s\n", date[0], date[1], date[2], type, foodName, count);
        } else { // for weight and calories
            data = String.format("%s,%s,%s,%s,%s\n", date[0], date[1], date[2], type, count);
        }

        File file = new File("resources/log.csv");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            bufferedWriter.write(data); // saves data to the file
            System.out.println(data);
            bufferedWriter.flush();

        } catch (IOException e) {
            System.out.println("Exception in writing to log");
            //  e.printStackTrace();
        }
    }

    /**
     * Reads the data from log file on load Sorts the data by days
     *
     * @param date
     */
    public ArrayList readIntake() {

        ArrayList<String> arrayIntakeData = new ArrayList<>();
        // read log.csv
        File file = new File("resources/log.csv");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String record;
            // reads file line by line
            while ((record = bufferedReader.readLine()) != null) {
                // Reads the data proparly

                arrayIntakeData.add(record);
            }
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            System.out.println("Exception: File is not found");
        } catch (IOException e) {
            System.out.println("Exception: Unable to read the log file");
        }

        // send data to controler to save into intakes 
        return orderLog(arrayIntakeData);
    }

    /**
     * Orders log entries and groups them by day
     *
     * @param unorderedLog
     * @return
     */
    public ArrayList orderLog(ArrayList<String> unorderedLog) {

        // orders the previous list by day 
        Collections.sort(unorderedLog, String.CASE_INSENSITIVE_ORDER);

        ArrayList<Object> orderedLog = new ArrayList();

        // split by dates and store in new Array
        String[] data = unorderedLog.get(0).split(",");

        //    String date = data[0]+data[1]+data[2];
        String date = String.format("%s,%s,%s", data[0], data[1], data[2]);

        ArrayList<String> newIntakeData = new ArrayList<>();
        // System.out.println(date);

        // for each element in the list place in array
        for (int i = 0; i < unorderedLog.size(); i++) {

            if (unorderedLog.get(i).contains(date)) {
                newIntakeData.add(unorderedLog.get(i));
            } else {

                // saves that intake
                orderedLog.add(newIntakeData);
      
                // sets data for next data
                newIntakeData = new ArrayList<>();
                data = unorderedLog.get(i).split(",");
                date = String.format("%s,%s,%s", data[0], data[1], data[2]);
                newIntakeData.add(unorderedLog.get(i));

            }
        }

        orderedLog.add(newIntakeData);

        // NO LOOP 
//        for (Object object : orderedLog) {
//            System.out.println(object.toString());
//        }

        //System.out.println(newIntakeData.toString());
        // orderedLog Contains grouped days in lists
        for (int i = 0; i < orderedLog.size(); i++) {

            ArrayList<Object> newList = new ArrayList();
            // orders data in each subArray
            newIntakeData = (ArrayList) orderedLog.get(i);

            String weight = null;
            String calories = null;
            date = null;
            Map<String, Double> foods = new HashMap<>();

            for (Object intake : newIntakeData) {

                data = intake.toString().split(",");
                date = String.format("%s/%s/%s", data[0], data[1], data[2]);
                // weight 
                if (data[3].equals("w")) {
                    weight = data[4];

                    // calories
                } else if (data[3].equals("c")) {
                    calories = data[4];

                } else {
                    foods.put(data[4], Double.parseDouble(data[5]));
                    // map of fName and quantity
                }
            }

            newList.add(date);
            newList.add(weight);
            newList.add(calories);
            newList.add(foods);

        //    System.out.println(newList);
            // replaces the array
            orderedLog.set(i, newList);
            newList = new ArrayList();
            // finalArray.add(intakeData);
        }



        // saves weight at first position
        // System.exit(0);
        return orderedLog;
    }
}
