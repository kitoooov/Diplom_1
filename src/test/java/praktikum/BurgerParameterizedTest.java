package praktikum;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {

    private float ingredientPrice;
    private float expectedPrice;

    private Burger burger;

    public BurgerParameterizedTest(float ingredientPrice, float expectedPrice) {
        this.ingredientPrice = ingredientPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0f, 100f},
                {50f, 150f},
                {100f, 200f}
        });
    }

    @Before
    public void setUp() {
        burger = new Burger();

        Bun bun = Mockito.mock(Bun.class);
        Mockito.when(bun.getPrice()).thenReturn(50f);

        burger.setBuns(bun);

        if (ingredientPrice > 0) {
            Ingredient ingredient = Mockito.mock(Ingredient.class);
            Mockito.when(ingredient.getPrice()).thenReturn(ingredientPrice);

            burger.addIngredient(ingredient);
        }
    }

    @Test
    public void testPriceParameterized() {
        Assert.assertEquals(expectedPrice, burger.getPrice(), 0.01);
    }
}


