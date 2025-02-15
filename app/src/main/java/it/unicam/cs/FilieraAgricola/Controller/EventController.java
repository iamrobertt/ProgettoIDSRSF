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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> insertEvent(@RequestBody EventDTO eventDTO) {

        try {
            Event event = this.controllerUtility.convertToEvent(eventDTO);
            if (event == null) {
                throw new IllegalArgumentException("Errore nella conversione dell'evento.");
            }

            Optional<User> user = this.userRepository.findById(3L);
            event.setEventCreator(user.get());

            this.eventManager.createEventRequest(user.get(), event);

        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.ok().body("Product created successfully.");
    }


    @Transactional
    @PostMapping("/addProductToTastingEvent")
    public ResponseEntity<String> addProductToTastingEvent(
                                           @RequestParam Long eventID,
                                           @RequestParam Long productId) {

        try{
            Optional<Event> event = this.eventRepository.findById(eventID);
            Optional<Product> productEvent = this.productRepository.findById(productId);

            TastingEvent tastingEvent = (TastingEvent) event.get();
            Product product = productEvent.get();


            EventProduct eventProduct = new EventProduct(tastingEvent, product);
            tastingEvent.getProductList().add(eventProduct);

            this.eventRepository.save(tastingEvent);

        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.ok().body("Product added to event " + eventID + "successfully.");
    }


    @PostMapping("/bookEvent")
    public ResponseEntity<String> bookEvent(@RequestParam long eventID) {

        try {
            Optional<Event> event = this.eventRepository.findById(eventID);

            if (!event.isPresent())
                throw new IllegalArgumentException("Event with id" + eventID + " not found.");

            Optional<User> user = this.userRepository.findById(3L);

            this.eventManager.bookEventRequest(user.get(), event.get());
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.ok().body("Product added to event " + eventID + "successfully.");
    }


}


