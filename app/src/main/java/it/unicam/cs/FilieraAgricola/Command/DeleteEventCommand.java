package it.unicam.cs.FilieraAgricola.Command;

import it.unicam.cs.FilieraAgricola.Event.*;
import it.unicam.cs.FilieraAgricola.Repository.EventRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;

import java.util.ArrayList;
import java.util.List;

public class DeleteEventCommand extends Command<Event> {

    private final EventRepository eventRepository;

    public DeleteEventCommand(User user, Event item, EventRepository eventRepository) {
        super(user, item);
        this.eventRepository = eventRepository;
    }

    @Override
    public List<UserRole> getNeededAuthorization() {
        List<UserRole> neededRoles = new ArrayList<>();
        neededRoles.add(UserRole.PROMOTER);
        neededRoles.add(UserRole.ADMINISTRATOR);
        return neededRoles;
    }

    @Override
    public boolean hasCallerNeededAuthorization() {return getNeededAuthorization().contains(this.user.getUserRole());}

    @Override
    public void execute() {
        this.eventRepository.delete(this.item);
    }
}
