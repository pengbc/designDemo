package factory;

/**
 * Created by pengbc on 2018/10/31.
 */
public class CheesePizza extends Pizza {
    PizzaIngredientFactory ingredientFactory;

    public CheesePizza(PizzaIngredientFactory ingredientFactory){
        this.ingredientFactory=ingredientFactory;
    }


    @Override
    void prepare() {
        System.out.println("Preparing"+name);
        dough=ingredientFactory.createDough();
        sauce=ingredientFactory.createSauce();
        cheese=ingredientFactory.createCheese();

    }
}
