package it.unicam.cs.FilieraAgricola.Product;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "bundle")
@Entity
public class BundleItem {

    @ManyToOne
    @Id
    @JoinColumn(name = "bundle_id", referencedColumnName = "product_id")
    private BundleProduct parentBundle;


    @ManyToOne
    @Id
    @JoinColumn(name = "bundle_product_id", referencedColumnName = "product_id")
    private Product product;

    @Column(name = "bundle_product_quantity")
    int productQuantity;

    public BundleItem(BundleProduct parentBundle, Product product, int productQuantity) {
        this.parentBundle = parentBundle;
        this.product = product;
        this.productQuantity = productQuantity;
    }

    public BundleItem() {

    }
}
