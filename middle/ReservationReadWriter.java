package middle;

public interface ReservationReadWriter extends ReservationReader {
    public void insertReservation();
    public void insertReservationStock(int reservationID, String stockNo, int stockLevel);
    public void setReservationStockLevel(int reservationID, String stockNo, int stockLevel);
}
