package it.unicam.cs.FilieraAgricola.Event;

import it.unicam.cs.FilieraAgricola.Repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component

public class TastingEventLoader implements EventLoader{

    @Autowired
    private EventRepository eventRepository;

    @Transactional
    @Override
    public void loadEvent(Event event) {
        this.eventRepository.save(event);
    }
}
