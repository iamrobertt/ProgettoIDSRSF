package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.Event.Event;
import it.unicam.cs.FilieraAgricola.Event.EventUtility;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DeleteEventCheckStrategy implements CheckStrategy<Event> {

    @Autowired
    private EventUtility eventUtility;
    @Autowired
    private UserUtility userUtility;

    @Override
    public boolean validate(User user, Event item) {

        if(!this.eventUtility.checkExistEvent(item)){
            throw new IllegalArgumentException("Event does not exist.");
        }
        if(!this.eventUtility.checkEventInfo(item)){
            throw new IllegalArgumentException("Event info does not exist.");
        }

        return true;
    }
}
