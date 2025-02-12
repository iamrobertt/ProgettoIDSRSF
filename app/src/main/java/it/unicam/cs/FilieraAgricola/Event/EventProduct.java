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
    @JoinColumn(name = "parenteventid", referencedColumnName = "eventid", nullable = false)
    private Event parentEvent;

    @ManyToOne
    @Id
    @JoinColumn(name = "eventproductid", referencedColumnName = "productid", nullable = false)
    private Product product;

    public EventProduct(Event parentEvent, Product product){
        this.parentEvent = parentEvent;
        this.product = product;
    }

    public EventProduct(){}



}
