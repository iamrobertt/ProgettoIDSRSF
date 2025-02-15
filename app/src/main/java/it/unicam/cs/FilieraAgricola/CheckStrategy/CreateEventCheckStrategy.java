package it.unicam.cs.FilieraAgricola.CheckStrategy;


import it.unicam.cs.FilieraAgricola.Event.*;
import it.unicam.cs.FilieraAgricola.Product.BundleProduct;
import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CreateEventCheckStrategy implements CheckStrategy<Event>{


    @Autowired
    private EventUtility eventUtility;

    @Autowired
    private AddGuestCheckStrategy addGuestCheckStrategy;

    @Autowired
    private AddBundleToEventCheckStrategy addBundleToEventCheckStrategy;


    @Override
    public boolean validate(User user, Event event) {


        if(user == null)
            throw new IllegalArgumentException("Error retrieving user information.");


        if(!this.eventUtility.checkEventInfo(event))
            throw new IllegalArgumentException("Error retrieving event information.");


        if(!this.eventUtility.checkExistEvent(event))
            throw new IllegalArgumentException("Event does not exist.");


        //check for all the guests in the event
        for(EventParticipant eventParticipant : event.getParticipants())
            if(!this.addGuestCheckStrategy.validate(eventParticipant.getParticipant(),event))
                throw new IllegalArgumentException("Error adding user id" + eventParticipant.getParticipant().getUserID() + " to the event.");


        if(event instanceof SimpleEvent)
            return true;


        TastingEvent tastingEvent = (TastingEvent) event;

        if(!this.addBundleToEventCheckStrategy.validate(user, tastingEvent))
            throw new IllegalArgumentException("Error adding products to event");

        return true;

    }
}
