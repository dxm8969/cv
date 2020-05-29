import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Scanner;

import static spark.Spark.get;

public class Runner {
    public static void main(String[] args) {
        MySQLDatabase mySQLDatabase = new MySQLDatabase();
        Scanner sc = new Scanner(System.in);
        String[] line = new String[3];
        boolean checkInput = true;

        while (checkInput) {

            System.out.printf("Please enter the database: ");
            line[0] = sc.nextLine();

            System.out.printf("Please enter the username: ");
            line[1] = sc.nextLine();

            System.out.printf("Please enter the password: ");
            line[2] = sc.nextLine();

            mySQLDatabase.setMySQL("jdbc:mysql://localhost:3306/");
            mySQLDatabase.setDatabaseName(line[0]);

            mySQLDatabase.setUsername(line[1]);
            mySQLDatabase.setPassword(line[2]);

            try {
                mySQLDatabase.connect();
                System.out.println("Connected to the database\n\n");
            } catch (DLException ex) {
                ex.log();
                System.exit(0);
            }
            checkInput = false;
        }

        get("/hello", (req, res) -> "Hello World");

        get("/restaurants", (req, res) -> {
            ArrayList<Restaurant> response = new ArrayList<Restaurant>();

            for (int id = 1; id < 12; id++){
                Restaurant restaurant = new Restaurant();
                restaurant.setId(id);
                try {
                    restaurant.fetch(mySQLDatabase);
                    response.add(restaurant);
                } catch (DLException e) {
                    e.log();
                    return e;
                }
            }

            try {
                mySQLDatabase.close();
                System.out.println("Disconnected from database.");
            } catch (DLException e) {
                e.log();
                return e;
            }
            Gson gson = new Gson();
            return gson.toJson(response);
        });
    }
}

