package middle;

import java.util.List;

import clients.customer.ReservationStock;

public interface ReservationReader {
    public int getReservationsSize();
    public List<ReservationStock> getAllReservationStockWhereID(int reservationID);
}
