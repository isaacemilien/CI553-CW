package dbAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import clients.customer.ReservationStock;
import middle.ReservationReadWriter;
import middle.StockException;

public class ReservationRW implements ReservationReadWriter{
  private Connection theCon    = null;      // Connection to database
  private Statement  theStmt   = null;      // Statement object

  /**
   * Connects to database
   * Uses a factory method to help setup the connection
   * @throws StockException if problem
   */
  public ReservationRW()
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

    @Override
    public int getReservationsSize(){
        try
        {
            ResultSet rs   = getStatementObject().executeQuery("SELECT * FROM ReservationTable");
            int count = 0;

            while (rs.next()) {
                count++;
            }

            return count;
        } catch ( SQLException e )
        {
            return 0;
        }
    }

    @Override
    public List<ReservationStock> getAllReservationStockWhereID(int reservationID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllReservationStockWhereID'");
    }

    @Override
    public boolean itemExistsInReservationID(ReservationStock reservationStock) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'itemExistsInReservationID'");
    }

    @Override
    public int getStockLevelForReservationStock(int reservationID, String stockNo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStockLevelForReservationStock'");
    }

    @Override
    public void insertReservation() {
            try {
                getStatementObject().executeUpdate("INSERT INTO ReservationTable VALUES DEFAULT");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    @Override
    public void insertReservationStock(int reservationID, String productNo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertReservation'");
    }

    @Override
    public void setReservationStockLevel(int reservationID, String stockNo, int stockLevel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setReservationStockLevel'");
    }
    
}
