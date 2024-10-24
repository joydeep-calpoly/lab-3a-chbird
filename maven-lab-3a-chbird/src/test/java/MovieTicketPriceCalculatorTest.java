import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MovieTicketPriceCalculatorTest {

@Test
void testMatineePricing() {
    MovieTicketPriceCalculator calculator = new MovieTicketPriceCalculator(
            LocalTime.of(12, 0), LocalTime.of(16, 0), 12, 65);
    
    int price = calculator.computePrice(LocalTime.of(13, 0), 30);
    assertEquals(2400, price);
}

@Test
void testChildDiscount() {
    MovieTicketPriceCalculator calculator = new MovieTicketPriceCalculator(
            LocalTime.of(12, 0), LocalTime.of(16, 0), 12, 65);
    
    int price = calculator.computePrice(LocalTime.of(13, 0), 10);
    assertEquals(2100, price);  // Matinee price minus child discount
}

@Test
void testSeniorDiscount() {
    MovieTicketPriceCalculator calculator = new MovieTicketPriceCalculator(
            LocalTime.of(12, 0), LocalTime.of(16, 0), 12, 65);
    
    int price = calculator.computePrice(LocalTime.of(17, 0), 70);
    assertEquals(2300, price);  // Standard price minus senior discount
}

@Test
void testInvalidMatineeTimes() {
    assertThrows(IllegalArgumentException.class, () -> {
        new MovieTicketPriceCalculator(LocalTime.of(16, 0), LocalTime.of(12, 0), 12, 65);
    });
}
}
