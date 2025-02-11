package it.unicam.cs.FilieraAgricola.Event;

import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.User.User;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)

public  class TastingEvent extends Event{


    protected List<Product> productList;

    public TastingEvent(
            long eventID,
            String eventName,
            String eventDescription,
            int eventMaxParticipants,
            int currentParticipants,
            List<User> participants,
            List<Product> productList
    ){
        super(eventID, eventName, eventDescription, eventMaxParticipants, currentParticipants, participants);
        this.productList = productList;
    }

    public TastingEvent(){}

}
