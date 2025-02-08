package it.unicam.cs.FilieraAgricola.Event;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "event")
public abstract class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventid")
    protected long eventID;

    @Column(name = "eventname")
    protected String eventName;

    @Column(name = "eventdescription")
    protected String eventDescription;

    @Column(name = "eventmaxparticipants")
    protected int eventMaxParticipants;

    @Column(name = "eventcurrentparticipants")
    protected int currentParticipants;

    //protected List<User> participants;

    public Event(
            long eventID,
            String eventName,
            String eventDescription,
            int eventMaxParticipants,
            int currentParticipants
    ){
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventMaxParticipants = eventMaxParticipants;
        this.currentParticipants = currentParticipants;
    }

    public Event() {
    }

}
