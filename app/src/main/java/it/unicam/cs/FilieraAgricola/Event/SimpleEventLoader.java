package it.unicam.cs.FilieraAgricola.Event;

import it.unicam.cs.FilieraAgricola.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleEventLoader implements EventLoader{

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void loadEvent(Event event) {
        this.eventRepository.save(event);
    }
}
