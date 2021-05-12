package com.github.alina1999mikh.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
//@Component("userDetailsService")
//@Service
public class AppTest {
    @Autowired
    private MockMvc mockMvc;
//    @Test
//    @DisplayName("FullTest")
//    void shouldContactsGetSuccess() throws Exception {
//        // when
//        mockMvc.perform(
//                post("/full")
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .content("Q=https://google.com"))
//                .andExpect(status().isOk());
//    }

    @Test
    @DisplayName("ShortTest")
    void shortTest() throws Exception {
        mockMvc.perform(
                get("/short")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("q=a"))
                .andExpect(status().isOk());
    }
}
