package it.unicam.cs.FilieraAgricola.Event;

import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.User.User;
import lombok.Data;

import java.util.List;

@Data
public  class TastingEvent extends Event{

    protected List<Product> productList;

    public TastingEvent(
            int eventID,
            int eventMaxParticipants,
            int currentParticipants,
            List<User> participants,
            List<Product> productList
    ){
        super(eventID, eventMaxParticipants,currentParticipants,participants);
        this.productList = productList;}

    public TastingEvent(){}

}
