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

        //if the product does not have the necessary data to be uniquely identified, return false
        if (!this.productUtility.checkProductInfo(product))
            return false;

        //if the product does not exist, return false
        if (!this.productUtility.checkExistProduct(product))
            return false;

        //if the product isn't in a sell state, return false
        if(!product.getProductState().equals(ProductState.PRODUCT_VALIDATED))
            return false;

        if(product instanceof SingleProduct singleProduct)
            return this.productUtility.checkProductAvailability(singleProduct, neededQuantity.intValue());

        //if the product is a bundle, check if al the products inside the bundle are available
        BundleProduct bundleProduct = (BundleProduct) product;
        for(BundleItem bundleItem : bundleProduct.getBundleItems())
            if(!this.productUtility.checkProductAvailability(bundleItem.getProduct(), bundleItem.getProductQuantity()))
                return false;

        return true;
    }
}
