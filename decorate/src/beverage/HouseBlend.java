package beverage;

/**
 * Created by pengbc on 2018/10/28.
 */
public class HouseBlend extends Beverage {

    public HouseBlend(){
        description="House Blend Coffee";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
