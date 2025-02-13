package it.unicam.cs.FilieraAgricola.Command;

import it.unicam.cs.FilieraAgricola.Event.Event;
import it.unicam.cs.FilieraAgricola.Event.EventParticipant;
import it.unicam.cs.FilieraAgricola.Repository.EventRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;

import java.util.ArrayList;
import java.util.List;

public class BookEventCommand extends Command<Event> {

    private final EventRepository eventRepository;

    public BookEventCommand(User user, Event event, EventRepository eventRepository) {
        super(user,event);
        this.eventRepository = eventRepository;
    }

    @Override
    public List<UserRole> getNeededAuthorization() {
        List<UserRole> neededRoles = new ArrayList<>();
        neededRoles.add(UserRole.PROMOTER);
        return neededRoles;
    }

    @Override
    public boolean hasCallerNeededAuthorization() {
        return getNeededAuthorization().contains(this.user.getUserRole());
    }

    @Override
    public void execute() {
        EventParticipant newEventParticipant = new EventParticipant(this.item, this.user);
        this.item.getParticipants().add(newEventParticipant);
        this.item.setCurrentParticipants(this.item.getCurrentParticipants() + 1);
        this.eventRepository.save(this.item);
    }
}
