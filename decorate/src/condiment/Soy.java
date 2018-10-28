package condiment;

import beverage.Beverage;

/**
 * Created by pengbc on 2018/10/28.
 */
public class Soy extends CondimentDecorator {
    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }
    @Override
    public String getDescription() {
        return beverage.getDescription() +" soy ";
    }

    @Override
    public double cost() {
        return 0.15 + beverage.cost();
    }
}
