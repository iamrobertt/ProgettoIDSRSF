package it.unicam.cs.FilieraAgricola.Controller;


import it.unicam.cs.FilieraAgricola.DTO.EventDTO;
import it.unicam.cs.FilieraAgricola.Event.*;
import it.unicam.cs.FilieraAgricola.Manager.EventManager;
import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.Repository.EventRepository;
import it.unicam.cs.FilieraAgricola.Repository.ProductRepository;
import it.unicam.cs.FilieraAgricola.Repository.UserRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @PostMapping("/insertEvent")
    public String insertEvent(@RequestBody EventDTO eventDTO) {

        Event event = this.controllerUtility.convertToEvent(eventDTO);
        if (event == null) {
            throw new IllegalArgumentException("Errore nella conversione dell'evento.");
        }

        Optional<User> user = this.userRepository.findById(3L);
        event.setEventCreator(user.get());

        this.eventManager.createEventRequest(user.get(), event);

        return "proviamo";
    }


    @Transactional
    @PostMapping("/addProductToTastingEvent")
    public String addProductToTastingEvent(@RequestParam Long eventId,
                                           @RequestParam Long productId) {

        Optional<Event> event = this.eventRepository.findById(eventId);
        Optional<Product> productEvent = this.productRepository.findById(productId);

        TastingEvent tastingEvent = (TastingEvent) event.get();
        Product product = productEvent.get();



        EventProduct eventProduct = new EventProduct(tastingEvent, product);
        tastingEvent.getProductList().add(eventProduct);

        this.eventRepository.save(tastingEvent);

        return "Prodotto aggiunto con successo all'evento di degustazione!";
    }


    @PostMapping("/bookEvent")
    public long bookEvent(@RequestParam long eventID) {

        Optional<Event> event = this.eventRepository.findById(eventID);

        if (!event.isPresent())
            throw new IllegalArgumentException("Event with id" + eventID + " not found.");

        Optional<User> user = this.userRepository.findById(3L);

        this.eventManager.bookEventRequest(user.get(), event.get());
        return 0;
    }

    @Transactional
    @PostMapping("/deleteEvent")
    public String deleteEvent(@RequestParam long eventID) {
        Optional<Event> event = this.eventRepository.findById(eventID);
        if (!event.isPresent()){
            throw new IllegalArgumentException("Event with id" + eventID + " not found.");
        }
        this.eventRepository.delete(event.get());

        return "Evento eliminato!";
    }

    @Transactional
    @PostMapping("/updateEvent")
    public String updateEvent(@RequestBody EventDTO eventDTO) {
        Optional<Event> event = this.eventRepository.findById(eventDTO.getEventID());

        if (!event.isPresent()) {
            throw new IllegalArgumentException("Event with id" + eventDTO.getEventID() + " not found.");
        }

        Event event1 = event.get();
        event1.setEventName(eventDTO.getEventName());
        event1.setEventDescription(eventDTO.getEventDescription());
        event1.setEventMaxParticipants(eventDTO.getEventMaxParticipants());
        event1.setCurrentParticipants(eventDTO.getEventCurrentParticipants());
        event1.setEventType(EventType.valueOf(eventDTO.getEventType()));

        this.eventRepository.save(event1);

        return "Evento aggiornato con succeesso";

    }

}


