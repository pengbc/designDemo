import beverage.Beverage;
import beverage.Espresso;
import condiment.Mocha;
import condiment.Whip;

/**
 * Created by pengbc on 2018/10/28.
 */
public class StarbuzzCoffee {
    public static  void main(String arg[]){
        Beverage beverage=new Espresso();
        beverage=new Mocha(beverage);
        beverage=new Whip(beverage);

        System.out.println(beverage.getDescription()+"$"+beverage.cost());
    }

}
