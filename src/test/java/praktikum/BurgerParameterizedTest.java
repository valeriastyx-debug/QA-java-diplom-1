package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {

    private final float bunPrice;
    private final float ingredientPrice;
    private final float expectedPrice;

    public BurgerParameterizedTest(float bunPrice,
                                   float ingredientPrice,
                                   float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Object[][] getPriceData() {
        return new Object[][]{
                {100.0f, 50.0f, 250.0f},
                {200.0f, 100.0f, 500.0f},
                {50.0f, 25.0f, 125.0f}
        };
    }

    @Test
    public void getPriceParameterizedTest() {
        Burger burger = new Burger();

        Bun bun = new Bun("Булка", bunPrice);
        Ingredient ingredient =
                new Ingredient(IngredientType.SAUCE, "Соус", ingredientPrice);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        Assert.assertEquals(expectedPrice, burger.getPrice(), 0.0f);
    }
}
