package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.Event.Event;
import it.unicam.cs.FilieraAgricola.Event.EventUtility;
import it.unicam.cs.FilieraAgricola.Event.GuestUtility;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserUtility;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddGuestCheckStrategy implements CheckStrategy<Event>{

    public boolean validate(User user, Event event) {

        // if the info aren't complete
        if(!UserUtility.checkUserInfo(user))
            return false;

        //if not exist participants for this event.
        if(!GuestUtility.checkExistParticipants(user, event))
            return false;

        List<User> participants = event.getParticipants();

//        // add the partcipants a this event
//        for (User user1 : participants)
//            AddGuestCheckStrategy addGuest = new AddGuestCheckStrategy(user, event);
//            if (!addGuest.validate())
//                return false;


        return true;

    }

}

