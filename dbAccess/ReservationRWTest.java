package dbAccess;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import middle.StockException;

public class ReservationRWTest {


    @Test
    void testInsertReservation() throws StockException {
        ReservationRW reservationRW = new ReservationRW();

        int initialSize = reservationRW.getReservationsSize();

        reservationRW.insertReservation();
        int nextSize = reservationRW.getReservationsSize();

        assertEquals(initialSize+1, nextSize);
    }
}
