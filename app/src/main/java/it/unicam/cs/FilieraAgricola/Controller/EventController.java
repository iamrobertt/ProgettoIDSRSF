package it.unicam.cs.FilieraAgricola.Controller;


import it.unicam.cs.FilieraAgricola.DTO.EventDTO;
import it.unicam.cs.FilieraAgricola.Event.Event;
import it.unicam.cs.FilieraAgricola.Event.EventParticipant;
import it.unicam.cs.FilieraAgricola.Event.SimpleEvent;
import it.unicam.cs.FilieraAgricola.Manager.EventManager;
import it.unicam.cs.FilieraAgricola.Repository.EventRepository;
import it.unicam.cs.FilieraAgricola.Repository.UserRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserState;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventManager eventManager;

    @Autowired
    private ControllerUtility controllerUtility;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @PostMapping("/insertEvent")
    public String insertEvent(@RequestBody EventDTO eventDTO) {

        Event event = this.controllerUtility.convertToEvent(eventDTO);

        Optional<User> user = this.userRepository.findById(3L);
        event.setEventCreator(user.get());

        this.eventManager.createEventRequest(user.get(), event);

        return "proviamo";

    }

    @GetMapping("/findEvent{eventID}")
    public long findEvent (@PathVariable("eventID") long eventID){
        Optional<Event> event1 = null; // this.eventRepository.findById(eventID);

        if(event1.isPresent())
            return event1.get().getEventID();
        else
            return -1;
    }



}


