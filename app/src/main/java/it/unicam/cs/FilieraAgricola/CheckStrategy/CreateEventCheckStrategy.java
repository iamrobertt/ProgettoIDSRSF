package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.Event.Event;
import it.unicam.cs.FilieraAgricola.Event.EventParticipant;
import it.unicam.cs.FilieraAgricola.Event.EventUtility;
import it.unicam.cs.FilieraAgricola.Repository.EventRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateEventCheckStrategy implements CheckStrategy<Event>{

    @Autowired
    private EventUtility eventUtility;
    @Autowired
    private AddGuestCheckStrategy addGuestCheckStrategy;


    @Override
    public boolean validate(User user, Event event) {

        if(!this.eventUtility.checkEventInfo(event)){
            return false;
        }
        if(this.eventUtility.checkExistEvent(event)){
            return false;
        }

        for(EventParticipant eventParticipant : event.getParticipants())
            if(!addGuestCheckStrategy.validate(eventParticipant.getParticipant(),event))
                return false;

        return false;
    }
}
