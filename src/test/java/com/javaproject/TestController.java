package com.javaproject;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import com.javaproject.database.DatabaseAccess;

@SpringBootTest
@AutoConfigureMockMvc
class TestController {

    private DatabaseAccess da;
    private MockMvc mockMvc;

    @Autowired
    public void setDatabase(DatabaseAccess da) {
        this.da = da;
    }

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    // @Test
    // public void testRoot() throws Exception {
    // mockMvc.perform(get("/"))
    // .andExpect(status().isOk())
    // .andExpect(view().name("index"));
    // }

    // @Test
    // public void testUpdateAvenger() throws Exception {
    // List<Avenger> avengers = da.getAvengers();
    // Avenger avenger = avengers.get(0);
    // Long id = avenger.getId();
    // avenger.setName("Star-Lord");

    // mockMvc.perform(post("/updateAvenger").flashAttr("avenger", avenger))
    // .andExpect(status().isFound())
    // .andExpect(redirectedUrl("/"));

    // avenger = da.getAvenger(id);
    // assertEquals(avenger.getName(), "Star-Lord");
    // }

    // @Test
    // public void testAddAvenger() throws Exception {
    // LinkedMultiValueMap<String, String> requestParams = new
    // LinkedMultiValueMap<>();

    // requestParams.add("name", "Gamora");
    // requestParams.add("age", "26");

    // int origSize = da.getAvengers().size();
    // mockMvc.perform(post("/add").params(requestParams))
    // .andExpect(status().isFound())
    // .andExpect(redirectedUrl("/"))
    // .andDo(print());
    // int newSize = da.getAvengers().size();
    // assertEquals(newSize, origSize + 1);
    // }

    // @Test
    // public void testDeleteAvenger() throws Exception {
    // List<Avenger> avengers = da.getAvengers();

    // int origSize = avengers.size();
    // Avenger avenger = avengers.get(0);
    // Long id = avenger.getId();

    // mockMvc.perform(get("/deleteAvenger/{id}", id))
    // .andExpect(status().isFound())
    // .andExpect(redirectedUrl("/"));

    // int newSize = da.getAvengers().size();

    // assertEquals(newSize, origSize - 1);
    // }
}
