package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.Product.*;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuyProductCheckStrategy implements CustomCheckStrategy<Product, Integer>  {


    @Autowired
    private ProductUtility productUtility;

    @Override
    public boolean validate(User user, Product product, Integer neededQuantity) {

        if(user == null)
            throw new IllegalArgumentException("Error retrieving user information");


        if (!this.productUtility.checkProductInfo(product))
            throw new IllegalArgumentException("Error retrieving product information.");


        if (!this.productUtility.checkExistProduct(product))
            throw new IllegalArgumentException("Product does not exist.");


        if(!product.getProductState().equals(ProductState.PRODUCT_VALIDATED))
            throw new IllegalArgumentException("Product with id " + product.getProductID() + " is not validated.");


        if(!this.productUtility.checkProductAvailability(product, neededQuantity))
            throw new IllegalArgumentException("Product with id " + product.getProductID() + " is not available.");


        return true;
    }


}
