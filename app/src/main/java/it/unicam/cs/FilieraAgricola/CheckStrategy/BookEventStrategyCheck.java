package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.Event.Event;

public class BookEventStrategyCheck implements CheckStrategy {
    public BookEventStrategyCheck(Event event) {
    }

    @Override
    public boolean validate() {
        return false;
    }
}
