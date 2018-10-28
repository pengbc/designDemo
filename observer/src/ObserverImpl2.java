/**
 * Created by pengbc on 2018/10/28.
 */
public class ObserverImpl2 implements Observer {

    @Override
    public void update(float temp, float humidity, float pressure) {
        System.out.println("default is Observer 2");
    }
}
