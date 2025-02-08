package it.unicam.cs.FilieraAgricola.Product;


import java.util.HashMap;
import java.util.Map;

public class ProductLoaderFactory {

    private static Map<Class<? extends Product>, ProductLoader> productLoaders = new HashMap<>();

    static {
        productLoaders.put(SingleProduct.class, new SingleProductLoader());
        productLoaders.put(BundleProduct.class, new BundleProductLoader());
    }

    public static ProductLoader getProductLoader(Class<? extends Product> productClass) {
        return productLoaders.get(productClass);
    }
}
