package fly;

/**
 * Created by pengbc on 2018/10/28.
 */
public class FlyNoWay implements FlyBehavior {
    public void fly() {
        //什么都不做，不会飞
        System.out.println("什么都不做，不会飞");
    }
}
