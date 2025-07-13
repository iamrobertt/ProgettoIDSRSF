package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.Event.Event;
import it.unicam.cs.FilieraAgricola.Event.EventUtility;
import it.unicam.cs.FilieraAgricola.Event.GuestUtility;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AddGuestCheckStrategy implements CheckStrategy<Event>{

    @Autowired
    private GuestUtility guestUtility;

    @Autowired
    private UserUtility userUtility;

    @Autowired
    private EventUtility eventUtility;

    public boolean validate(User guest, Event event) {

        if(!this.userUtility.checkUserInfo(guest))
            throw new IllegalArgumentException("Failed to retrieve guest information");


        if(this.guestUtility.checkExistParticipants(guest,event))
            throw new IllegalArgumentException("Already signed to the event.");

        if(this.eventUtility.isEventFull(event))
            throw new IllegalArgumentException("The event is already full");

        return true;

    }

}

