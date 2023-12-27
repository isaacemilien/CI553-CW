package clients.customer;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import dbAccess.ReservationRW;
import middle.LocalMiddleFactory;
import middle.StockException;

public class CustomerModelTest {
    @Test
    void testDoCheck() {

    }

    @Test
    void testDoReservation() {
        CustomerModel customerModel = new CustomerModel(new LocalMiddleFactory());
        ReservationRW reservationRW;
        int before = 0;
        int after = 0;

        try {
            reservationRW = new ReservationRW();
            before = reservationRW.getReservationCount();
            customerModel.doReservation();
            after = reservationRW.getReservationCount();
        } catch (StockException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        assertEquals(before + 1, after);

    }

    @Test
    void testRemoveStock() {

    }
}
