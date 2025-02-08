package it.unicam.cs.FilieraAgricola.Event;

import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.User.User;

import java.util.List;


public  class TastingEvent extends Event{

    protected List<User> participants;
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
        super(eventID, eventName, eventDescription, eventMaxParticipants,currentParticipants);
        this.participants = participants;
        this.productList = productList;
    }

    public TastingEvent(){}

}
