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

        if (!this.productUtility.checkProductInfoForLoading(product))
            return false;

        if (this.productUtility.checkExistProductWithUser(user, product))
            return false;

        if (product instanceof SingleProduct)
            return true;

        if (product instanceof BundleProduct bundleProduct)
            return validateBundle(bundleProduct);

        return false;

    }


    private boolean validateBundle(BundleProduct bundleProduct) {

        for (BundleItem bundleItem : bundleProduct.getBundleItems()) {
            Product productInBundle = bundleItem.getProduct();

            if (productInBundle instanceof BundleProduct nestedBundle)
                if (!validateBundle(nestedBundle))
                    return false;


            Product realProduct = this.productUtility.getProduct(productInBundle.getProductID());
            if (realProduct == null)
                return false;

            int neededQuantity = bundleItem.getProductQuantityPerBundle() * bundleProduct.getWarehouseProduct().getProductQuantity();

            if(!this.productUtility.checkProductAvailability(realProduct, neededQuantity))
                return false;
        }

        return true;
    }
}

