package com.github.alina1999mikh.controller;

import com.github.alina1999mikh.model.Link;
import com.github.alina1999mikh.repository.LinksMapRepository;
import com.github.alina1999mikh.service.GetLink;
import org.apache.juli.logging.Log;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
//@Component("userDetailsService")
//@Service
public class AppTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("getShortLinkTest")
    void getShortLinkTest() throws Exception {
        mockMvc.perform(
                post("/full")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("Q=https://www.google.ru/"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getFullLinkTest")
    void getFullLinkTest() throws Exception {
        LinkController linkController = new LinkController();
        String link = linkController.getShortLink("https://www.google.ru/").getLink();

        mockMvc.perform(
                get("/short")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(link.substring(link.lastIndexOf("/short?")+7)))
                .andExpect(status().isFound());
    }
}