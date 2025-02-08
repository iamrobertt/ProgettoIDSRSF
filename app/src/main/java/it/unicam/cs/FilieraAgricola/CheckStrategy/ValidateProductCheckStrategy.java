package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.Product.ProductState;
import it.unicam.cs.FilieraAgricola.Product.ProductUtility;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.stereotype.Component;

@Component
public class ValidateProductCheckStrategy implements CustomCheckStrategy<Product, ProductState>  {


    @Override
    public boolean validate(User user, Product product, ProductState productState) {

        ProductUtility productUtility = new ProductUtility();

        if(!product.getProductState().equals(ProductState.PRODUCT_TO_VALIDATE))
            return false;

        if(!productState.equals(ProductState.PRODUCT_VALIDATED) &&
                !productState.equals(ProductState.PRODUCT_NOT_VALIDATED))
            return false;

        return productUtility.checkProductInfo(product) &&
                !productUtility.checkExistProduct(user, product);
    }

}
