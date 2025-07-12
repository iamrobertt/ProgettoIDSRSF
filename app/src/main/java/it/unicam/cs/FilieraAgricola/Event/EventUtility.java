package it.unicam.cs.FilieraAgricola.Event;

import it.unicam.cs.FilieraAgricola.Repository.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventUtility {

    @Autowired
    private EventRepository eventRepository;

    public  boolean checkEventInfo (Event event) {
        return event != null && event.getEventID() != 0;
    }

    public boolean checkEventInfoForLoading(Event event) {
        return event != null;
    }

    public boolean checkExistEvent (Event event) {
        return this.eventRepository.findById(event.getEventID()).isPresent();
    }


    public Event getEvent(long eventID) {
        Optional<Event> event = this.eventRepository.findById(eventID);
        return event.orElse(null);
    }

    public boolean isEventFull (Event event) {
        return event.getCurrentParticipants() >= event.getEventMaxParticipants();
    }

}
