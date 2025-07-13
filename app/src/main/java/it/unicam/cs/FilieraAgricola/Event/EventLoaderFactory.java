package it.unicam.cs.FilieraAgricola.Event;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EventLoaderFactory {

    private final Map<Class<? extends Event>, EventLoader> eventLoaders = new HashMap<>();


    public EventLoaderFactory() {
        eventLoaders.put(SimpleEvent.class, new SimpleEventLoader());
        eventLoaders.put(TastingEvent.class, new TastingEventLoader());
    }

    public EventLoader getEventLoader(Class<? extends Event> eventClass) {
        return eventLoaders.get(eventClass);
    }
}
