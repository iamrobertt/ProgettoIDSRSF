package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.Event.Event;
import it.unicam.cs.FilieraAgricola.Event.EventManager;
import it.unicam.cs.FilieraAgricola.Event.EventUtility;
import it.unicam.cs.FilieraAgricola.Event.GuestUtility;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserUtility;

import java.util.List;

public class AddGuestCheckStrategy implements CheckStrategy{

    private User user;
    private Event event;
    public AddGuestCheckStrategy (User user, Event event){
        this.user = user;
        this.event = event;
        //this.invitation = invitation;
    }

    public boolean validate() {

        // if the info aren't complete
        if(!UserUtility.checkUserInfo(user))
            return false;

        //if not exist participants for this event.
        if(!GuestUtility.checkExistParticipants(user, event))
            return false;

        List<User> participants = event.participants;

        // add the partcipants a this event
        for (User user : participants){
            AddGuestCheckStrategy addGuest = new AddGuestCheckStrategy(user, this.event);
            if (!addGuest.validate()) return false;
        }

        return true;

    }

}

