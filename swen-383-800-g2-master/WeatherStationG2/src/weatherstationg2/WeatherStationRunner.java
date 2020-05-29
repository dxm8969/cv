package weatherstationg2;

import java.util.EnumMap;


public class WeatherStationRunner {

    public static void main(String[] args) {
       EnumMap<MeasurementUnit, ISensor> sensorMap = new EnumMap<>(MeasurementUnit.class);
       
       sensorMap.put(MeasurementUnit.KELVIN, SensorFactory.create(MeasurementUnit.KELVIN));
       sensorMap.put(MeasurementUnit.INHG, SensorFactory.create(MeasurementUnit.INHG));
        
       WeatherStation ws = new WeatherStation(sensorMap);
        
       SwingUI swingUi = new SwingUI(ws);
       TextUI textUi = new TextUI(ws);
       ws.attach(swingUi);
       ws.attach(textUi);

       Thread thread = new Thread(ws);
       thread.start();
    }

}
