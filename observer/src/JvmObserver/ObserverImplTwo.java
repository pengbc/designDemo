package JvmObserver;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by pengbc on 2018/10/28.
 */
public class ObserverImplTwo implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("default is Observer two ,Observable"+o+",Object param:"+arg);
    }
}
