package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.Product.ProductUtility;

public class LoadProductCheckStrategy implements CheckStrategy {

    private final Product product;

    public LoadProductCheckStrategy(Product product) {
        this.product = product;
    }

    @Override
    public boolean validate() {
        return ProductUtility.checkProductInfo(this.product) &&
                !ProductUtility.checkExistProduct(this.product);
    }
}
