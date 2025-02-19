/**
 * @author  Mike Smith University of Brighton
 * Interface Middle factory
 * @version 2.0
 */

package middle;

/**
  * Provide access to middle tier components.
  */

// Pattern: Abstract Factory

public interface MiddleFactory
{
 
  /**
   * Return an object to access the database for read only access
   * @return instance of StockReader
   * @throws StockException if issue
   */
  public StockReader makeStockReader() throws StockException;

  /**
   * Return an object to access the database for read/write access
   * @return instance of StockReadWriter
   * @throws StockException if issue
   */
  public StockReadWriter makeStockReadWriter() throws StockException;

  
  /**
   * Return an object to access the order processing system
   * @return instance of OrderProcessing
   * @throws OrderException if issue
   */
  public OrderProcessing makeOrderProcessing() throws OrderException;

  /**
   * Return an object to access the database for read/write access
   * @return instance of ReservationReader
   * @throws StockException if issue
   */
  public ReservationReader makeReservationReader() throws StockException;
  
  /**
   * Return an object to access the database for read/write access
   * @return instance of ReservationReadWriter
   * @throws StockException if issue
   */
  public ReservationReadWriter makeReservationReadWriter() throws StockException;

}

