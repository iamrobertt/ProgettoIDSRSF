package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.Event.Event;
import it.unicam.cs.FilieraAgricola.Event.EventUtility;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DeleteEventCheckStrategy implements CheckStrategy<Event> {

    @Autowired
    private EventUtility eventUtility;
    @Autowired
    private UserUtility userUtility;

    @Override
    public boolean validate(User user, Event event) {

        if(user == null)
            throw new IllegalArgumentException("Error retrieving user information.");

        if(event == null)
            throw new IllegalArgumentException("Error retrieving event information.");

        if(!this.eventUtility.checkEventInfo(event))
            throw new IllegalArgumentException("Event does not exist.");

        if(!this.eventUtility.checkExistEvent(event))
            throw new IllegalArgumentException("Event does not exist.");

        return true;
    }
}
