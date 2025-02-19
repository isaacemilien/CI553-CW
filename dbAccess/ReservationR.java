package dbAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import clients.customer.ReservationStock;
import debug.DEBUG;
import middle.ReservationReader;
import middle.StockException;

public class ReservationR implements ReservationReader{

  private Connection theCon    = null;      // Connection to database
  private Statement  theStmt   = null;      // Statement object

  /**
   * Connects to database
   * Uses a factory method to help setup the connection
   * @throws StockException if problem
   */
  public ReservationR()
         throws StockException
  {
    try
    {
      DBAccess dbDriver = (new DBAccessFactory()).getNewDBAccess();
      dbDriver.loadDriver();
    
      theCon  = DriverManager.getConnection
                  ( dbDriver.urlOfDatabase(), 
                    dbDriver.username(), 
                    dbDriver.password() );

      theStmt = theCon.createStatement();
      theCon.setAutoCommit( true );
    }
    catch ( SQLException e )
    {
      throw new StockException( "SQL problem:" + e.getMessage() );
    }
    catch ( Exception e )
    {
      throw new StockException("Can not load database driver.");
    }
  }

  /**
   * Returns a statement object that is used to process SQL statements
   * @return A statement object used to access the database
   */
  
  protected Statement getStatementObject()
  {
    return theStmt;
  }

  /**
   * Returns a connection object that is used to process
   * requests to the DataBase
   * @return a connection object
   */

  protected Connection getConnectionObject()
  {
    return theCon;
  }

    /**
    * Retrieves the total number of reservations from the ReservationTable.
    *
    * @return The count of reservations.
    */
    @Override
    public int getReservationsSize() {
        try {
            ResultSet rs = getStatementObject().executeQuery("SELECT * FROM ReservationTable");
            int count = 0;

            while (rs.next()) {
                count++;
            }

            return count;
        } catch (SQLException e) {
            return 0; // Return 0 in case of an exception
        }
    }


    /**
    * Retrieves all ReservationStock objects associated with the given reservationID.
    *
    * @param reservationID The ID of the reservation.
    * @return A list of ReservationStock objects.
    */
    @Override
    public List<ReservationStock> getAllReservationStockWhereID(int reservationID) {
        ResultSet rs;
        List<ReservationStock> reservationStocks = new ArrayList<>();

        try {
            rs = getStatementObject().executeQuery("SELECT * FROM ReservationStockTable WHERE reservationID = " + reservationID);

            while (rs.next()) {
                Integer id = rs.getInt("reservationID");
                String productNo = rs.getString("productNo");
                Integer stockLevel = rs.getInt("stockLevel");

                reservationStocks.add(new ReservationStock(id, productNo, stockLevel));
            }

            return reservationStocks;
        } catch (SQLException e) {
            e.printStackTrace();
            return reservationStocks;
        }
    }
    
}
