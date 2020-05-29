
import java.util.EnumMap;

/**
 * Class for a simple computer based weather station that samples data every
 * second. The station is attached to multiple sensors in this regard.
 * <p>
 * This class implements Runnable so that it can be embedded in a Thread which
 * runs the periodic sensing.
 * <p>
 * The class also extends Observable so that it can notify registered objects
 * whenever its state changes. Convenience functions are provided to store and
 * access different readings (Kelvin, Celsius, inMg, etc.)
 *
 * @author Michael J. Lutz, Kristina Marasovic <kristina.marasovic@rit.edu>
 * @version 3
 */
public class WeatherStation extends Subject implements Runnable {
    
    private EnumMap<MeasurementUnit, ISensor> sensorMap = null;
    private EnumMap<MeasurementUnit, Double> readingMap = new EnumMap<>(MeasurementUnit.class);
    /**
     * Thread sleep duration (ms)
     */
    private final long PERIOD = 1000;

    /**
     * Creates and holds the {@link ISensor} objects.
     */
    public WeatherStation(EnumMap<MeasurementUnit, ISensor> _sensorMap) {
        
        // sensors injected with constructor
        sensorMap = _sensorMap;
       
    }

    /**
     * This method is called by the enclosing Thread object when started.
     * Repeatedly sleeps a second, acquires the current temperature from its
     * tempSensor, and notifies registered Observers of the change.
     */
    public void run() {
        while (true) {
            this.getSensorReadings();
            notifyObservers(); //waits for all observers to finish with their update method

            try {
                Thread.sleep(PERIOD);
            } catch (Exception e) {
            }
        }
    }

    /**
     * Returns the reading in the unit specified. See {@link Reading.Unit}
     *
     * @param unit measure unit
     * @return current reading converted to the specified unit
     * @since 3
     */
    public double getReading(MeasurementUnit unit) {
        return readingMap.get(unit);
    }

    /**
     * Reads the measures from all sensors and stores them in an EnumMap.
     */
    private void getSensorReadings() {
        ISensor sensor = null;
        int reading = 0;
        for (MeasurementUnit unit : MeasurementUnit.values()) {
            switch (unit) {
                case KELVIN:
                    sensor = sensorMap.get(MeasurementUnit.KELVIN);
                    reading = sensor.read();
                    break;
                case INHG:
                    sensor = sensorMap.get(MeasurementUnit.INHG);
                    reading = sensor.read();
                    break;
            }
            /*
             * Associates the specified value with the specified key in this
             * map. If the map previously contained a mapping for this key, the
             * old value is replaced.
             */
            readingMap.put(unit, unit.get(reading));
        }
    }

}
