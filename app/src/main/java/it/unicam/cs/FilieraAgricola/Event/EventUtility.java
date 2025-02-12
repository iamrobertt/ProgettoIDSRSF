package it.unicam.cs.FilieraAgricola.Event;

import it.unicam.cs.FilieraAgricola.Repository.EventRepository;
import it.unicam.cs.FilieraAgricola.Repository.ProductRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventUtility {

    @Autowired
    private EventRepository eventRepository;

    public  boolean checkEventInfo (Event event) {
        return event != null && event.getEventID() != 0;
    }
    public  boolean checkExistEvent (Event event) {
        return event != null ;
    }
    public  boolean isEventFull (Event event) {return event!= null ;}
    protected List<User> CurrentEventParticipants;

    public EventUtility (List<User> CurrentEventParticipants){
        this.CurrentEventParticipants = CurrentEventParticipants;
    }
}
