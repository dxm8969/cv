

/**
 * Enum for sensor units. The Enum comes with a get method that converts a
 * reading to the appropriate unit.
 *
 * @author Kristina Marasovic <kristina.marasovic@rit.edu>
 */
public enum MeasurementUnit {

    KELVIN (1, 0),
    CELSIUS (1, -27315),
    FAHRENHEIT (1.8, -45967),
    INHG (1, 0),
    MBAR (33.8639, 0),
    HUM (100, 0);

    private final double cf1; //conversion factor
    private final double cf2;

    MeasurementUnit(double cf1, double cf2) {
        this.cf1 = cf1;
        this.cf2 = cf2;
    }

    public double get(int reading) {
        return (cf1*reading + cf2)/100.0;
    }
}
