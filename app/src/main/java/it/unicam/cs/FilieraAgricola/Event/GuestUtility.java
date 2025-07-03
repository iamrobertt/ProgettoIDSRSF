package it.unicam.cs.FilieraAgricola.Event;

import it.unicam.cs.FilieraAgricola.Repository.EventRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestUtility {

    @Autowired
    private EventRepository eventRepository;

    public boolean checkExistParticipants (User user,Event event) {
        if(user == null || event == null)
            return false;

        return this.eventRepository.findByParticipantAndEvent(user.getUserID(), event.getEventID()).isPresent();
    }
}
