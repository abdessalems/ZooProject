package com.example.zootest.serviceTest;

import com.example.zootest.InvalidOperationException;
import com.example.zootest.model.Enclosure;
import com.example.zootest.model.Visit;
import com.example.zootest.repository.VisitRepository;
import com.example.zootest.service.EnclosureService;
import com.example.zootest.service.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class VisitServiceTest {

    @Mock
    private VisitRepository visitRepository;

    @Mock
    private EnclosureService enclosureService;

    @InjectMocks
    private VisitService visitService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllVisits() {
        // Mock data
        List<Visit> mockVisits = Arrays.asList(
                new Visit(1L, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), Arrays.asList(1L, 2L)),
                new Visit(2L, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), Arrays.asList(3L))
        );
        when(visitRepository.findAll()).thenReturn(mockVisits);

        List<Visit> visits = visitService.findAll();

        // Verify result
        assertEquals(2, visits.size());
        assertEquals(1L, visits.get(0).getId());
        assertEquals(Arrays.asList(1L, 2L), visits.get(0).getEnclosureIds());
        assertEquals(2L, visits.get(1).getId());
        assertEquals(Arrays.asList(3L), visits.get(1).getEnclosureIds());

        // Verify repository method invocation
        verify(visitRepository, times(1)).findAll();
    }

    @Test
    public void testFindVisitById() {
        // Mock data
        Visit mockVisit = new Visit(1L, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), Arrays.asList(1L));
        when(visitRepository.findById(1L)).thenReturn(Optional.of(mockVisit));

        // Call service method
        Optional<Visit> visit = visitService.findById(1L);

        // Verify result
        assertTrue(visit.isPresent());
        assertEquals(1L, visit.get().getId());
        assertEquals(Arrays.asList(1L), visit.get().getEnclosureIds());

        // Verify repository method invocation
        verify(visitRepository, times(1)).findById(1L);
    }

    @Test
    public void testAddVisit() {
        // Mock data
        Visit newVisit = new Visit(null, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), Arrays.asList(1L));

        // Mocking EnclosureService to return a specific Enclosure
        Enclosure mockedEnclosure = new Enclosure(1L, "Enclosure 1", "Large", Arrays.asList("Lion", "Tiger"), Arrays.asList(1L));
        when(enclosureService.findById(1L)).thenReturn(Optional.of(mockedEnclosure));

        // Call service method
        visitService.addVisit(newVisit);

        // Verify repository method invocation
        verify(visitRepository, times(1)).save(any(Visit.class));
    }

    @Test
    public void testAddVisitWithInvalidEnclosure() {
        // Mock data
        Visit newVisit = new Visit(null, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), Arrays.asList(1L));

        // Mocking EnclosureService to return Optional.empty(), indicating enclosure not found
        when(enclosureService.findById(1L)).thenReturn(Optional.empty());

        // Verify exception handling
        assertThrows(InvalidOperationException.class, () -> visitService.addVisit(newVisit));

        // Verify repository method invocation
        verify(visitRepository, never()).save(any(Visit.class));
    }

    @Test
    public void testUpdateVisit() {
        // Mock data
        Visit existingVisit = new Visit(1L, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), Arrays.asList(1L));

        // Mocking EnclosureService to return a specific Enclosure
        Enclosure mockedEnclosure = new Enclosure(1L, "Enclosure 1", "Large", Arrays.asList("Lion", "Tiger"), Arrays.asList(1L));
        when(enclosureService.findById(1L)).thenReturn(Optional.of(mockedEnclosure));
        when(visitRepository.findById(1L)).thenReturn(Optional.of(existingVisit));

        // Call service method
        visitService.updateVisit(existingVisit);

        // Verify repository method invocation
        verify(visitRepository, times(1)).save(any(Visit.class));
    }

    @Test
    public void testUpdateVisitWithInvalidEnclosure() {
        // Mock data
        Visit existingVisit = new Visit(1L, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), Arrays.asList(1L));

        // Mocking EnclosureService to return Optional.empty(), indicating enclosure not found
        when(enclosureService.findById(1L)).thenReturn(Optional.empty());
        when(visitRepository.findById(1L)).thenReturn(Optional.of(existingVisit));

        // Verify exception handling
        assertThrows(InvalidOperationException.class, () -> visitService.updateVisit(existingVisit));

        // Verify repository method invocation
        verify(visitRepository, never()).save(any(Visit.class));
    }

    @Test
    public void testDeleteVisit() {
        // Mock data
        Visit existingVisit = new Visit(1L, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), Arrays.asList(1L));

        when(visitRepository.findById(1L)).thenReturn(Optional.of(existingVisit));

        // Call service method
        visitService.deleteVisit(1L);

        // Verify repository method invocation
        verify(visitRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteVisitNotFound() {
        // Mocking VisitRepository to return Optional.empty(), indicating visit not found
        when(visitRepository.findById(1L)).thenReturn(Optional.empty());

        // Verify exception handling
        assertThrows(InvalidOperationException.class, () -> visitService.deleteVisit(1L));

        // Verify repository method invocation
        verify(visitRepository, never()).deleteById(1L);
    }


}
