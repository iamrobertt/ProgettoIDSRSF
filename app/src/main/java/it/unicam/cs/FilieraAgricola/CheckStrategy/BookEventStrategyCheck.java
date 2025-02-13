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
    private GuestUtility guestUtility;

    @Autowired
    private AddGuestCheckStrategy addGuestCheckStrategy;

    @Override
    public boolean validate(User user, Event event) {

        if(!this.userUtility.checkUserInfo(user))
            return false;

        if(!this.eventUtility.checkExistEvent(event)){
            return false;
        }

        if(this.guestUtility.checkExistParticipants(user, event))
            return false;

        if(this.eventUtility.isEventFull(event)){
            return false;
        }

        return true;
    }
}
