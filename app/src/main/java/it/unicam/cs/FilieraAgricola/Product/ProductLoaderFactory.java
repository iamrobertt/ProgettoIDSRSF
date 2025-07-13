package it.unicam.cs.FilieraAgricola.Product;


import java.util.HashMap;
import java.util.Map;


public class ProductLoaderFactory {

    private final Map<Class<? extends Product>, ProductLoader> productLoaders = new HashMap<>();

    public ProductLoaderFactory() {
        productLoaders.put(SingleProduct.class, new SingleProductLoader());
        productLoaders.put(BundleProduct.class, new BundleProductLoader());
    }

    public ProductLoader getProductLoader(Class<? extends Product> productClass) {
        return productLoaders.get(productClass);
    }
}
