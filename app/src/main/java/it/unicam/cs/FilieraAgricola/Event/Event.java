package it.unicam.cs.FilieraAgricola.Event;

import it.unicam.cs.FilieraAgricola.User.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "event")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "eventtype", discriminatorType = DiscriminatorType.STRING)
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

    @Enumerated(EnumType.STRING)
    @Column(name = "eventtype", insertable=false, updatable=false)
    protected EventType eventType;

    @OneToMany(mappedBy = "parentEvent", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    protected List<EventParticipant> participants;

    @ManyToOne
    @JoinColumn(name = "eventcreator", referencedColumnName = "userid", nullable = false)
    protected User eventCreator;

    public Event(
            long eventID,
            String eventName,
            String eventDescription,
            int eventMaxParticipants,
            int currentParticipants,
            EventType eventType,
            List<EventParticipant> participants
    ){
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventMaxParticipants = eventMaxParticipants;
        this.currentParticipants = currentParticipants;
        this.eventType = eventType;
        this.participants = participants;
    }

    public Event() {
    }

}
