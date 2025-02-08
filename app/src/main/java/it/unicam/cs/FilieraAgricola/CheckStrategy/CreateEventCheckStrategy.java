package it.unicam.cs.FilieraAgricola.CheckStrategy;

import it.unicam.cs.FilieraAgricola.Event.Event;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.stereotype.Component;

@Component
public class CreateEventCheckStrategy implements CheckStrategy<Event> {


    @Override
    public boolean validate(User user, Event event) {
        return false;
    }
}
