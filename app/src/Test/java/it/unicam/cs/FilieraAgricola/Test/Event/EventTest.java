package it.unicam.cs.FilieraAgricola.Test.Event;

import it.unicam.cs.FilieraAgricola.CheckStrategy.*;
import it.unicam.cs.FilieraAgricola.Event.*;
import it.unicam.cs.FilieraAgricola.Product.*;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserState;
import it.unicam.cs.FilieraAgricola.User.UserUtility;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@Testable
public class EventTest extends Event {

        @Autowired
        private UserUtility userUtility;
        @Autowired
        private GuestUtility guestUtility;

        private CreateEventCheckStrategy createEventCheckStrategy;
        private AddGuestCheckStrategy addGuestCheckStrategy;
        private AddBundleToEventCheckStrategy addBundleToEventCheckStrategy;
        private BookEventStrategyCheck bookEventStrategyCheck;
        private DeleteEventCheckStrategy deleteEventCheckStrategy;
        private UpdateEventCheckStrategy updateEventCheckStrategy;

        private EventUtility eventUtility;
        private User user;
        private ProductUtility productUtility;

        private List<EventParticipant> participants;
        private List<EventProduct> products;


        @Test
        void createEvent(){

                User user = new User(1,"Shaz","Khan","porva@prova.prova","juventus12345","1234567", UserRole.SELLER, UserState.VALIDATED);
                User user1 = new User(3,"Robert","Necula","porva@prova.prova1","juventus12345","1234567", UserRole.SELLER, UserState.VALIDATED);

                Event event = new SimpleEvent(8,"TastingProva","DescrizioneEvento",200,2,EventType.TASTING,participants);

                EventParticipant eventParticipant = new EventParticipant(event,user);
                EventParticipant eventParticipant1 = new EventParticipant(event,user1);
                participants.add(1,eventParticipant);
                participants.add(3,eventParticipant1);

                //mi aspetto un erroe poichè utente o evetnto o entrambi sono nulli
                assertThrows(IllegalArgumentException.class,()-> createEventCheckStrategy.validate(null,event));
                assertThrows(IllegalArgumentException.class,()-> createEventCheckStrategy.validate(user,null));
                assertThrows(IllegalArgumentException.class,()-> createEventCheckStrategy.validate(null,null));

                // mi aspetto un errore poichè le info dell'evento sono errate
                event.setEventName(null);
                assertThrows(IllegalStateException.class, () -> eventUtility.checkEventInfoForLoading(event));

                event.setEventDescription("descrizioneEvento");

                // mi aspetto che mi dia errore poichè le info dell'utente non sono corrette
                user.setUserName(null);
                assertThrows(IllegalStateException.class, () -> userUtility.checkUserInfo(user));

                user.setUserName("Necula");

                //mi aspetto entrambi errori pichè gli utenti sono stati gia aggiunti a l'evento
                assertThrows(IllegalStateException.class, () -> guestUtility.checkExistParticipants(user,event));
                assertThrows(IllegalStateException.class, () -> guestUtility.checkExistParticipants(user1,event));

                //configurazione per il tasting event
                Product  product = new SingleProduct(66,"Anatra5","Pera Cotogna",12.3,5, ProductState.PRODUCT_TO_VALIDATE, ProductType.SINGLE);
                Product  product1 = new SingleProduct(67,"Papera","Pera Cotogna",12.3,5, ProductState.PRODUCT_TO_VALIDATE, ProductType.SINGLE);

                Event tastingEvent = new TastingEvent(1,"prova","descrizoneProva",200,2,EventType.TASTING,participants,products);
                EventProduct eventProduct = new EventProduct(tastingEvent,product,1);
                EventProduct eventProduct1 = new EventProduct(tastingEvent,product1,1);

                products.add(66,eventProduct);
                products.add(67,eventProduct1);


                assertThrows(IllegalStateException.class, () -> addBundleToEventCheckStrategy.validate(user,tastingEvent));

        }

        @Test
        void addGuestEvent(){

                User user = new User(3,"Robert","Necula","porva@prova.prova1","juventus12345","1234567", UserRole.SELLER, UserState.VALIDATED);

                Event event = new SimpleEvent(8,"TastingProva","DescrizioneEvento",200,2,EventType.TASTING,participants);

                EventParticipant eventParticipant = new EventParticipant(event,user);
                participants.add(1,eventParticipant);

                //mi aspetto un errore poichè l'evento o l'utente o entrambi sono nulli
                assertThrows(IllegalStateException.class, () -> addGuestCheckStrategy.validate(null,event));
                assertThrows(IllegalStateException.class, () -> addGuestCheckStrategy.validate(user,null));
                assertThrows(IllegalStateException.class, () -> addGuestCheckStrategy.validate(null,null));

                //mi aspetto un errore poichè le info su l'evento non sono corrette
               event.setEventName(null);
               assertThrows(IllegalStateException.class, () -> addGuestCheckStrategy.validate(user,event));

               event.setEventName("TastingProva");

               //mi aspetto un errore poichè l'utente ha info non complete
               user.setUserName(null);
               assertThrows(IllegalStateException.class, () -> addGuestCheckStrategy.validate(user,event));

               //mi aspetto un errore poichè l'evento è gia pieno è sta cercando di aggiungere un nuovo utente
                event.setEventMaxParticipants(1);
                event.setCurrentParticipants(1);
                User newUser = new User (4,"a","b","c","d","e",UserRole.CUSTOMER,UserState.VALIDATED);
                EventParticipant newParticipant1 = new EventParticipant(event,newUser);
                participants.add(4,newParticipant1);
                assertThrows(IllegalStateException.class, () -> addGuestCheckStrategy.validate(newUser,event));

        }

