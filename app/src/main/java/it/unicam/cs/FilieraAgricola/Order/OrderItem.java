package it.unicam.cs.FilieraAgricola.Order;

import it.unicam.cs.FilieraAgricola.Product.Product;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_item")
public class OrderItem {

    @Column(name = "item_quantity")
    private int orderItemQuantity;

    @Column(name = "item_price")
    private double orderItemPrice;

    @ManyToOne
    @Id
    @JoinColumn(name = "parent_order_id", referencedColumnName = "order_id", nullable = false)
    private Order parentOrder;

    @ManyToOne
    @Id
    @JoinColumn(name = "item_product_id", referencedColumnName = "product_id", nullable = false)
    private Product orderItemProduct;


    public OrderItem() {}

    public OrderItem(Order parentOrder, Product orderItemProduct, int orderItemQuantity, double orderItemPrice) {
        this.parentOrder = parentOrder;
        this.orderItemProduct = orderItemProduct;
        this.orderItemQuantity = orderItemQuantity;
        this.orderItemPrice = orderItemPrice;
    }
}
