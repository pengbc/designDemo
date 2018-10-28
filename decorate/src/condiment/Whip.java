package condiment;

import beverage.Beverage;

/**
 * Created by pengbc on 2018/10/28.
 */
public class Whip extends CondimentDecorator {
    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " whip ";
    }

    @Override
    public double cost() {
        return .01 + beverage.cost();
    }
}
