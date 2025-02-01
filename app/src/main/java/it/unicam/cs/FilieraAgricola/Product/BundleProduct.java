package it.unicam.cs.FilieraAgricola.Product;

import java.util.ArrayList;
import java.util.List;

public class BundleProduct extends Product{

    private final List<Product> productsInBundle;

    BundleProduct(int bundleID, String bundleName, String bundleDescription, double bundlePrice, int bundleQuantity, ProductState bundleState, List<Product> productsInBundle) {
        super(bundleID, bundleName, bundleDescription, bundlePrice, bundleQuantity, bundleState);
        this.productsInBundle = new ArrayList<>(productsInBundle);
    }

}
