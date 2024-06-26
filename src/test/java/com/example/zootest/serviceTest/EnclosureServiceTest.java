package com.example.zootest.serviceTest;

import com.example.zootest.InvalidOperationException;
import com.example.zootest.model.Enclosure;
import com.example.zootest.repository.EnclosureRepository;
import com.example.zootest.service.EnclosureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EnclosureServiceTest {

    @Mock
    private EnclosureRepository enclosureRepository;

    @InjectMocks
    private EnclosureService enclosureService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddEnclosure() {
        // Mock data
        Enclosure newEnclosure = new Enclosure();
        newEnclosure.setId(1L);
        newEnclosure.setName("New Enclosure");
        newEnclosure.setSize("Large");
        newEnclosure.setAllowedSpecies(List.of("Elephant"));

        // Mock repository save behavior
        doNothing().when(enclosureRepository).save(any(Enclosure.class));

        // Call service method
        enclosureService.addEnclosure(newEnclosure);

        // Verify repository method invocation
        verify(enclosureRepository, times(1)).save(any(Enclosure.class));
    }

    @Test
    public void testFindAllEnclosures() {
        // Mock data
        List<Enclosure> mockEnclosures = new ArrayList<>();
        mockEnclosures.add(new Enclosure(1L, "Enclosure 1", "Medium", List.of("Lion", "Tiger"), new ArrayList<>()));
        mockEnclosures.add(new Enclosure(2L, "Enclosure 2", "Small", List.of("Giraffe"), new ArrayList<>()));

        // Mock repository behavior
        when(enclosureRepository.findAll()).thenReturn(mockEnclosures);

        // Call service method
        List<Enclosure> allEnclosures = enclosureService.findAll();

        // Verify repository method invocation
        verify(enclosureRepository, times(1)).findAll();
        assertNotNull(allEnclosures);
        assertEquals(2, allEnclosures.size());
    }

    @Test
    public void testDeleteEnclosure() {
        // Mock data
        Enclosure existingEnclosure = new Enclosure();
        existingEnclosure.setId(3L);
        existingEnclosure.setName("Enclosure to Delete");
        existingEnclosure.setSize("Small");
        existingEnclosure.setAllowedSpecies(List.of("Giraffe"));

        // Mock repository behavior
        when(enclosureRepository.findById(3L)).thenReturn(Optional.of(existingEnclosure));

        // Call service method
        enclosureService.deleteEnclosure(3L);

        // Verify repository method invocation
        verify(enclosureRepository, times(1)).deleteById(3L);
    }

    @Test
    public void testDeleteEnclosureNotFound() {
        // Mock repository behavior
        when(enclosureRepository.findById(4L)).thenReturn(Optional.empty());

        // Verify exception handling
        assertThrows(InvalidOperationException.class, () -> enclosureService.deleteEnclosure(4L));
    }
}
