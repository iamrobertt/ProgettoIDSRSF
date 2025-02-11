package it.unicam.cs.FilieraAgricola.Order;

import it.unicam.cs.FilieraAgricola.User.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "\"order\"")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long orderID;

    @Column(name = "ordertotalprice")
    private double totalOrderPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "orderstate")
    private OrderState orderState;

    @ManyToOne
    @JoinColumn(name = "orderbuyer", referencedColumnName = "userid", nullable = false)
    private User orderBuyer;

    @OneToMany(mappedBy = "parentOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;


    public Order(User orderBuyer, double totalOrderPrice, OrderState orderState) {
        this.orderBuyer = orderBuyer;
        this.totalOrderPrice = totalOrderPrice;
        this.orderState = orderState;
    }

    public Order() {

    }
}
