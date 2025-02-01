package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.Product.ProductState;
import it.unicam.cs.FilieraAgricola.Product.ProductUtility;

public class ValidateProductCheckStrategy implements CheckStrategy {

    private Product product;
    private ProductState productState;


    public ValidateProductCheckStrategy(Product product, ProductState productState) {
        this.product = product;
        this.productState = productState;
    }


    @Override
    public boolean validate() {

        if(!this.product.getProductState().equals(ProductState.PRODUCT_TO_VALIDATE))
            return false;

        if(!this.productState.equals(ProductState.PRODUCT_VALIDATED) &&
                !this.productState.equals(ProductState.PRODUCT_NOT_VALIDATED))
            return false;

        return ProductUtility.checkProductInfo(this.product) &&
                !ProductUtility.checkExistProduct(this.product);
    }
}
