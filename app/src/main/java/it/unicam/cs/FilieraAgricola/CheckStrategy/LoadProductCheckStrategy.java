package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.Product.*;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadProductCheckStrategy implements CheckStrategy<Product> {

    @Autowired
    private ProductUtility productUtility;

    @Override
    public boolean validate(User user, Product product) {

        if(user == null)
            throw new IllegalArgumentException("Error retrieving user information.");


        if (!this.productUtility.checkProductInfoForLoading(product))
            throw new IllegalArgumentException("Error retrieving product information.");


        if (this.productUtility.checkExistProductWithUser(user, product))
            throw new IllegalArgumentException("Product already exist.");


        if (product instanceof SingleProduct)
            return true;


        BundleProduct bundleProduct = (BundleProduct) product;
        return validateBundle(bundleProduct);

    }


    private boolean validateBundle(BundleProduct bundleProduct) {

        if(bundleProduct == null)
            throw new IllegalArgumentException("Error retrieving bundle information.");

        for (BundleItem bundleItem : bundleProduct.getBundleItems()) {
            Product productInBundle = bundleItem.getProduct();

            if(productInBundle == null)
                throw new IllegalArgumentException("A product in the bundle" + bundleProduct.getProductID() + " was not found.");


            if (productInBundle instanceof BundleProduct nestedBundle)
                if (!validateBundle(nestedBundle))
                    throw new IllegalArgumentException("A product in the bundle" + nestedBundle.getProductID() + " was not found.");


            Product realProduct = this.productUtility.getProduct(productInBundle.getProductID());

            int neededQuantity = bundleItem.getProductQuantityPerBundle() * bundleProduct.getWarehouseProduct().getProductQuantity();

            if(!this.productUtility.checkProductAvailability(realProduct, neededQuantity))
                throw new IllegalArgumentException("Product with id" + realProduct.getProductID() + " does not have enough stock for the bundle.");

        }

        return true;
    }
}

