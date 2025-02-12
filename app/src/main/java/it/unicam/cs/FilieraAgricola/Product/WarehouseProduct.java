package it.unicam.cs.FilieraAgricola.Product;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "warehouse_product")
@Data
@Entity
public class WarehouseProduct {

    @Column(name = "product_quantity")
    private int productQuantity;

    @Id
    @OneToOne
    @MapsId
    @JoinColumn(name = "warehouse_product_id")
    private Product product;

    public WarehouseProduct() {}

    public WarehouseProduct(Product product, int productQuantity) {
        this.product = product;
        this.productQuantity = productQuantity;
    }

}
