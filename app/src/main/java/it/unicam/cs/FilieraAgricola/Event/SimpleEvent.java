package it.unicam.cs.FilieraAgricola.Event;


import it.unicam.cs.FilieraAgricola.User.User;

import java.util.List;

public class SimpleEvent extends Event {

    public SimpleEvent(
            long eventID,
            String eventName,
            String eventDescription,
            int eventMaxParticipants,
            int currentParticipants,
            List<User> participants
    ){
        super(eventID, eventName, eventDescription, eventMaxParticipants, currentParticipants, participants);
    }

    public SimpleEvent() {}
}
