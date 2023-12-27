package middle;

public interface ReservationReadWriter extends ReservationReader {
    public void generateNewReservation() throws StockException;
    public int getReservationCount()throws StockException;
}
