package it.unicam.cs.FilieraAgricola.DTO;

import lombok.Data;

import java.util.List;

@Data
public class EventDTO {
    private long eventID;
    private String eventName;
    private String eventDescription;
    private int eventMaxParticipants;
    private int eventCurrentParticipants;
    private String eventType;
    private List<EventParticipantDTO> participants;
    private List<ProductWithQuantityDTO> productList;
}
