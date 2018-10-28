package beverage;

/**
 * 首先，让Espresso扩展自Beverage类，因为Espresso是一种饮料。
 * Created by pengbc on 2018/10/28.
 */
public class Espresso extends Beverage {
    public Espresso() {
        description = "Espresso";
    }

    //最后，需要计算Espresso的价钱，现在不需要管调料的价钱，直接把Espresso的价格返回即可
    @Override
    public double cost() {
        return 1.9;
    }
}
