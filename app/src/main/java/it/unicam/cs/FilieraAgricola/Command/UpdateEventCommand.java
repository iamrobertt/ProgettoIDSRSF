package it.unicam.cs.FilieraAgricola.Command;

import it.unicam.cs.FilieraAgricola.Event.*;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;

import java.util.ArrayList;
import java.util.List;

public class UpdateEventCommand extends Command <Event>{

    private final EventLoaderFactory eventLoaderFactory;

    public UpdateEventCommand(User user, Event item, EventLoaderFactory eventLoaderFactory) {
        super(user, item);
        this.eventLoaderFactory = eventLoaderFactory;
    }

    @Override
    public List<UserRole> getNeededAuthorization() {
        List<UserRole> neededRoles = new ArrayList<>();
        neededRoles.add(UserRole.CUSTOMER);
        neededRoles.add(UserRole.ADMINISTRATOR);
        return neededRoles;
    }

    @Override
    public boolean hasCallerNeededAuthorization() {
        return getNeededAuthorization().contains(this.user.getUserRole());
    }

    @Override
    public void execute() {
        EventLoader eventLoader = this.eventLoaderFactory.getEventLoader(this.item.getClass());

        for (EventParticipant eventParticipant : this.item.getParticipants() ) {
            eventParticipant.setParentEvent(null);
        }

        this.item.setCurrentParticipants(0);
        eventLoader.loadEvent(this.item);
    }
}
