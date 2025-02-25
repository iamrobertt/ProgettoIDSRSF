package it.unicam.cs.FilieraAgricola.Controller;


import it.unicam.cs.FilieraAgricola.DTO.EventDTO;
import it.unicam.cs.FilieraAgricola.Event.Event;
import it.unicam.cs.FilieraAgricola.Event.EventManager;
import it.unicam.cs.FilieraAgricola.Event.EventRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventManager eventManager;

    @Autowired
    private EventRepository eventRepository;

    @PostMapping("/insertEvent")
    public String insertEvent(@RequestBody EventDTO eventDTO) {
        ControllerUtility controllerUtility = new ControllerUtility();
        Event event = controllerUtility.convertToEvent(eventDTO);


        List<UserRole> userRole = new ArrayList<UserRole>();
        userRole.add(UserRole.PROMOTER);

        User user = new User(
                        1,
                        "ciao",
                        "ciao",
                        "ciao",
                        "ciao",
                        123456,
                        userRole,
                        UserState.AUTHENTICATED
        );


        this.eventManager.createEventRequest(user, event);

        return "proviamo";

    }

    @GetMapping("/findEvent{eventID")
    public int findEvent (@PathVariable("eventID") int eventID){
        Optional<EventDTO> event1 = this.eventRepository.findById(eventID);

        if(event1.isPresent())
            return event1.get().getEventID();
        else
            return -1;
    }



}


