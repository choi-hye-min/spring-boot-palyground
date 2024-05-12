package com.example.testweb;

import com.example.testweb.req.TestRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class WebTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void exampleTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String reqeust = objectMapper.writeValueAsString(new TestRequest().setAge(20).setName("aaa"));

        mockMvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(reqeust))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("aaa"));
    }
}
