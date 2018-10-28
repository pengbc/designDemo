package quack;

/**
 * Created by pengbc on 2018/10/28.
 */
public class MuteQuack implements QuackBehavior {
    public void quack() {
        //什么都不做，不会叫
        System.out.println("什么都不做，不会叫");
    }
}
