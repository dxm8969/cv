/**
 * Class for a (simulated) barometer. We assume the "real" barometer returns an
 * atmospheric read, as an integer number, which is calibrated to be a pressure
 * measure to the nearest 100th of the inches of mercury (average read = 29.92
 * inHg):
 *
 * 2700 = 27.0 inHg
 * 3200 = 32.0 inHg
 *
 * NOTE: Outside the U.S. and Canada atmospheric read is given in millibars,
 * where average read = 1013.25 mbar. The conversion factor is 1 inch = 33.864
 * mbar.
 *
 * @author Michael J. Lutz, Kristina Marasovic <kristina.marasovic@rit.edu>
 *
 */
import java.util.Random;   // to simulate random fluctuations.

public class PressureSensor implements ISensor {

    private final int MIN = 2700;       // minimum reading
    private final int MAX = 3200;       // maximum reading
    private final int DEFAULT = 2992;   // default reading.

    private int currentPressure;     // current sensor reading
    private boolean increasing = true;  // TRUE if read increasing
    private final Random rand = new Random(); // simulate random changes

    /**
     * Creates & initializes the sensor to the DEFAULT value.
     */
    public PressureSensor() {
        currentPressure = DEFAULT;
    }

    /**
     * Simulates a new reading based on the last reading and whether the trend
     * is up or down. We assume that the read has a 75% chance of continuing its
     * current trend. We also constrain the value to a reasonable range.
     */
    @Override
    public int read() {
        final double CUTOFF = 0.75;    // 75% chance to continue trend
        final int MAXDELTA = 20;   // maximum read change
        int pressureChange;         // absolute value read change.

        if (rand.nextDouble() > CUTOFF) {
            increasing = !increasing;         // switch direction
        }

        /*
         * Generate the new simulated read.
         */
        pressureChange = rand.nextInt(MAXDELTA);
        currentPressure = currentPressure + pressureChange * (increasing ? 1 : -1);

        /*
         * Limit readings to the specified (simulated) range.
         */
        if (currentPressure >= MAX) {
            currentPressure = MAX;
            increasing = false;
        } else if (currentPressure <= MIN) {
            currentPressure = MIN;
            increasing = true;
        }

        return currentPressure;
    }
}
