package weatherstationg2;

/**
 * The TextUI class is an observer of the WeatherStation that, when it receives
 * an update message, prints the temperature in Celsius and Kelvin.
 *
 * The main method for the text based monitoring application is here as well.
 *
 * @author Michael J. Lutz, Kristina Marasovic <kristina.marasovic@rit.edu>
 */
public class TextUI implements Observer {

    private final WeatherStation station;

    /*
     * Remember the station we're attached to and add ourselves as an observer.
     */
    public TextUI(WeatherStation station) {
        this.station = station;
    }

    /**
     * Called when WeatherStation collects all the readings. The Subject's
     * notifyObservers() method calls update() on all observers.
     */
    @Override
    public void update() {
        double reading;
        for (MeasurementUnit unit : MeasurementUnit.values()) {
            reading = station.getReading(unit);
            System.out.printf("Reading in %10s is %6.2f %n", unit, reading);
        }
        System.out.println("-------------------------------------------------");

    }

}
