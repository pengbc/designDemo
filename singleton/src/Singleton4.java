/**
 * Created by pengbc on 2018/10/31.
 */
public class Singleton4 {
    //volatile关键词确保，当singleton变量被初始化成Singleton实例时，多个线程正确处理singleton变量
    private volatile static Singleton4 singleton;

    private Singleton4() {
    }

    public static Singleton4 getInstance() {
        if (singleton == null) {
            //只有第一次才彻底执行这里的代码
            synchronized (Singleton4.class) {
                if (singleton == null) {
                    singleton = new Singleton4();
                }
            }
        }
        return singleton;
    }

}
