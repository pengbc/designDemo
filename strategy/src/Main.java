import quack.QuackBehavior;
import quack.SqueakQuack;
import fly.FlyBehavior;
import fly.FlyWithWings;

/**
 * Created by pengbc on 2018/10/28.
 */
public class Main {

    public static void main(String[] args) {
        //MallarDuck
        MallardDuck mallardDuck = new MallardDuck();
        mallardDuck.setFlyBehavior(new FlyWithWings());
        mallardDuck.setQuackBehavior(new SqueakQuack());
        mallardDuck.display();
        FlyBehavior flyBehavior = mallardDuck.getFlyBehavior();
        QuackBehavior quackBehavior = mallardDuck.getQuackBehavior();
        flyBehavior.fly();
        quackBehavior.quack();
    }
}
