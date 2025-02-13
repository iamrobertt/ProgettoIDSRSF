package it.unicam.cs.FilieraAgricola.Event;

import it.unicam.cs.FilieraAgricola.User.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "event")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "event_type", discriminatorType = DiscriminatorType.STRING)
//TODO EVENTI
//TODO AGGIORNARE CURRENT PARTICIPANT QUANDO VIENE AGGIUNTO UN NUOVO PARTECIPANTE
//TODO AGGIORNARE QUANTITA BUNDLE AGGIUNTI AD UN EVENTO + controllo disponibilità
public abstract class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    protected long eventID;

    @Column(name = "event_name")
    protected String eventName;

    @Column(name = "event_description")
    protected String eventDescription;

    @Column(name = "event_max_participants")
    protected int eventMaxParticipants;

    @Column(name = "event_current_participants")
    protected int currentParticipants;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", insertable=false, updatable=false)
    protected EventType eventType;

    @OneToMany(mappedBy = "parentEvent", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    protected List<EventParticipant> participants;

    @ManyToOne
    @JoinColumn(name = "event_creator", referencedColumnName = "user_id", nullable = false)
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
