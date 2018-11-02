package factory;

/**
 * Created by pengbc on 2018/10/31.
 */
public class NYPizzaStore extends PizzaStore {
    protected Pizza createPizza(String item) {
        Pizza pizza = null;
        PizzaIngredientFactory ingredientFactory;
        if (item.equals("cheese")) {
            ingredientFactory = new NYPizzaIngredientFactory();
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("New ……");
        } else if (item.equals("veggie")) {
            //……
        }
        return pizza;
    }
}

