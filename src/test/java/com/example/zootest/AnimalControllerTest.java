package com.example.zootest;

import com.example.zootest.controller.AnimalController;
import com.example.zootest.model.Animal;
import com.example.zootest.service.AnimalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AnimalControllerTest {

    @Mock
    private AnimalService animalService;

    @InjectMocks
    private AnimalController animalController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(animalController).build();
    }

    @Test
    public void testGetAllAnimals() throws Exception {
        mockMvc.perform(get("/animals"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAnimalById() throws Exception {
        Long animalId = 1L;
        Animal animal = new Animal(animalId, "Lion", "Panthera leo", 5, 1L);

        when(animalService.findById(animalId)).thenReturn(java.util.Optional.of(animal));

        mockMvc.perform(get("/animals/{id}", animalId))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddAnimal() throws Exception {
        Animal animal = new Animal(null, "Lion", "Panthera leo", 5, 1L);

        mockMvc.perform(post("/animals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Lion\", \"species\": \"Panthera leo\", \"age\": 5, \"enclosureId\": 1 }"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateAnimal() throws Exception {
        Long animalId = 1L;
        Animal animal = new Animal(animalId, "Lion", "Panthera leo", 5, 1L);

        mockMvc.perform(put("/animals/{id}", animalId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"name\": \"Lion\", \"species\": \"Panthera leo\", \"age\": 5, \"enclosureId\": 1 }"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteAnimal() throws Exception {
        Long animalId = 1L;

        doNothing().when(animalService).deleteAnimal(animalId);

        mockMvc.perform(delete("/animals/{id}", animalId))
                .andExpect(status().isOk());
    }
}
