package beverage;

/**
 * Created by pengbc on 2018/10/28.
 */
public class Decaf extends Beverage {
    public Decaf() {
        description = "Decaf";
    }

    @Override
    public double cost() {
        return 1.07;
    }
}
