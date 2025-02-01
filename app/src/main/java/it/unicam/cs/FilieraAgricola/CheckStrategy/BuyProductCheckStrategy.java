package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.MarketPlace.MarketPlaceUtility;
import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.Product.ProductState;
import it.unicam.cs.FilieraAgricola.Product.ProductUtility;


public class BuyProductCheckStrategy implements CheckStrategy {

    private Product product;

    public BuyProductCheckStrategy(Product product) {
        this.product = product;
    }


    @Override
    public boolean validate() {

        //if the product does not have the necessary data to be uniquely identified, return false
        if (!ProductUtility.checkProductInfo(this.product))
            return false;

        //if the product does not exist, return false
        if (!ProductUtility.checkExistProduct(this.product))
            return false;

        //if the product isn't in a sell state, return false
        if(!this.product.getProductState().equals(ProductState.PRODUCT_VALIDATED))
            return false;

        if(!MarketPlaceUtility.checkProductAvailability(this.product))
            return false;

        return true;
    }
}
