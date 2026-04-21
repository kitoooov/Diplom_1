package praktikum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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
    public void testAddIngredientShouldIncreaseSize() {
        Ingredient ingredient = Mockito.mock(Ingredient.class);

        burger.addIngredient(ingredient);

        Assert.assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testAddIngredientShouldAddCorrectIngredient() {
        Ingredient ingredient = Mockito.mock(Ingredient.class);

        burger.addIngredient(ingredient);

        Assert.assertEquals(ingredient, burger.ingredients.get(0));
    }


    @Test
    public void testRemoveIngredientShouldDecreaseSize() {
        Ingredient ingredient = Mockito.mock(Ingredient.class);
        burger.addIngredient(ingredient);

        burger.removeIngredient(0);

        Assert.assertEquals(0, burger.ingredients.size());
    }


    @Test
    public void testMoveIngredientShouldMoveFirstElement() {
        Ingredient firstIngredient = Mockito.mock(Ingredient.class);
        Ingredient secondIngredient = Mockito.mock(Ingredient.class);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(0, 1);

        Assert.assertEquals(secondIngredient, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientShouldMoveSecondElement() {
        Ingredient firstIngredient = Mockito.mock(Ingredient.class);
        Ingredient secondIngredient = Mockito.mock(Ingredient.class);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(0, 1);

        Assert.assertEquals(firstIngredient, burger.ingredients.get(1));
    }


    @Test
    public void testGetPriceShouldReturnCorrectPrice() {
        Ingredient ingredient = Mockito.mock(Ingredient.class);
        Mockito.when(ingredient.getPrice()).thenReturn(100f);

        burger.addIngredient(ingredient);

        float price = burger.getPrice();

        Assert.assertEquals(200f, price, 0.01);
    }


    @Test
    public void testGetReceiptShouldContainBun() {
        Ingredient ingredient = Mockito.mock(Ingredient.class);
        Mockito.when(ingredient.getName()).thenReturn("Соус");
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient.getPrice()).thenReturn(100f);

        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();

        Assert.assertTrue(receipt.contains("Булка"));
    }

    @Test
    public void testGetReceiptShouldContainIngredient() {
        Ingredient ingredient = Mockito.mock(Ingredient.class);
        Mockito.when(ingredient.getName()).thenReturn("Соус");
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient.getPrice()).thenReturn(100f);

        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();

        Assert.assertTrue(receipt.contains("sauce Соус"));
    }

    @Test
    public void testGetReceiptShouldContainPrice() {
        Ingredient ingredient = Mockito.mock(Ingredient.class);
        Mockito.when(ingredient.getName()).thenReturn("Соус");
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient.getPrice()).thenReturn(100f);

        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();

        Assert.assertTrue(receipt.contains("Price:"));
    }
}
