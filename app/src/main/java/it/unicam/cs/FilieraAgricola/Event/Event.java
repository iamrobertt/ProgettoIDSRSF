package it.unicam.cs.FilieraAgricola.Event;

import it.unicam.cs.FilieraAgricola.User.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
 public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "eventID")
    protected int eventID;

    protected int eventMaxParticipants;

    protected int currentParticipants;

    protected List<User> participants;

    public Event(
            int eventID,
            int eventMaxParticipants,
            int currentParticipants,
            List<User> participants
    ){
        this.eventID = eventID;
        this.eventMaxParticipants = eventMaxParticipants;
        this.currentParticipants = currentParticipants;
        this.participants = participants;
    }

    public Event() {
    }

}
