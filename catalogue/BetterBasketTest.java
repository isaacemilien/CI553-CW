package catalogue;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class BetterBasketTest {
    
    BetterBasket basket = new BetterBasket();
    Product product = new Product(
        "0001", 
        "Test desc",
        10.0,
        1);
    @Test
    void testAdd() {
        basket.add(product);
        basket.add(product);

        assertEquals(basket.size(), 1);
    }
}
