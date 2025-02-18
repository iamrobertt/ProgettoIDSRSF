package it.unicam.cs.FilieraAgricola.Event;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class EventTest extends Event {

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

        @Test
        public void testCreateEvent(){
            EventTest event = new EventTest(1L, "vendemmia", "raccolta",
                    50, 10, EventType.SIMPLE, new ArrayList<>());

            assertEquals(1L, event.getEventID());
            assertEquals("vendemmia", event.getEventName());
            assertEquals(50, event.getEventMaxParticipants());
            assertEquals(10, event.getCurrentParticipants());
        }

        @Test
        void testMaxParticipantNegativ() {
            assertThrows(IllegalArgumentException.class, () -> new EventTest(
                    1L, "vendemmia", "raccolta",
                    -5, 0, EventType.SIMPLE, new ArrayList<>()
            ));
        }

        @Test
        void testMaxParticipantsSuperated() {
            assertThrows(IllegalArgumentException.class, () -> new EventTest(
                    1L, "vendemmia", "raccolta",
                    50, 60, EventType.SIMPLE, new ArrayList<>()
            ));
        }

        @Test
        void testNameEventNull() {
            assertThrows(IllegalArgumentException.class, () -> new EventTest(
                    1L, "", "raccolta",
                    10, 5, EventType.SIMPLE, new ArrayList<>()
            ));
        }

        @Test
        void testDescriptionEventNull() {
            assertThrows(IllegalArgumentException.class, () -> new EventTest(
                    1L, "uva", "",
                    10, 5, EventType.SIMPLE, new ArrayList<>()
            ));
        }

        @Test
        void testAggiuntaPartecipanteQuandoEventoPieno() {
            EventTest event = new EventTest(1L, "uva", "raccolta", 1, 1, EventType.SIMPLE, new ArrayList<>());
            assertThrows(IllegalStateException.class, () -> event.participants.add(new EventParticipant()));
        }


}
