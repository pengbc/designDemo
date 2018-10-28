package beverage;

/**
 * Created by pengbc on 2018/10/28.
 */
public class DarkRoast extends Beverage {
    public DarkRoast(){
        description="DarkRoast";
    }

    @Override
    public double cost() {
        return 0.99;
    }
}
