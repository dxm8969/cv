
/**
 *
 * @author zvonimir
 */
public class SensorFactory {
    
    public static ISensor create(MeasurementUnit unit) {
        switch(unit) {
            case KELVIN:
                return new TemperatureSensor();
            case INHG:
                return  new PressureSensor();
        }
        return null;
    }
       
}
