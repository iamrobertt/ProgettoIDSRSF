package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.Product.ProductUtility;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadProductCheckStrategy implements CheckStrategy<Product> {

    @Autowired
    private ProductUtility productUtility;

    @Override
    public boolean validate(User user, Product product) {

        //return this.productUtility.checkProductInfo(product) &&
               // !this.productUtility.checkExistProduct(user, product);
        return true;
    }
}
