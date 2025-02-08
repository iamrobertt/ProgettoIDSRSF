package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.MarketPlace.MarketPlaceUtility;
import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.Product.ProductState;
import it.unicam.cs.FilieraAgricola.Product.ProductUtility;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.stereotype.Component;

@Component
public class BuyProductCheckStrategy implements CheckStrategy<Product>  {


    @Override
    public boolean validate(User user, Product product) {

        ProductUtility productUtility = new ProductUtility();
        //if the product does not have the necessary data to be uniquely identified, return false
        if (!productUtility.checkProductInfo(product))
            return false;

        //if the product does not exist, return false
        if (!productUtility.checkExistProduct(user, product))
            return false;

        //if the product isn't in a sell state, return false
        if(!product.getProductState().equals(ProductState.PRODUCT_VALIDATED))
            return false;

        if(!MarketPlaceUtility.checkProductAvailability(product))
            return false;

        return true;
    }
}
