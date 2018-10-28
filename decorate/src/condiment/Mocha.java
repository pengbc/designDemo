package condiment;

import beverage.Beverage;

/**
 * Created by pengbc on 2018/10/28.
 */
public class Mocha extends CondimentDecorator {
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    //返回加入调料后的描述
    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Mocha";
    }

    //返回装饰后的价格
    @Override
    public double cost() {
        return 0.20 + beverage.cost();
    }

}
