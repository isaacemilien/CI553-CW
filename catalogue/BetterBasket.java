package catalogue;

import java.io.Serializable;
import java.util.Collections;

/**
 * Better basket allows stacking of purchased prodcut of the same ID and organisation of product list sorting 
 * 
 * @author  Your Name 
 * @version 1.0
 */
public class BetterBasket extends Basket implements Serializable
{
  private static final long serialVersionUID = 1L;
  
  // You need to add code here
  @Override
  public boolean add( Product pr )
  {                              
    for (Product product : this) {
      if(product.getProductNum().equals(pr.getProductNum())){
        product.setQuantity(product.getQuantity() + 1);
        return true;
      }
    }
    
    return super.add( pr );     // Call add in ArrayList
  }
}
