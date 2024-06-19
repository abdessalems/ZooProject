package com.example.zootest;

import com.example.zootest.controller.EnclosureController;
import com.example.zootest.model.Enclosure;
import com.example.zootest.service.EnclosureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EnclosureControllerTest {

    @Mock
    private EnclosureService enclosureService;

    @InjectMocks
    private EnclosureController enclosureController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(enclosureController).build();
    }

    @Test
    public void testGetAllEnclosures() throws Exception {
        mockMvc.perform(get("/enclosures"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetEnclosureById() throws Exception {
        Long enclosureId = 1L;
        Enclosure enclosure = new Enclosure(enclosureId, "Lion Enclosure", "large", List.of("Panthera leo", "Panthera tigris"));

        when(enclosureService.findById(enclosureId)).thenReturn(java.util.Optional.of(enclosure));

        mockMvc.perform(get("/enclosures/{id}", enclosureId))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddEnclosure() throws Exception {
        Enclosure enclosure = new Enclosure(null, "Lion Enclosure", "large", List.of("Panthera leo", "Panthera tigris"));

        mockMvc.perform(post("/enclosures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Lion Enclosure\", \"size\": \"large\", \"allowedSpecies\": [\"Panthera leo\", \"Panthera tigris\"] }"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateEnclosure() throws Exception {
        Long enclosureId = 1L;
        Enclosure enclosure = new Enclosure(enclosureId, "Lion Enclosure", "large", List.of("Panthera leo", "Panthera tigris"));

        mockMvc.perform(put("/enclosures/{id}", enclosureId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"name\": \"Lion Enclosure\", \"size\": \"large\", \"allowedSpecies\": [\"Panthera leo\", \"Panthera tigris\"] }"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteEnclosure() throws Exception {
        Long enclosureId = 1L;

        doNothing().when(enclosureService).deleteEnclosure(enclosureId);

        mockMvc.perform(delete("/enclosures/{id}", enclosureId))
                .andExpect(status().isOk());
    }
}

