package it.unicam.cs.FilieraAgricola.Order;

import it.unicam.cs.FilieraAgricola.Product.Product;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_item")
@IdClass(OrderItem.class)
public class OrderItem {


    @Column(name = "itemquantity")
    private int orderItemQuantity;

    @Id
    @Column(name = "itemprice")
    private double orderItemPrice;

    @ManyToOne
    @Id
    @JoinColumn(name = "parentorderid", referencedColumnName = "orderid", nullable = false)
    private Order parentOrder;

    @ManyToOne
    @JoinColumn(name = "itemproductid", referencedColumnName = "productid", nullable = false)
    private Product orderItemProduct;


    public OrderItem() {}

    public OrderItem(Order parentOrder, Product orderItemProduct, int orderItemQuantity, double orderItemPrice) {
        this.parentOrder = parentOrder;
        this.orderItemProduct = orderItemProduct;
        this.orderItemQuantity = orderItemQuantity;
        this.orderItemPrice = orderItemPrice;
    }
}
