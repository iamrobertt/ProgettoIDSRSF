package it.unicam.cs.FilieraAgricola.Controller;


import it.unicam.cs.FilieraAgricola.DTO.EventDTO;
import it.unicam.cs.FilieraAgricola.Event.*;
import it.unicam.cs.FilieraAgricola.Manager.EventManager;
import it.unicam.cs.FilieraAgricola.Product.Product;
import it.unicam.cs.FilieraAgricola.Product.SingleProduct;
import it.unicam.cs.FilieraAgricola.Repository.EventRepository;
import it.unicam.cs.FilieraAgricola.Repository.ProductRepository;
import it.unicam.cs.FilieraAgricola.Repository.UserRepository;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserState;
import jakarta.transaction.Transactional;
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

        Optional<Event> event = this.eventRepository.findById(3L);
        Optional<Product> productEvent = this.productRepository.findById(productId);

        TastingEvent tastingEvent = (TastingEvent) event.get();
        Product product = productEvent.get();

        tastingEvent.getProductList().add(product);
        this.eventRepository.save(tastingEvent);


        return "Prodotto aggiunto con successo all'evento di degustazione!";
    }


    /*@Transactional
    @PostMapping("/insertTastingEvent")
    public String insertTastingEvent(@RequestBody EventDTO eventDTO) {
        if (!EventType.TASTING.equals(EventType.valueOf(eventDTO.getEventType()))) {
            return "Errore: il tipo di evento non è una degustazione!";
        }

        TastingEvent tastingEvent = (TastingEvent) this.controllerUtility.convertToEvent(eventDTO);
        Optional<User> user = this.userRepository.findById(3l);
        if (user.isPresent()) {
            tastingEvent.setEventCreator(user.get());
            eventManager.createEventRequest(user.get(), tastingEvent);
            return "TastingEvent creato con successo!";
        } else {
            return "Errore: utente non trovato!";
        }
    }*/

    @GetMapping("/findEvent{eventID}")
    public long findEvent (@PathVariable("eventID") long eventID){
        Optional<Event> event1 = null; // this.eventRepository.findById(eventID);

        if(event1.isPresent())
            return event1.get().getEventID();
        else
            return -1;
    }



}


