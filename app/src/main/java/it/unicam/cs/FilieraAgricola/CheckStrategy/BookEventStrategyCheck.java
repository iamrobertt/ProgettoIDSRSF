package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.Event.Event;

import it.unicam.cs.FilieraAgricola.Event.EventUtility;
import it.unicam.cs.FilieraAgricola.Event.GuestUtility;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookEventStrategyCheck implements CheckStrategy<Event> {

    @Autowired
    private EventUtility eventUtility;

    @Autowired
    private UserUtility userUtility;

    @Autowired
    private AddGuestCheckStrategy addGuestCheckStrategy;

    @Override
    public boolean validate(User user, Event event) {

        if(!this.userUtility.checkUserInfo(user))
           throw new IllegalArgumentException("Failed to retrieve user information.");


        if(!this.eventUtility.checkExistEvent(event))
            throw new IllegalArgumentException("Event does not exist.");


        if(this.eventUtility.isEventFull(event))
            throw new IllegalArgumentException("Event is currently full, no booking are available.");


        if(!this.addGuestCheckStrategy.validate(user, event))
            throw new IllegalArgumentException("Failed to book the event.");


        return true;
    }
}
