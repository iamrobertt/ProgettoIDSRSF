package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.Product.*;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SellProductCheckStrategy implements CheckStrategy<Product> {

    @Autowired
    private ProductUtility productUtility;

    @Override
    public boolean validate(User user, Product product) {

        if(user == null)
            throw new IllegalArgumentException("Error retrieving user information.");

        if(product == null)
            throw new IllegalArgumentException("Error retrieving product information.");


        if (!this.productUtility.checkProductInfo(product))
            throw new IllegalArgumentException("Error retrieving product information.");


        if (!this.productUtility.checkExistProductWithUser(user,product))
            throw new IllegalArgumentException("Product does not exist.");


        if(!product.getProductState().equals(ProductState.PRODUCT_INSERTED))
            throw new IllegalArgumentException("Product already validated or waiting for validation.");


        if (product instanceof SingleProduct)
            return true;


        /*
          Due to how the bundles are crated, i only need to check the first "layer" of
          products to determine if the product can be sold.
         */

        BundleProduct bundleProduct = (BundleProduct) product;

        for(BundleItem bundleItem : bundleProduct.getBundleItems()) {

            Product realProduct = this.productUtility.getProduct(bundleItem.getProduct().getProductID());

            if (!realProduct.getProductState().equals(ProductState.PRODUCT_VALIDATED))
                throw new IllegalArgumentException("Product" + bundleProduct.getProductID() + " with id " + realProduct.getProductID() + " is not validated.");

        }

        return true;

    }
}
