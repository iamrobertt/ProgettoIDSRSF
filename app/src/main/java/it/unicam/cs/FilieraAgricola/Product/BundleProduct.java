package it.unicam.cs.FilieraAgricola.Product;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("BUNDLE")
@Data
public class BundleProduct extends Product{

    @OneToMany
    @JoinTable(
            name = "bundle",
            joinColumns = @JoinColumn(name = "bundleid"),
            inverseJoinColumns = @JoinColumn(name = "bundleproductid")
    )
    private List<Product> bundleProducts = new ArrayList<>();


    public BundleProduct(long bundleID, String bundleName, String bundleDescription, double bundlePrice, int bundleQuantity, ProductState bundleState, ProductType bundleType, List<Product> productsInBundle) {
        super(bundleID, bundleName, bundleDescription, bundlePrice, bundleQuantity, bundleState, ProductType.BUNDLE);
        this.bundleProducts = productsInBundle;
    }


    public BundleProduct() {}


    @Override
    public void setProductPrice(double productPrice) {
        double price = 0.0;
        for (Product product : this.bundleProducts)
            price += product.getProductPrice();

        this.productPrice = price;
    }


    public void add(Product product) {
        if(product == null) throw new NullPointerException("Product is null");
        this.bundleProducts.add(product);
    }



}
