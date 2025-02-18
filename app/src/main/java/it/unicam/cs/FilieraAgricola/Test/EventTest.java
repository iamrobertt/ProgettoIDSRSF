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

        String jsonEventDTO = "{\"name\":\"Evento di prova\", \"date\":\"2025-02-18\"}";

            mockMvc.perform(post("/insertEvent"))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonEventDTO)
                    .andExpect(status().isOk())
                    .andExpect((ResultMatcher) content().string("Inserimento avvenuto con successo"));

        }
}
