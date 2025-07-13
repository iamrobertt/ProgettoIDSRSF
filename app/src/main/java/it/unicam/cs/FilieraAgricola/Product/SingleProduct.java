package it.unicam.cs.FilieraAgricola.Product;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SINGLE")
public class SingleProduct extends Product{


    public SingleProduct(long productID, String productName, String productDescription, double productPrice, int productQuantity, ProductState productState, ProductType productType) {
        super(productID, productName, productDescription, productPrice, productQuantity, productState, productType);
    }

    public SingleProduct() {}
}
