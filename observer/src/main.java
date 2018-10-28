import JvmObserver.ObserverImplOne;
import JvmObserver.ObserverImplTwo;
import JvmObserver.WeatherDataTWO;

/**
 * Created by pengbc on 2018/10/28.
 */
public class main {
    public static void main(String[] args) {
//        WeatherDataObserver weatherDataObserver = new WeatherDataObserver();
//        weatherDataObserver.registerObserver(new ObserverImpl1());
//        weatherDataObserver.setMeasurements(1,2,3);

        WeatherDataTWO weatherDataTWO = new WeatherDataTWO();
        weatherDataTWO.addObserver(new ObserverImplOne());
        weatherDataTWO.addObserver(new ObserverImplTwo());
        weatherDataTWO.setMeasurements(1,2,3);
    }
}
