package it.unicam.cs.FilieraAgricola.Event;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("TASTING")
@Data
public  class TastingEvent extends Event{

    @OneToMany(mappedBy = "parentEvent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<EventProduct> productList;

    public TastingEvent(
            long eventID,
            String eventName,
            String eventDescription,
            int eventMaxParticipants,
            int currentParticipants,
            EventType eventType,
            List<EventParticipant> participants,
            List<EventProduct> productList
    ){
        super(eventID, eventName, eventDescription, eventMaxParticipants, currentParticipants, eventType, participants);
        this.productList = productList;
    }

    public TastingEvent(){}

}
