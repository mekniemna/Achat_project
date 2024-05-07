package tn.esprit.rh.achat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.ReglementRepository;
import tn.esprit.rh.achat.services.ReglementServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class ReglementServiceImplTest {

    @Mock
    ReglementRepository reglementRepository;

    @Mock
    FactureRepository factureRepository;

    @InjectMocks
    ReglementServiceImpl reglementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveAllReglements() {
        // Given
        List<Reglement> reglements = new ArrayList<>();
        reglements.add(new Reglement());
        reglements.add(new Reglement());
        when(reglementRepository.findAll()).thenReturn(reglements);

        // When
        List<Reglement> result = reglementService.retrieveAllReglements();

        // Then
        assert !result.isEmpty();
        assert result.size() == 2;
    }

    @Test
    void testAddReglement() {
        // Given
        Reglement reglement = new Reglement();

        // When
        Reglement result = reglementService.addReglement(reglement);

        // Then
        assert result != null;
    }

    @Test
    void testRetrieveReglement() {
        // Given
        Long reglementId = 1L;
        Reglement reglement = new Reglement();
        when(reglementRepository.findById(reglementId)).thenReturn(Optional.of(reglement));

        // When
        Reglement result = reglementService.retrieveReglement(reglementId);

        // Then
        assert result != null;
    }

    @Test
    void testRetrieveReglementByFacture() {
        // Given
        Long factureId = 1L;
        List<Reglement> reglements = new ArrayList<>();
        reglements.add(new Reglement());
        when(reglementRepository.retrieveReglementByFacture(factureId)).thenReturn(reglements);

        // When
        List<Reglement> result = reglementService.retrieveReglementByFacture(factureId);

        // Then
        assert !result.isEmpty();
        assert result.size() == 1;
    }

    @Test
    void testGetChiffreAffaireEntreDeuxDate() {
        // Given
        Date startDate = new Date();
        Date endDate = new Date();
        when(reglementRepository.getChiffreAffaireEntreDeuxDate(startDate, endDate)).thenReturn(100f);

        // When
        float result = reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate);

        // Then
        assert result == 100f;
    }
}
