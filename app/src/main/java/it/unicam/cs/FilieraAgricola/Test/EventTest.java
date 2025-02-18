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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class EventTest {

        @Autowired
        private MockMvc mockMvc;
        @Autowired


    @Test
        public void testInsertEventSuccessfull() throws Exception {

            mockMvc.perform(post("/api/event/insertEvent"))
                    .contentType(APPLICATION_JSON)
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
                            """
                    )
                    .andExpect(status().isOk())
                    .andExpect((ResultMatcher) content().string("Inserimento avvenuto con successo"));

        }
}
