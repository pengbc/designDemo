package beverage;

/**
 * Created by pengbc on 2018/10/28.
 */
public abstract class Beverage {
    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();

}
