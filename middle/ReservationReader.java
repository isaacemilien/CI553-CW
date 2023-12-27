package middle;

public interface ReservationReader {
    /**
   * Checks if the product exits in the stock list
   * @param pNum Product nymber
   * @return true if exists otherwise false
   * @throws StockException if issue
   */
  String exists(String pNum) throws StockException;
}
