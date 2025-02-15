package it.unicam.cs.FilieraAgricola.Repository;

import it.unicam.cs.FilieraAgricola.Event.Event;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{
        @Query("SELECT e FROM Event e WHERE e.eventID = ?1")
        Optional<Event> findById(int id);

        @Query("SELECT ev FROM EventParticipant ev WHERE ev.parentEvent.eventID = :eventID AND ev.participant.userID = :userID")
        Optional<User> findByParticipantAndEvent(long userID, long eventID);
}
