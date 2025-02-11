package it.unicam.cs.FilieraAgricola.Product;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "warehouse_product")
@Data
@Entity
public class WarehouseProduct {

    @Column(name = "productquantity")
    private int productQuantity;

    @Id
    @OneToOne
    @MapsId
    @JoinColumn(name = "warehouseproductid")
    private Product product;

    public WarehouseProduct() {}

    public WarehouseProduct(Product product, int productQuantity) {
        this.product = product;
        this.productQuantity = productQuantity;
    }

}
