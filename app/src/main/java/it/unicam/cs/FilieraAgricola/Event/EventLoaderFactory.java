package it.unicam.cs.FilieraAgricola.Event;

import java.util.HashMap;
import java.util.Map;

public class EventLoaderFactory {

    private static Map<Class<? extends Event>, EventLoader> eventLoaders = new HashMap<>();

    static {
        eventLoaders.put(Event.class, new SimpleEventLoader());
        eventLoaders.put(TastingEvent.class, new TastingEventLoader());

    }
}
