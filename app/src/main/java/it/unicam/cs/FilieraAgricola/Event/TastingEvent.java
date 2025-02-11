package it.unicam.cs.FilieraAgricola.Event;

import it.unicam.cs.FilieraAgricola.Product.Product;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
public  class TastingEvent extends Event{


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
