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
    protected long productID;

    @Column(name = "productname")
    protected String productName;

    @Column(name = "productdescription")
    protected String productDescription;

    @Column(name = "productprice")
    protected double productPrice;

    @Column(name = "productquantity")
    protected int productQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "productstate")
    protected ProductState productState;

    public Product(
            long productID,
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
