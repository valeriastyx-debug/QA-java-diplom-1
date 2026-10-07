package praktikum;

import static org.mockito.Mockito.when;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    @Mock
    private Ingredient secondIngredient;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsTest() {
        burger.setBuns(bun);

        Assert.assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(ingredient);

        Assert.assertEquals(1, burger.ingredients.size());
        Assert.assertEquals(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);

        burger.removeIngredient(0);

        Assert.assertEquals(1, burger.ingredients.size());
        Assert.assertEquals(secondIngredient, burger.ingredients.get(0));
    }
    @Test
    public void moveIngredientTest() {
        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(0, 1);

        Assert.assertEquals(secondIngredient, burger.ingredients.get(0));
        Assert.assertEquals(ingredient, burger.ingredients.get(1));
    }
    @Test
    public void getPriceTest() {
        when(bun.getPrice()).thenReturn(100.0f);
        when(ingredient.getPrice()).thenReturn(50.0f);
        when(secondIngredient.getPrice()).thenReturn(70.0f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);

        float actualPrice = burger.getPrice();

        Assert.assertEquals(320.0f, actualPrice, 0.0f);
    }
    @Test
    public void getReceiptTest() {
        when(bun.getName()).thenReturn("Краторная булка");
        when(bun.getPrice()).thenReturn(100.0f);

        when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient.getName()).thenReturn("Соус");
        when(ingredient.getPrice()).thenReturn(50.0f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String expectedReceipt =
                "(==== Краторная булка ====)" + System.lineSeparator() +
                        "= sauce Соус =" + System.lineSeparator() +
                        "(==== Краторная булка ====)" + System.lineSeparator() +
                        System.lineSeparator() +
                        String.format("Price: %f%n", 250.0f);

        String actualReceipt = burger.getReceipt();

        Assert.assertEquals(expectedReceipt, actualReceipt);
    }
}