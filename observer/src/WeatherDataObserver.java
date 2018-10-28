import java.util.ArrayList;

/**
 * Created by pengbc on 2018/10/28.
 */
public class WeatherDataObserver implements Subject {
    private ArrayList<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherDataObserver(){
        observers=new ArrayList<Observer>();
    }

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        int i=observers.indexOf(o);
        if(i>=0){
            observers.remove(i);
        }
    }

    @Override
    public void notifyObserver() {
        for(Observer observer:observers){
            observer.update(temperature,humidity,pressure);
        }
    }
    //当从气象站得到更新观测值时，我们通知观察者
    public void measurementsChanged(){
        notifyObserver();
    }

    public void setMeasurements(float temperature,float humidity,float pressure){
        System.out.println("begin is measure ");
        this.temperature=temperature;
        this.humidity=humidity;
        this.pressure=pressure;
        measurementsChanged();
    }
//WeatherData的其他方法

}
