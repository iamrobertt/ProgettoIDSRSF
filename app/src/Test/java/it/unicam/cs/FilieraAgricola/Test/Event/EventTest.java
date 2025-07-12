package it.unicam.cs.FilieraAgricola.Test.Event;

import it.unicam.cs.FilieraAgricola.CheckStrategy.*;
import it.unicam.cs.FilieraAgricola.Event.*;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserUtility;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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
        private UpdateOrderStateCheckStrategy updateOrderStateCheckStrategy;

        private EventUtility eventUtility;
        private User user;

        private List<EventParticipant> participants;


        @Test
        void createEvent(){


        }

}
