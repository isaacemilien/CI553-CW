package catalogue;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;

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

    boolean wasAdded = super.add( pr );

    // Refactor to lambda later
    Collections.sort(this, new Comparator<Product>() {
      public int compare(Product pr1, Product pr2){
        if(Integer.parseInt(pr1.getProductNum()) == Integer.parseInt(pr2.getProductNum())) return 0;

        return Integer.parseInt(pr1.getProductNum()) < Integer.parseInt(pr2.getProductNum()) ? -1 : 1;
      }
    });

    return wasAdded;     // Call add in ArrayList
  }
}
