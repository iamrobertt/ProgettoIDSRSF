package it.unicam.cs.FilieraAgricola.Event;

import it.unicam.cs.FilieraAgricola.Product.Product;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("TASTING")
public  class TastingEvent extends Event{

    @ManyToMany
    @JoinTable(
            name = "event_products",
            joinColumns = @JoinColumn(name = "productid"),
            inverseJoinColumns = @JoinColumn(name = "productid", insertable = false, updatable = false)
    )
    protected List<Product> productList;

    public TastingEvent(
            long eventID,
            String eventName,
            String eventDescription,
            int eventMaxParticipants,
            int currentParticipants,
            EventType eventType,
            List<EventParticipant> participants,
            List<Product> productList
    ){
        super(eventID, eventName, eventDescription, eventMaxParticipants, currentParticipants, eventType, participants);
        this.productList = productList;
    }

    public TastingEvent(){}

}
