package com.exercise.uno;

import com.exercise.uno.mapper.UserMapper;
import com.exercise.uno.models.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ServletResponse servletResponse;

    @Test
    void getListUser_ShouldReturnUsers() throws Exception {

        String response = mockMvc.perform(get("/api/user/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn().getResponse().getContentAsString();

        //Deserializza la risposta JSON in una lista di oggetti User
        List<User> users = objectMapper.readValue(response, new TypeReference<List<User>>() {
        });

        //Verifica che la risposta sia una lista di utenti:
        assertNotNull(users);
        assertEquals(2, users.size());
        assertTrue(users.get(0) instanceof User);
        assertTrue(users.get(1) instanceof User);
    }




    @Test
    void contextLoads() {
    }

}
