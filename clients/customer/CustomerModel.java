package clients.customer;

import catalogue.Basket;
import catalogue.Product;
import debug.DEBUG;
import middle.MiddleFactory;
import middle.OrderProcessing;
import middle.ReservationReadWriter;
import middle.StockException;
import middle.StockReadWriter;
import middle.StockReader;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Implements the Model of the customer client
 * @author  Mike Smith University of Brighton
 * @version 1.0
 */
public class CustomerModel extends Observable
{
  private Product     theProduct = null;          // Current product
  private Basket      theBasket  = null;          // Bought items

  private String      pn = "";                    // Product being processed

  private StockReadWriter     theStock     = null;
  private OrderProcessing theOrder     = null;
  private ImageIcon       thePic       = null;
  private boolean reservationExists = false;
  private int currentReservationID = 0;

  private ReservationReadWriter theReservationReadWriter = null;

  /*
   * Construct the model of the Customer
   * @param mf The factory to create the connection objects
   */
  public CustomerModel(MiddleFactory mf)
  {
    try                                          // 
    {  
      theStock = mf.makeStockReadWriter();           // Database access
      theReservationReadWriter = mf.makeReservationReadWriter();
    } catch ( Exception e )
    {
      DEBUG.error("CustomerModel.constructor\n" +
                  "Database not created?\n%s\n", e.getMessage() );
    }
    theBasket = makeBasket();                    // Initial Basket
  }
  
  /**
   * return the Basket of products
   * @return the basket of products
   */
  public Basket getBasket()
  {
    return theBasket;
  }

  /**
   * Check if the product is in Stock
   * @param productNum The product number
   */
  public void doCheck(String productNum )
  {
    theBasket.clear();                          // Clear s. list
    String theAction = "";
    pn  = productNum.trim();                    // Product no.
    int    amount  = 1;                         //  & quantity
    try
    {
      if ( theStock.exists( pn ) )              // Stock Exists?
      {                                         // T
        Product pr = theStock.getDetails( pn ); //  Product
        if ( pr.getQuantity() >= amount )       //  In stock?
        { 
          theAction =                           //   Display 
            String.format( "%s : %7.2f (%2d) dssdf", //
              pr.getDescription(),              //    description
              pr.getPrice(),                    //    price
              pr.getQuantity() );               //    quantity
          pr.setQuantity( amount );             //   Require 1
          theBasket.add( pr );                  //   Add to basket
          thePic = theStock.getImage( pn );     //    product
        } else {                                //  F
          theAction =                           //   Inform
            pr.getDescription() +               //    product not
            " not in stock" ;                   //    in stock
        }
      } else {                                  // F
        theAction =                             //  Inform Unknown
          "Unknown product number " + pn;       //  product number
      }
    } catch( StockException e )
    {
      DEBUG.error("CustomerClient.doCheck()\n%s",
      e.getMessage() );
    }
    setChanged(); notifyObservers(theAction);
  }

  /**
   * Clear the products from the basket
   */
  public void doClear()
  {
    String theAction = "";
    theBasket.clear();                        // Clear s. list
    theAction = "Enter Product Number";       // Set display
    thePic = null;                            // No picture
    setChanged(); notifyObservers(theAction);

    reservationExists = false;
    currentReservationID = 0;
  }
  
  /**
   * Return a picture of the product
   * @return An instance of an ImageIcon
   */ 
  public ImageIcon getPicture()
  {
    return thePic;
  }
  
  /**
   * ask for update of view callled at start
   */
  private void askForUpdate()
  {
    setChanged(); notifyObservers("START only"); // Notify
  }

/**
 * Performs a reservation for the specified product.
 * Creates a new reservation if it doesn't exist and adds the product to the reservation.
 * Updates stock levels accordingly.
 *
 * @param pnum The product number for the reservation.
 */
public void doReservation(String pnum) {
    if (!reservationExists) {
        reservationExists = true;
        theReservationReadWriter.insertReservation();
        currentReservationID = theReservationReadWriter.getReservationsSize();
    }

    if (!pnum.equals("")) {
        try {
            theStock.buyStock(pnum, 1);
        } catch (StockException e) {
            e.printStackTrace(); // Exception handling
        }

        if (!itemExistsInReservationID(currentReservationID, pnum)) {
            theReservationReadWriter.insertReservationStock(currentReservationID, pnum, 1);
        } else {
            List<ReservationStock> reservationStocks = theReservationReadWriter.getAllReservationStockWhereID(currentReservationID);
            int currentProductStockLevel = 0;

            for (ReservationStock reservationStock : reservationStocks) {
                if (reservationStock.getProductNo().equals(pnum)) {
                    currentProductStockLevel = reservationStock.getStockLevel();
                }
            }

            theReservationReadWriter.setReservationStockLevel(currentReservationID, pnum, currentProductStockLevel++);
        }
    }
}

/**
 * Checks if a product with the given productNo exists in the ReservationStock
 * associated with the specified reservationID.
 *
 * @param reservationID The ID of the reservation.
 * @param productNo     The product number to check for existence.
 * @return True if the product exists in the reservation, false otherwise.
 */
public boolean itemExistsInReservationID(int reservationID, String productNo) {
    // Retrieve all ReservationStock objects for the given reservationID
    List<ReservationStock> reservationStocks = theReservationReadWriter.getAllReservationStockWhereID(reservationID);

    // Check if the provided productNo exists in any ReservationStock
    for (ReservationStock reservationStock : reservationStocks) {
        if (reservationStock.getProductNo().equals(productNo)) {
            return true;
        }
    }

    // Product not found in any ReservationStock
    return false;
}

  /**
   * Make a new Basket
   * @return an instance of a new Basket
   */
  protected Basket makeBasket()
  {
    return new Basket();
  }
}

