package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.Product.*;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateProductCheckStrategy implements CustomCheckStrategy<Product, ProductValidationState>  {

    @Autowired
    private ProductUtility productUtility;

    @Override
    public boolean validate(User user, Product product, ProductValidationState productValidationState) {


        if(!this.productUtility.checkProductInfo(product))
            throw new IllegalArgumentException("Error retrieving product information.");


        if(productValidationState == null)
            throw new IllegalArgumentException("Validation state inserted not valid.");


        if(!product.getProductState().equals(ProductState.PRODUCT_TO_VALIDATE))
            throw new IllegalArgumentException("Product is not in a waiting validation state or is already validated.");


        if(!productValidationState.equals(ProductValidationState.ACCEPTED) &&
                !productValidationState.equals(ProductValidationState.DENIED))
            throw new IllegalArgumentException("New validation state does not exist.");


        if(!this.productUtility.checkExistProduct(product))
            throw new IllegalArgumentException("Product does not exist.");


        if(product instanceof SingleProduct)
            return true;


        BundleProduct bundleProduct = (BundleProduct) product;

        //all the products in a bundle need to be already validated
        for(BundleItem bundleItem : bundleProduct.getBundleItems())
            if(!bundleItem.getProduct().getProductState().equals(ProductState.PRODUCT_VALIDATED))
                throw new IllegalArgumentException("Product with id" + bundleItem.getProduct().getProductID() + " is not validated.");

        return true;
    }

}
