package it.unicam.cs.FilieraAgricola.Product;

import it.unicam.cs.FilieraAgricola.User.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Table(name = "product")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "producttype", discriminatorType = DiscriminatorType.STRING)
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productid")
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

    @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false, name = "producttype")
    protected ProductType productType;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "productuserid", referencedColumnName = "userid")
    protected User productUser;

    public Product(
            long productID,
            String productName,
            String productDescription,
            double productPrice,
            int productQuantity,
            ProductState productState,
            ProductType productType
    ) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productState = productState;
        this.productType = productType;
    }

    public Product() {}

    @Override
    public String toString() {
        return "Product [productID=" + productID + ", productName=" + productName + ", productDescription="
                + productDescription + ", productPrice=" + productPrice + ", productQuantity=" + productQuantity
                + ", productState=" + productState.getValue() + ", productType=" + productType.getValue() + "]";
    }
}
