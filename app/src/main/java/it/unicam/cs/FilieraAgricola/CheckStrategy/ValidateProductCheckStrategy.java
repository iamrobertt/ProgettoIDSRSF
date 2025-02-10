package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.Product.ProductState;
import it.unicam.cs.FilieraAgricola.Product.ProductUtility;
import it.unicam.cs.FilieraAgricola.Product.ProductValidationState;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateProductCheckStrategy implements CustomCheckStrategy<Product, ProductValidationState>  {

    @Autowired
    private ProductUtility productUtility;

    @Override
    public boolean validate(User user, Product product, ProductValidationState productValidationState) {

        if(!product.getProductState().equals(ProductState.PRODUCT_TO_VALIDATE))
            return false;

        if(!productValidationState.equals(ProductValidationState.ACCEPTED) &&
                !productValidationState.equals(ProductValidationState.DENIED))
            return false;


        return this.productUtility.checkProductInfo(product) &&
                this.productUtility.checkExistProductWithUser(user, product);
    }

}
