package JvmObserver;

import java.util.Observable;

/**
 * Created by pengbc on 2018/10/28.
 */
public class WeatherDataTWO extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;


    public void measurementsChanged() {
        //在调用notifyObservers（）之前，要先调用setChanged（）来指示状态已经改变
        setChanged();
        //我们没有调用notifyObservers传送数据对象，表示我们采用的做法
        String[] str = new String[]{String.valueOf(this.temperature),String.valueOf(this.humidity), String.valueOf(this.pressure)};
        notifyObservers(str);
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

}
