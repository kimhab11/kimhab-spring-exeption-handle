package com.example.kimhabspringexeptionhandle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GlobalExceptionHandlerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenInvalidEndpoint_whenGetRequest_thenHandleException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/nonexistent-endpoint")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Not Found"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("No handler found for GET /nonexistent-endpoint"));
    }

    @Test
    public void givenValidEndpointWithInvalidData_whenGetRequest_thenHandleException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/999")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Not Found"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Resource not found: User not found with id: 999"));
    }
}
