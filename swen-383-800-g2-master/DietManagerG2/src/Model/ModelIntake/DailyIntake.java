package Model.ModelIntake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DailyIntake {

    //local variables
    public Map<String, Intake> intakeList = new HashMap<>();

    /**
     * Default constructor
     *
     */
    public DailyIntake() {
    }

    /**
     * Gets the intake data for a specific day
     *
     * @param day
     * @return array with intake data
     */
    public ArrayList<Object> get(String day) {
        // gets intek object for specified day
        Intake intake = getIntake(day);
        ArrayList<Object> info = intake.getData();
        info.add(day);

        return info;
    }

    public void createIntake(String date, String _calories, String _weight) {

        int weight = Integer.parseInt(_weight);
        double calories = Double.parseDouble(_calories);
        
        Intake intake = new Intake(weight, calories);
        addIntake(date, intake);
    }

    /**
     * Returns intake for specified day
     *
     * @param date Requested date
     * @return intake for that date
     */
    public Intake getIntake(String date) {        
        // checks if intake for that day exists
        if (intakeList.get(date) == null) {
            // create new Inteks for that day   
            Intake intake = new Intake();
            // maps new intake date
            addIntake(date, intake);

            return intake;
        }
        // returns intake
        return intakeList.get(date);
    }

    /**
     * Maps an intake to specific day
     *
     * @param data Data in format YYYY/MM/DD
     * @param intake Intake for that day
     */
    public void addIntake(String data, Intake intake) {
        intakeList.put(data, intake);
    }

}
