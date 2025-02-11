package it.unicam.cs.FilieraAgricola.Event;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;


@Table(name = "event")
@Entity
public class SimpleEvent extends Event {


    public SimpleEvent(
            long eventID,
            String eventName,
            String eventDescription,
            int eventMaxParticipants,
            int currentParticipants,
            EventType eventType,
            List<EventParticipant> participants
    ){
        super(eventID, eventName, eventDescription, eventMaxParticipants, currentParticipants, eventType, participants);
    }

    public SimpleEvent() {}
}
