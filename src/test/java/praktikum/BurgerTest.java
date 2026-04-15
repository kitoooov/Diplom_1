package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;

public class BurgerTest {

    private Burger burger;
    private Bun bun;

    @Before
    public void setUp() {
        burger = new Burger();

        bun = Mockito.mock(Bun.class);
        Mockito.when(bun.getName()).thenReturn("Булка");
        Mockito.when(bun.getPrice()).thenReturn(50f);

        burger.setBuns(bun);
    }


    @Test
    public void testAddIngredient() {
        Ingredient ingredient = Mockito.mock(Ingredient.class);

        burger.addIngredient(ingredient);

        Assert.assertEquals(1, burger.ingredients.size());
        Assert.assertEquals(ingredient, burger.ingredients.get(0));
    }


    @Test
    public void testRemoveIngredient() {
        Ingredient ingredient = Mockito.mock(Ingredient.class);
        burger.addIngredient(ingredient);

        burger.removeIngredient(0);

        Assert.assertEquals(0, burger.ingredients.size());
    }


    @Test
    public void testMoveIngredient() {
        Ingredient ingredient1 = Mockito.mock(Ingredient.class);
        Ingredient ingredient2 = Mockito.mock(Ingredient.class);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(0, 1);

        Assert.assertEquals(ingredient2, burger.ingredients.get(0));
        Assert.assertEquals(ingredient1, burger.ingredients.get(1));
    }


    @Test
    public void testGetPrice() {
        Ingredient ingredient = Mockito.mock(Ingredient.class);
        Mockito.when(ingredient.getPrice()).thenReturn(100f);

        burger.addIngredient(ingredient);

        float price = burger.getPrice();


        Assert.assertEquals(200f, price, 0.01);
    }


    @Test
    public void testGetReceipt() {
        Ingredient ingredient = Mockito.mock(Ingredient.class);

        Mockito.when(ingredient.getName()).thenReturn("Соус");
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient.getPrice()).thenReturn(100f);

        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();

        Assert.assertTrue(receipt.contains("Булка"));
        Assert.assertTrue(receipt.contains("sauce Соус"));
        Assert.assertTrue(receipt.contains("Price:"));
    }
}
