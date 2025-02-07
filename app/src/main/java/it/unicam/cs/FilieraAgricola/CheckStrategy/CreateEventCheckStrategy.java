package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.Event.Event;
import it.unicam.cs.FilieraAgricola.Event.EventUtility;
import it.unicam.cs.FilieraAgricola.Event.GuestUtility;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserUtility;

public class CreateEventCheckStrategy implements CheckStrategy {

    private Event event;

    public CreateEventCheckStrategy(Event event) { this.event = event;}

    @Override
    public boolean validate() {

        // if the event have not the necessary info
        if(!EventUtility.checkEventInfo(this.event))
            return false;
        // if the event not exist
        if(!EventUtility.checkExistEvent(this.event))
            return false;



        return false;
    }
}
