package condiment;

import beverage.Beverage;

/**
 * Created by pengbc on 2018/10/28.
 */
public abstract class CondimentDecorator extends Beverage {
    //所有的调料装饰者都必须重新实现getDescription方法，稍后我们会解释
    @Override
    public abstract String getDescription();

}
