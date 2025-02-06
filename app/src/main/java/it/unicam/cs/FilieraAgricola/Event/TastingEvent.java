package it.unicam.cs.FilieraAgricola.Event;

import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.User.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity

public  class TastingEvent extends Event{

    protected List<Product> productList;

    public TastingEvent(

            int eventMaxParticipants,
            int currentParticipants,
            List<User> participants,
            List<Product> productList
    ){
        super(eventMaxParticipants,currentParticipants,participants);
        this.productList = productList;}

}
