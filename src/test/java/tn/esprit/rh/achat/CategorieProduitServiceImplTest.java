package tn.esprit.rh.achat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class CategorieProduitServiceImplTest {

    @Mock
    CategorieProduitRepository categorieProduitRepository;

    @InjectMocks
    CategorieProduitServiceImpl categorieProduitService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveAllCategorieProduits() {
        // Given
        List<CategorieProduit> categories = new ArrayList<>();
        categories.add(new CategorieProduit());
        categories.add(new CategorieProduit());
        when(categorieProduitRepository.findAll()).thenReturn(categories);

        // When
        List<CategorieProduit> result = categorieProduitService.retrieveAllCategorieProduits();

        // Then
        assert !result.isEmpty();
        assert result.size() == 2;
        Assertions.assertTrue(result.containsAll(categories));
    }

    @Test
    void testAddCategorieProduit() {
        // Given
        CategorieProduit categorieProduit = new CategorieProduit();
        categorieProduit.setIdCategorieProduit(1L);

        // When
        when(categorieProduitRepository.save(categorieProduit)).thenReturn(categorieProduit);
       CategorieProduit c =categorieProduitService.addCategorieProduit(categorieProduit);

        // Then
        Assertions.assertTrue(c.equals(categorieProduit));
    }

    @Test
    void testDeleteCategorieProduit() {
        // Given
        Long categorieId = 1L;
        CategorieProduit categorieProduit=new CategorieProduit();
        categorieProduit.setIdCategorieProduit(1L);

        // When
        categorieProduitService.deleteCategorieProduit(1L);

        // Then
        verify(categorieProduitRepository, times(1)).deleteById(categorieId);
    }

    @Test
    void testUpdateCategorieProduit() {
        // Given
        CategorieProduit categorieProduit = new CategorieProduit();
        categorieProduit.setIdCategorieProduit(1L);

        // When
        when(categorieProduitRepository.save(categorieProduit)).thenReturn(categorieProduit);
        CategorieProduit c=categorieProduitService.updateCategorieProduit(categorieProduit);

        // Then
        Assertions.assertTrue(c.equals(categorieProduit));
    }

    @Test
    void testRetrieveCategorieProduit() {
        // Given
        Long categorieId = 1L;
        CategorieProduit categorieProduit = new CategorieProduit();
        categorieProduit.setIdCategorieProduit(1L);
        when(categorieProduitRepository.findById(1L)).thenReturn(Optional.of(categorieProduit));

        // When
        CategorieProduit result = categorieProduitService.retrieveCategorieProduit(categorieId);

        // Then
        assert result != null;
        Assertions.assertTrue(categorieProduit.equals(result));
    }
}
