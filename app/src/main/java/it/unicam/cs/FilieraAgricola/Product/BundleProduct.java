package it.unicam.cs.FilieraAgricola.Product;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class BundleProduct extends Product{

    private final List<Product> productsInBundle;

    public BundleProduct(long bundleID, String bundleName, String bundleDescription, double bundlePrice, int bundleQuantity, ProductState bundleState, List<Product> productsInBundle) {
        super(bundleID, bundleName, bundleDescription, bundlePrice, bundleQuantity, bundleState);
        this.productsInBundle = new ArrayList<>(productsInBundle);
    }


    @Override
    public void setProductPrice(double productPrice) {
        double price = 0.0;
        for (Product product : this.productsInBundle)
            price += product.getProductPrice();

        this.productPrice = price;
    }


    public void add(Product product) {
        if(product == null) throw new NullPointerException("Product is null");
        this.productsInBundle.add(product);
    }


}
