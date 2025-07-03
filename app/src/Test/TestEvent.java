package it.unicam.cs.FilieraAgricola.Test;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class TestEvent {

        @Autowired
        private MockMvc mockMvc;
        @Autowired

    @Test
        public void testInsertEventSuccessfull() throws Exception {

            mockMvc.perform(post("/api/event/insertEvent")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                            
                            {
                            
                                "eventName" : "uva",
                                "eventDescription" : "raccolta",
                                "eventMaxParticipants" : 50,
                                "eventCurrentParticipants" : 10,
                                "eventType" : "SIMPLE",
                                "participants" : [
                                    {
                                        "participantID": 1
                                    },
                                    {
                                        "participantID": 3
                                    }
                                ]
                            }
                            """)
                    )
                    .andExpect(status().isOk())
                    .andExpect((ResultMatcher) content().string("Inserimento avvenuto con successo"));
        }
    @Test
    public void testAddproductToTastingEventSuccessfull() throws Exception {

        mockMvc.perform(post("/api/event/addProductToTastingEvent")
                        .param("vendemmia","1L")
                        .param("penna","1L"))

                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string("Prodotto aggiunto con successo all'evento"));
    }

    @Test
    public void testBookEventSuccessfull() throws Exception {
        mockMvc.perform(post("/api/event/bookEvent")
                        .param("vendemmia","1L"))

                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateEventSuccessfull() throws Exception {

        mockMvc.perform(post("/api/event/insertEvent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            
                            {
                            
                                "eventName" : "uva",
                                "eventDescription" : "raccolta",
                                "eventMaxParticipants" : 50,
                                "eventCurrentParticipants" : 10,
                                "eventType" : "SIMPLE",
                                "participants" : [
                                    {
                                        "participantID": 1
                                    },
                                    {
                                        "participantID": 3
                                    }
                                ]
                            }
                            """))

                .andExpect(status().isOk());


        mockMvc.perform(post("/api/event/updateEvent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            
                            {
                                "eventID" : "1L",
                                "eventName" : "uva marce",
                                "eventDescription" : "raccolta",
                                "eventMaxParticipants" : 80,
                                "eventCurrentParticipants" : 90,
                                "eventType" : "SIMPLE",
                                "participants" : [
                                    {
                                        "participantID": 1
                                    },
                                    {
                                        "participantID": 3
                                    }
                                ]
                            }
                            """)
                )
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string("Aggiornamento avvenuto con successo"));
    }

    @Test
    public void testDeleteEventSuccessfull() throws Exception {

        mockMvc.perform(post("/api/event/insertEvent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            
                            {
                            
                                "eventName" : "uva",
                                "eventDescription" : "raccolta",
                                "eventMaxParticipants" : 50,
                                "eventCurrentParticipants" : 10,
                                "eventType" : "SIMPLE",
                                "participants" : [
                                    {
                                        "participantID": 1
                                    },
                                    {
                                        "participantID": 3
                                    }
                                ]
                            }
                            """))

                .andExpect(status().isOk());


        mockMvc.perform(post("/api/event/deleteEvent")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                            
                            {
                            
                                "eventName" : "uva",
                                "eventDescription" : "raccolta",
                                "eventMaxParticipants" : 50,
                                "eventCurrentParticipants" : 10,
                                "eventType" : "SIMPLE",
                                "participants" : [
                                    {
                                        "participantID": 1
                                    },
                                    {
                                        "participantID": 3
                                    }
                                ]
                            }
                            """))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string("Evento eliminato con successo"));
    }
}
