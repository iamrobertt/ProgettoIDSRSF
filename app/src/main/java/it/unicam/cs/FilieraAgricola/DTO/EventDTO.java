package it.unicam.cs.FilieraAgricola.DTO;

import it.unicam.cs.FilieraAgricola.User.User;
import lombok.Data;
import lombok.Generated;

import java.util.List;

@Data
public class EventDTO {

    @Generated
    private int eventID;
    private int eventMaxParticipants;
    private int currentParticipants;
    private List<User> participants;
}
