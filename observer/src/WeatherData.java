import java.util.ArrayList;

/**
 * Created by pengbc on 2018/10/28.
 */
public class WeatherData {
    private ArrayList<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public int getTemperature(){
        return 0;
    }
    public int getHumidity(){
        return 0;
    }
    public int getPressure(){
        return 0;
    }
    public void measurementsChanged() {
        float temp = getTemperature();
        float humidity = getHumidity();
        float pressure = getPressure();
       // currentConditionsDisplay.update(temp, humidity, pressure);
       // statisticsDisplay.update(temp, humidity, pressure);
       // forecastDisplay.update(temp, humidity, pressure);
    }
}
