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
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    protected long productID;

    @Column(name = "product_name")
    protected String productName;

    @Column(name = "product_description")
    protected String productDescription;

    @Column(name = "product_price")
    protected double productPrice;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, optional = false)
    private WarehouseProduct warehouseProduct;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_state")
    protected ProductState productState;

    @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false, name = "product_type")
    protected ProductType productType;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "product_user_id", referencedColumnName = "user_id")
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
        this.productState = productState;
        this.productType = productType;
        this.warehouseProduct = new WarehouseProduct(this, productQuantity);
    }

    public Product() {}

    @Override
    public String toString() {
        return "Product [productID=" + productID + ", productName=" + productName + ", productDescription="
                + productDescription + ", productPrice=" + productPrice + ", productQuantity=" + warehouseProduct.getProductQuantity()
                + ", productState=" + productState.getValue() + ", productType=" + productType.getValue() + "]";
    }
}
