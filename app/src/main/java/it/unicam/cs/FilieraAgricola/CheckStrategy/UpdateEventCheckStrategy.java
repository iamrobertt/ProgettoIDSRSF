package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.Event.Event;
import it.unicam.cs.FilieraAgricola.Event.EventUtility;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UpdateEventCheckStrategy implements CheckStrategy <Event> {

    @Autowired
    private EventUtility eventUtility;

    @Override
    public boolean validate(User user, Event item) {
        if(!this.eventUtility.checkEventInfo(item)){
            throw new IllegalArgumentException("Event info is not valid");
        }
        if(!this.eventUtility.checkExistEvent(item)){
            throw new IllegalArgumentException("Event does not exist");
        }
        return true;
    }
}
