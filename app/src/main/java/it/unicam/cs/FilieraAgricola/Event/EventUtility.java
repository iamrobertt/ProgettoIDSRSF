package it.unicam.cs.FilieraAgricola.Event;

import it.unicam.cs.FilieraAgricola.User.User;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;


public class EventUtility {

    public static boolean checkEventInfo (Event event) { return false; }
    public static boolean checkExistEvent (Event event) { return false ;}
    public static boolean isEventFull (Event event) {return false ;}
    protected List<User> CurrentEventParticipants;

    public EventUtility (List<User> CurrentEventParticipants){
        this.CurrentEventParticipants = CurrentEventParticipants;
    }
    public List<User> getCurrentEventParticipants() {
        return CurrentEventParticipants;
    }
}
