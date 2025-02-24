package pl.rejmanbeata.bakery.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class BreadControllerTest {

  public static final String PATH = "/breads";
  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldCallAcceptBread() throws Exception {
    mockMvc.perform(post(PATH)
            .contentType(APPLICATION_JSON)
            .content("""
                {
                  "breadExpirationDate":"2025-02-02T18:00:00Z",
                  "breadCreationDate":"2025-02-02T18:00:00Z",
                  "breadPrice":"40.00"
                }
                """))
        .andExpect(status().isOk());
  }
  
}