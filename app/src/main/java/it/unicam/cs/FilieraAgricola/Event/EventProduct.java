package it.unicam.cs.FilieraAgricola.Event;

import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.User.User;
import jakarta.persistence.*;
import lombok.Data;

@Table(name = "event_product")
@Entity
@Data

public class EventProduct {

    @ManyToOne
    @Id
    @JoinColumn(name = "parentproductid", referencedColumnName = "eventid", nullable = false)
    private Event parentProduct;

    @ManyToOne
    @Id
    @JoinColumn(name = "productid", referencedColumnName = "productid", nullable = false)
    private Product product;

    public EventProduct(Event parentProduct, Product product){
        this.parentProduct = parentProduct;
        this.product = product;
    }

    public EventProduct(){}



}
