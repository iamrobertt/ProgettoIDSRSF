package it.unicam.cs.FilieraAgricola.Command;

import it.unicam.cs.FilieraAgricola.Event.Event;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;

import java.util.List;

public class BookEventCommand extends Command<Event> {
    public BookEventCommand(User user, Event event) {
        super(user,event);
    }

    @Override
    public List<UserRole> getNeededAuthorization() {
        return List.of();
    }

    @Override
    public boolean hasCallerNeededAuthorization() {
        return false;
    }

    @Override
    public void execute() {

    }
}
