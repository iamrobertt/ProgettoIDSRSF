package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.Product.ProductState;
import it.unicam.cs.FilieraAgricola.Product.ProductUtility;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SellProductCheckStrategy implements CheckStrategy<Product> {

    @Autowired
    private ProductUtility productUtility;

    @Override
    public boolean validate(User user, Product product) {

        //if the product does not have the necessary data to be uniquely identified, return false
        if (!this.productUtility.checkProductInfo(product))
            return false;

        //if the product does not exist, return false
        if (!this.productUtility.checkExistProduct(user,product))
            return false;

        //if the product isn't in a pre-sell state, return false
        if(!product.getProductState().equals(ProductState.PRODUCT_INSERTED))
            return false;

        return true;
    }
}
