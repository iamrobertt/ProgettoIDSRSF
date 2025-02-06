package it.unicam.cs.FilieraAgricola.Controller;


import it.unicam.cs.FilieraAgricola.DTO.EventDTO;
import it.unicam.cs.FilieraAgricola.Event.Event;
import it.unicam.cs.FilieraAgricola.Event.EventManager;
import it.unicam.cs.FilieraAgricola.Event.TastingEvent;
import it.unicam.cs.FilieraAgricola.Product.ProductRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Nodes.collect;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventManager eventManager;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/insertEvent")
    public String insertEvent(@RequestBody EventDTO eventDTO) {
        Event event = convertToEvent(eventDTO);
        List<UserRole> userRoles = new ArrayList<UserRole>();
        userRoles.add(UserRole.MANUFACTURER);

        User user = new User(
                user.getUserID(1),
                user.setUserName("Prova"),
                user.setUserSurname("prova cognome"),
                user.getUserEmail('email'),
                user.setUserPassword("prova_pwd"),
                user.setCompanyVATNumber("888"),
                user.setUserRole(userRoles),
                user.setUserState(UserState.AUTHENTICATED)
        );


        this.eventManager.createEventRequest(user, event);

        return "proviamo";

    }

    @GetMapping("/findEvent{eventID")
    public String findEvent (@PathVariable("eventID") int eventID){
        Optional<EventDTO> event1 = this.eventRepository.findById(eventID); //this.eventRepository.findById(eventID);

        if(event1.isPresent())
            return event1.get().getEventName();
        else
            return "porcodio";
    }




    private Event convertToEvent (EventDTO eventDTO){
        if(eventDTO.getTastingEvent != null && !eventDTO.getTastingEvent().isEmpty()){
            List<Event> events = eventDTO.getTastingEvent()
                    .steam()
                    .map(this::convertToEvent())
                    .collect(Collectors.toList());

            return new TastingEvent(
                    eventDTO.getEventID(),
                    eventDTO.getEventMaxParticipants(),
                    eventDTO.getEventCurrentParticipants(),
                    eventDTO.getParticipants(),
                    eventDTO.getProductList()

            );

        }

        return new TastingEvent(
                eventDTO.getEventID(),
                eventDTO.getEventMaxParticipants(),
                eventDTO.getEventCurrentPartcipants(),
                eventDTO.getParticipants(),
                eventDTO.getProductList()
        );
    }

}


