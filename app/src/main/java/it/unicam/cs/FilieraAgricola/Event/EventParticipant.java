package it.unicam.cs.FilieraAgricola.Event;


import it.unicam.cs.FilieraAgricola.User.User;
import jakarta.persistence.*;
import lombok.Data;

@Table(name = "event_participant")
@Entity
@Data
public class EventParticipant {

    @ManyToOne
    @Id
    @JoinColumn(name = "parenteventid", referencedColumnName = "eventid", nullable = false)
    private Event parentEvent;

    @ManyToOne
    @Id
    @JoinColumn(name = "participantid", referencedColumnName = "userid", nullable = false)
    private User participant;

    public EventParticipant(Event parentEvent, User participant) {
        this.parentEvent = parentEvent;
        this.participant = participant;
    }

    public EventParticipant() {

    }
}
