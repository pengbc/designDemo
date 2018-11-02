/**
 * Created by pengbc on 2018/10/31.
 */
public class Singleton1 {
    private static Singleton1 singleton;
    private  Singleton1(){}
    public static Singleton1 getInstance() {
        if (singleton == null) {
            singleton = new Singleton1();
        }
        return singleton;
    }

}
