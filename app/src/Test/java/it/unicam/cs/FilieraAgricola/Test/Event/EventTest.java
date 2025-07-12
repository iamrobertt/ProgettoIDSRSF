package it.unicam.cs.FilieraAgricola.Test.Event;

import it.unicam.cs.FilieraAgricola.CheckStrategy.AddGuestCheckStrategy;
import it.unicam.cs.FilieraAgricola.CheckStrategy.CreateEventCheckStrategy;
import it.unicam.cs.FilieraAgricola.Event.*;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;
import it.unicam.cs.FilieraAgricola.User.UserState;
import it.unicam.cs.FilieraAgricola.User.UserUtility;
import org.testng.annotations.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

@Testable
public class EventTest extends Event {

        @Autowired
        private UserUtility userUtility;
        @Autowired
        private GuestUtility guestUtility;

        private CreateEventCheckStrategy createEventCheckStrategy;
        private EventUtility eventUtility;
        private User user;
        private AddGuestCheckStrategy addGuestCheckStrategy;

        public EventTest(
                long eventID,
                String eventName,
                String eventDescription,
                int eventMaxParticipants,
                int currentParticipants,
                EventType eventType,
                List<EventParticipant> participants
        ){
            this.eventID = eventID;
            this.eventName = eventName;
            this.eventDescription = eventDescription;
            this.eventMaxParticipants = eventMaxParticipants;
            this.currentParticipants = currentParticipants;
            this.eventType = eventType;
            this.participants = participants;
        }

        List<EventParticipant> participants = new ArrayList<>();

    /*
        creo oggetti di eventi:

        -event1: completo e corretto
        -event2: non corretto -> descrizione vuota
        -event3: non corretto -> id negativo
        -event4: non corretto -> incoerenza tra maxParticipnts e currentParticipnts
        -event5: non corretto -> partecipanti attuali negativi
     */
        EventTest event1 = new EventTest(1L, "vendemmia", "raccolta",
                50, 10, EventType.SIMPLE, participants);

        EventTest event2 = new EventTest(1L, "vendemmia", "",
                50, 10, EventType.SIMPLE, new ArrayList<>());

        EventTest event3 = new EventTest(-90L, "vendemmia", "raccolta",
                50, 10, EventType.SIMPLE, new ArrayList<>());

        EventTest event4 = new EventTest(1L, "vendemmia", "raccolta",
                -90, 10, EventType.SIMPLE, new ArrayList<>());
        EventTest event5 = new EventTest(1L,"vendemmia","raccolta",
                50,-30,EventType.SIMPLE,new ArrayList<>());

    /*
        creo due oggetti user:
        -user 1:è completto e corretto,
        -user2: non corretto -> campo nome vuoto, compo congnome vuoto
     */

        User user1 = new User(1L, "Mario", "Rossi", "mario.rossi@email.com","prova",
               "It56", UserRole.GENERIC_USER, UserState.VALIDATED);

        User user2 = new User(1L, "", "", "mario.rossi@email.com","prova",
            "It56", UserRole.GENERIC_USER, UserState.VALIDATED);

    /*
        aggiungo user1 a event1, mi aspetto che il test testAddPartcipants, fallisca
     */

        EventParticipant participant1 = new EventParticipant(event1, user1);


        @Test
        void testUserNull() {
            assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(null,event1));
            assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(null,event2));
            assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(null,event3));
            assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(null,event4));
            assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(null,event5));
            assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(user1,null));
            assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(user1,event2));
            assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(user1,event3));
            assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(user1,event4));
            assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(user1,event5));
            assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(user2,null));
            assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(user2,event2));
            assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(user2,event3));
            assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(user2,event4));
            assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(user2,event5));


        }
        @Test
        void testEventNull() {
        assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(user1,null));
        assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(user1,event2));
        assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(user1,event3));
        assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(user1,event4));
        assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(user1,event5));
        assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(user2,null));
        assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(user2,event2));
        assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(user2,event3));
        assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(user2,event4));
        assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(user2,event5));
        }

        @Test
        void testDescriptionEvent(){
            assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(null,event2));
            assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(user1,event2));
            assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(user2,event2));
        }

        @Test
        void testExistEvent(){
            assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(user1,event3));
            assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(user2,event3));
            assertThrows(IllegalArgumentException.class, () -> createEventCheckStrategy.validate(null,event3));
        }

        @Test
        void testInfoUser(){

            assertThrows(IllegalArgumentException.class, () -> addGuestCheckStrategy.validate(null,event1));
            assertThrows(IllegalArgumentException.class, () -> addGuestCheckStrategy.validate(null,event2));
            assertThrows(IllegalArgumentException.class, () -> addGuestCheckStrategy.validate(null,event3));
            assertThrows(IllegalArgumentException.class, () -> addGuestCheckStrategy.validate(null,event4));
            assertThrows(IllegalArgumentException.class, () -> addGuestCheckStrategy.validate(null,event5));

            assertThrows(IllegalArgumentException.class, () -> addGuestCheckStrategy.validate(user2,event1));
            assertThrows(IllegalArgumentException.class, () -> addGuestCheckStrategy.validate(user2,event2));
            assertThrows(IllegalArgumentException.class, () -> addGuestCheckStrategy.validate(user2,event3));
            assertThrows(IllegalArgumentException.class, () -> addGuestCheckStrategy.validate(user2,event4));
            assertThrows(IllegalArgumentException.class, () -> addGuestCheckStrategy.validate(user2,event5));

        }

        @Test
        void testAddParticipant(){
            assertThrows(IllegalArgumentException.class, () -> addGuestCheckStrategy.validate(user1,event2));
            assertThrows(IllegalArgumentException.class, () -> addGuestCheckStrategy.validate(user1,event3));
            assertThrows(IllegalArgumentException.class, () -> addGuestCheckStrategy.validate(user1,event4));
            assertThrows(IllegalArgumentException.class, () -> addGuestCheckStrategy.validate(user1,event5));

            assertThrows(IllegalArgumentException.class, () -> addGuestCheckStrategy.validate(user2,event2));
            assertThrows(IllegalArgumentException.class, () -> addGuestCheckStrategy.validate(user2,event3));
            assertThrows(IllegalArgumentException.class, () -> addGuestCheckStrategy.validate(user2,event4));
            assertThrows(IllegalArgumentException.class, () -> addGuestCheckStrategy.validate(user2,event5));
        }

        @Test
        void testEventParticipant(){
            assertThrows(IllegalArgumentException.class, () -> addGuestCheckStrategy.validate(null,event4));
            assertThrows(IllegalArgumentException.class, () -> addGuestCheckStrategy.validate(null,event5));

            assertThrows(IllegalArgumentException.class, () -> addGuestCheckStrategy.validate(user1,event4));
            assertThrows(IllegalArgumentException.class, () -> addGuestCheckStrategy.validate(user1,event5));

            assertThrows(IllegalArgumentException.class, () -> addGuestCheckStrategy.validate(user2,event4));
            assertThrows(IllegalArgumentException.class, () -> addGuestCheckStrategy.validate(user2,event5));
        }

}