        @Test
        void addBundleToEvent(){


                User user = new User(3,"Robert","Necula","porva@prova.prova1","juventus12345","1234567", UserRole.SELLER, UserState.VALIDATED);

                // creazione di bundle product
                Product singleProduct = new SingleProduct(66,"Vodka","40%",20,3,ProductState.PRODUCT_VALIDATED,ProductType.SINGLE);
                Product singleProduct1 = new SingleProduct(66,"Gin","40%",20,3,ProductState.PRODUCT_VALIDATED,ProductType.SINGLE);
                Product singleProduct2 = new SingleProduct(66,"Rum","40%",20,3,ProductState.PRODUCT_VALIDATED,ProductType.SINGLE);

                List<BundleItem> items = new ArrayList<>();

                BundleProduct product = new BundleProduct(72,"superV2", "SuperAlcolici V2", 60, 3, ProductState.PRODUCT_INSERTED,ProductType.BUNDLE, items);

                items.add(new BundleItem(product,singleProduct,3));
                items.add(new BundleItem(product,singleProduct1,3));
                items.add(new BundleItem(product,singleProduct2,3));

                // single product
                Product  product1 = new SingleProduct(66,"Anatra5","Pera Cotogna",12.3,5, ProductState.PRODUCT_TO_VALIDATE, ProductType.SINGLE);

                //tasting event
                Event tastingEvent = new TastingEvent(1,"prova","descrizoneProva",200,2,EventType.TASTING,participants,products);

                Event simpleEvent = new SimpleEvent(1,"prova","descrizoneProva",200,2,EventType.SIMPLE,participants);

                //aggiungo il rpdotto bundle al tasting event
                EventProduct eventProduct = new EventProduct(tastingEvent,product,1);

                //aggiunta del prodotto bundle product al simple event
                EventProduct eventProduct1 = new EventProduct(simpleEvent,product,1);



                products.add(66,eventProduct);
                products.add(67,eventProduct1);

                // mi aspetto un errore perchè ho aggiunto un bundle product ad un simple event
                assertThrows(IllegalStateException.class, () -> addBundleToEventCheckStrategy.validate(user,simpleEvent));

                //mi aspetto un errore poichè un single product, del bundle product, aggiunto al tasting event non esiste.
                singleProduct1.setProductID(-1);
                assertThrows(IllegalStateException.class, () -> addBundleToEventCheckStrategy.validate(user,tastingEvent));

                //mi aspetto un errore, poichè non c'è confermità tra la wuantità in magazzino e quella da controllare
                singleProduct.setWarehouseProduct(null);
                assertThrows(IllegalStateException.class, () -> productUtility.checkProductAvailability(product,2));

                product.setProductID(66);

                //mi aspetto un errore poichè è stato aggiunto un single product al tasting event
                EventProduct eventProduct2 = new EventProduct(tastingEvent,product1,1);
                products.add(66,eventProduct2);
                assertThrows(IllegalStateException.class, () -> addBundleToEventCheckStrategy.validate(user,tastingEvent));

        }

        @Test
        void deleteEvent(){

                User user = new User(3,"Robert","Necula","porva@prova.prova1","juventus12345","1234567", UserRole.SELLER, UserState.VALIDATED);

                Event event = new SimpleEvent(8,"TastingProva","DescrizioneEvento",200,2,EventType.TASTING,participants);

                EventParticipant eventParticipant = new EventParticipant(event,user);
                participants.add(1,eventParticipant);

                //mi aspetto un errore poichè user o event o entrambi sono nulli
                assertThrows(IllegalStateException.class, () -> deleteEventCheckStrategy.validate(null,event));
                assertThrows(IllegalStateException.class, () -> deleteEventCheckStrategy.validate(user,null));
                assertThrows(IllegalStateException.class, () -> deleteEventCheckStrategy.validate(null,null));

                //mi aspetto errore nell'identificazione dellevento
                event.setEventID(-1);
                assertThrows(IllegalStateException.class, () -> deleteEventCheckStrategy.validate(user,event));

                event.setEventID(8);

                //mi aspetto un errore poichè le info su l'evento sono incomplete
                event.setEventName(null);
                assertThrows(IllegalStateException.class, () -> eventUtility.checkEventInfo(event));

        }

        @Test
        void updateEvent(){

                User user = new User(3,"Robert","Necula","porva@prova.prova1","juventus12345","1234567", UserRole.SELLER, UserState.VALIDATED);

                Event event = new SimpleEvent(8,"TastingProva","DescrizioneEvento",200,2,EventType.TASTING,participants);

                EventParticipant eventParticipant = new EventParticipant(event,user);
                participants.add(1,eventParticipant);

                //mi aspetto un errore poichè user o event o entrambi sono nulli
                assertThrows(IllegalStateException.class, () -> updateEventCheckStrategy.validate(null,event));
                assertThrows(IllegalStateException.class, () -> updateEventCheckStrategy.validate(user,null));
                assertThrows(IllegalStateException.class, () -> updateEventCheckStrategy.validate(null,null));

                //mi aspetto errore nell'identificazione dellevento
                event.setEventID(-1);
                assertThrows(IllegalStateException.class, () -> updateEventCheckStrategy.validate(user,event));

                event.setEventID(8);

                //mi aspetto un errore poichè le info su l'evento sono incomplete
                event.setEventName(null);
                assertThrows(IllegalStateException.class, () -> eventUtility.checkEventInfo(event));
        }

}
