import edu.rit.marasovic.swen383.thirdparty.sensor.HumidityReader;


public class HumiditySensor implements ISensor {
      HumidityReader humidityReader = null;
      
      public HumiditySensor() {
        humidityReader = new HumidityReader();
      }
      
      public int read() {
        return humidityReader.get();
      }
}
