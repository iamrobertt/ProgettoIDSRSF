package it.unicam.cs.FilieraAgricola.Event;

import it.unicam.cs.FilieraAgricola.User.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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

    @Transient
    protected List<User> participants;

    @ManyToOne
    @JoinColumn(name = "eventcreator", referencedColumnName = "userid", nullable = false)
    protected User eventCreator;

    public Event(
            long eventID,
            String eventName,
            String eventDescription,
            int eventMaxParticipants,
            int currentParticipants,
            List<User> participants
    ){
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventMaxParticipants = eventMaxParticipants;
        this.currentParticipants = currentParticipants;
        this.participants = participants;
    }

    public Event() {
    }

}
