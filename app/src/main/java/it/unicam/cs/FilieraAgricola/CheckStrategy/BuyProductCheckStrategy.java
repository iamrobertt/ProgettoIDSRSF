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


        if (!this.productUtility.checkProductInfo(product))
            throw new IllegalArgumentException("Error retrieving product information.");


        if (!this.productUtility.checkExistProduct(product))
            throw new IllegalArgumentException("Product does not exist.");


        if(!product.getProductState().equals(ProductState.PRODUCT_VALIDATED))
            throw new IllegalArgumentException("Product with id " + product.getProductID() + " is not validated.");


        if(!this.productUtility.checkProductAvailability(product, neededQuantity))
            throw new IllegalArgumentException("Product with id " + product.getProductID() + " is not available.");


        if(product instanceof SingleProduct)
            return true;


        /*
          Due to how the bundles are created, there is no need to check recursively in other bundles
          I only need to check the first "layer" quantity and state
         */
        BundleProduct bundleProduct = (BundleProduct) product;

        for(BundleItem bundleItem : bundleProduct.getBundleItems()) {

            if (!this.productUtility.checkProductAvailability(bundleItem.getProduct(), bundleItem.getProductQuantityPerBundle()))
                throw new IllegalArgumentException("Product with id " + bundleItem.getProduct().getProductID() + " is not available.");

            if(!bundleItem.getProduct().getProductState().equals(ProductState.PRODUCT_VALIDATED))
                throw new IllegalArgumentException("Product with id " + bundleItem.getProduct().getProductID() + " is not validated.");
        }

        return true;
    }


}
