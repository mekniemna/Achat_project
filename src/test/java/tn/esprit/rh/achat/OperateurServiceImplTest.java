package tn.esprit.rh.achat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class OperateurServiceImplTest {

    @Mock
    OperateurRepository operateurRepository;

    @InjectMocks
    OperateurServiceImpl operateurService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveAllOperateurs() {
        // Given
        List<Operateur> operateurs = new ArrayList<>();
        operateurs.add(new Operateur());
        operateurs.add(new Operateur());
        when(operateurRepository.findAll()).thenReturn(operateurs);

        // When
        List<Operateur> result = operateurService.retrieveAllOperateurs();

        // Then
        assert !result.isEmpty();
        assert result.size() == 2;
        Assertions.assertEquals(operateurs,result);
    }

    @Test
    void testAddOperateur() {
        // Given
        Operateur operateur = new Operateur();
        operateur.setIdOperateur(1L);

        // When
        Operateur result = operateurService.addOperateur(operateur);

        // Then
        assert result != null;
        Assertions.assertEquals(operateur,result);
    }

    @Test
    void testDeleteOperateur() {
        // Given
        Long operateurId = 1L;

        // When
        operateurService.deleteOperateur(operateurId);

        // Then
        verify(operateurRepository, times(1)).deleteById(operateurId);
    }

    @Test
    void testUpdateOperateur() {
        // Given
        Operateur operateur = new Operateur();
        operateur.setIdOperateur(5L);
        operateur.setNom("new op");

        // When
        Operateur result = operateurService.updateOperateur(operateur);

        // Then
        assert result != null;
        Assertions.assertEquals(operateur,result);
    }

    @Test
    void testRetrieveOperateur() {
        // Given
        Long operateurId = 1L;
        Operateur operateur = new Operateur();
        operateur.setIdOperateur(1L);
        when(operateurRepository.findById(operateurId)).thenReturn(Optional.of(operateur));

        // When
        Operateur result = operateurService.retrieveOperateur(operateurId);

        // Then
        assert result != null;
        Assertions.assertEquals(operateur,result);
    }
}

