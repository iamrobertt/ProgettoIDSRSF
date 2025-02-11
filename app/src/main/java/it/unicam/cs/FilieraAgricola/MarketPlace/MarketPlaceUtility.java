package it.unicam.cs.FilieraAgricola.MarketPlace;

import it.unicam.cs.FilieraAgricola.Product.Product;

public class MarketPlaceUtility {

    public static boolean checkProductAvailability(Product product){

        //TODO prendi le quantitò del prodotto dal database
        int productQuantityDB = 0;

        return productQuantityDB >= product.getWarehouseProduct().getProductQuantity();
    }

}
