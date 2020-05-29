

import java.awt.Font;
import java.awt.GridLayout;
import java.util.EnumMap;

import javax.swing.*;

/**
 * Swing UI class used for displaying the information from the associated
 * weather station object. This is an extension of JFrame, the outermost
 * container in a Swing application.
 *
 * @author Michael J. Lutz, Kristina Marasovic <kristina.marasovic@rit.edu>
 * @version 2
 */
public class SwingUI extends JFrame implements Observer {

    private final WeatherStation station;

    private final EnumMap<MeasurementUnit, JLabel> labelMap = new EnumMap<>(MeasurementUnit.class);
    private final Font labelFont = new Font(Font.SERIF, Font.PLAIN, 36);

    /**
     * Creates and populates the SwingUI JFrame with panels and labels to show
     * the temperatures.
     *
     * @param station
     */
    public SwingUI(WeatherStation station) {
        super("Weather Station");
        this.station = station;

        /*
         * WeatherStation frame is a grid of 1 row by an indefinite
         * number of columns.
         */
        this.setLayout(new GridLayout(1, 0));

        JPanel panel = null;
        for (MeasurementUnit unit : MeasurementUnit.values()) {
            panel = createPanel(unit);
            this.add(panel);
        }

        /*
         * Set up the frame's default close operation, pack its elements,
         * and make the frame visible.
         */
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Creates and returns a Label with the initial value <code>title</code>.
     *
     * @param title title of the label
     */
    private JLabel createLabel(String title) {
        JLabel label = new JLabel(title);

        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setFont(labelFont);

        return label;
    }

    /**
     * Sets the labels values.
     *
     * @param unit label name reflecting temperature unit
     * @param value value to set
     * @since 1.2
     */
    private void setLabel(MeasurementUnit unit, double value) {
        labelMap.get(unit).setText(String.format("%6.2f", value));
    }

    /**
     * Creates a display panel for a certain measurement unit.
     *
     * @param unit measurement unit.
     */
    private JPanel createPanel(MeasurementUnit unit) {
        JPanel panel = new JPanel(new GridLayout(2, 1));

        //label for the temperature unit name
        JLabel tempLabel = createLabel(" " + unit + " ");
        panel.add(tempLabel);

        //label for the temperature value
        tempLabel = createLabel("");
        panel.add(tempLabel);

        labelMap.put(unit, tempLabel);

        return panel;
    }

    @Override
    public void update() {
        double reading;
        for (MeasurementUnit unit : MeasurementUnit.values()) {
            reading = station.getReading(unit);
            setLabel(unit, reading);
        }

    }
}
