package it.unicam.cs.FilieraAgricola.Product;

public class ProductUtility {

    public static boolean checkProductInfo(Product product) {
        return false;
    }

    public static boolean checkExistProduct(Product product) {
        return false;
    }

    public static boolean checkIsProductBundle(Product product) {
        return product instanceof BundleProduct;
    }
}
