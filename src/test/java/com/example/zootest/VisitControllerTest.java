package com.example.zootest;

import com.example.zootest.controller.VisitController;
import com.example.zootest.model.Visit;
import com.example.zootest.service.VisitService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class VisitControllerTest {

    private MockMvc mockMvc;

    @Mock
    private VisitService visitService;

    @InjectMocks
    private VisitController visitController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    public void getAllVisits_shouldReturnAllVisits() throws Exception {
        Visit visit1 = new Visit(1L, LocalDateTime.now(), LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(2), Arrays.asList(1L, 2L));
        Visit visit2 = new Visit(2L, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(1),
                LocalDateTime.now().plusDays(1).plusHours(2), Arrays.asList(3L, 4L));

        List<Visit> visits = Arrays.asList(visit1, visit2);

        when(visitService.findAll()).thenReturn(visits);

        mockMvc.perform(get("/visits"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(visits.size()));
    }

    @Test
    public void getVisitById_shouldReturnVisit_whenVisitExists() throws Exception {
        Visit visit = new Visit(1L, LocalDateTime.now(), LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(2), Arrays.asList(1L, 2L));

        when(visitService.findById(1L)).thenReturn(Optional.of(visit));

        mockMvc.perform(get("/visits/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(visit.getId().intValue()))
                .andExpect(jsonPath("$.enclosureIds").isArray());
    }




    @Test
    public void deleteVisit_shouldDeleteVisit() throws Exception {
        mockMvc.perform(delete("/visits/{id}", 1L))
                .andExpect(status().isOk());

        verify(visitService, times(1)).deleteVisit(1L);
    }
}
