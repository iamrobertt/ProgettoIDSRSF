package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.Event.Event;

public class CreateEventCheckStrategy implements CheckStrategy {
    public CreateEventCheckStrategy(Event event) {
    }

    @Override
    public boolean validate() {
        return false;
    }
}
