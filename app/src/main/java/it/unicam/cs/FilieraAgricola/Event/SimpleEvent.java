package it.unicam.cs.FilieraAgricola.Event;


public class SimpleEvent extends Event {

    public SimpleEvent(
            long eventID,
            String eventName,
            String eventDescription,
            int eventMaxParticipants,
            int currentParticipants
    ){
        super(eventID, eventName, eventDescription, eventMaxParticipants, currentParticipants);
    }

    public SimpleEvent() {}
}
