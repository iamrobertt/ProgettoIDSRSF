package it.unicam.cs.FilieraAgricola.Product;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Table(name = "product")

@Entity
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productID")
    protected int productID;

    //@Column(name = "productName")
    protected String productName;

    //@Column(name = "productDescription")
    protected String productDescription;

    //@Column(name = "productPrice")
    protected double productPrice;

    //@Column(name = "productQuantity")
    protected int productQuantity;

    //@Column(name = "productState")
    protected ProductState productState;

    public Product(int productID,
            String productName,
            String productDescription,
            double productPrice,
            int productQuantity,
            ProductState productState
    ) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productState = productState;
    }

    public Product() {

    }
}
