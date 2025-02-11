package it.unicam.cs.FilieraAgricola.Event;

import it.unicam.cs.FilieraAgricola.Product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EventLoaderFactory {

    private final Map<Class<? extends Event>, EventLoader> eventLoaders = new HashMap<>();

    @Autowired
    public EventLoaderFactory(SimpleEventLoader simpleEventLoader, TastingEventLoader tastingEventLoader) {
        eventLoaders.put(SimpleEvent.class, simpleEventLoader);
        eventLoaders.put(TastingEvent.class, tastingEventLoader);
    }

    public EventLoader getEventLoader(Class<? extends Event> eventClass) {
        return eventLoaders.get(eventClass);
    }
}
