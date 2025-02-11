package it.unicam.cs.FilieraAgricola.Product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProductLoaderFactory {

    private final Map<Class<? extends Product>, ProductLoader> productLoaders = new HashMap<>();

    @Autowired
    public ProductLoaderFactory(SingleProductLoader singleProductLoader, BundleProductLoader bundleProductLoader) {
        productLoaders.put(SingleProduct.class, singleProductLoader);
        productLoaders.put(BundleProduct.class, bundleProductLoader);
    }

    public ProductLoader getProductLoader(Class<? extends Product> productClass) {
        return productLoaders.get(productClass);
    }
}
