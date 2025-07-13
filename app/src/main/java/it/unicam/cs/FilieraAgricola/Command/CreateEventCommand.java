package it.unicam.cs.FilieraAgricola.Command;

import it.unicam.cs.FilieraAgricola.Event.Event;
import it.unicam.cs.FilieraAgricola.Event.EventLoader;
import it.unicam.cs.FilieraAgricola.Event.EventLoaderFactory;
import it.unicam.cs.FilieraAgricola.Event.EventParticipant;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;

import java.util.ArrayList;
import java.util.List;

public class CreateEventCommand extends Command<Event> {

    private final EventLoaderFactory eventLoaderFactory;

    public CreateEventCommand(User user, Event event, EventLoaderFactory eventLoaderFactory) {
        super(user,event);
        this.eventLoaderFactory = eventLoaderFactory;
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
        EventLoader eventLoader = this.eventLoaderFactory.getEventLoader(this.item.getClass());

        for(EventParticipant eventParticipant : this.item.getParticipants())
            eventParticipant.setParentEvent(this.item);

        this.item.setCurrentParticipants(this.item.getParticipants().size());
        eventLoader.loadEvent(this.item);
    }
}
