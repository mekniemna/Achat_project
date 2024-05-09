package tn.esprit.rh.achat;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.ReglementRepository;
import tn.esprit.rh.achat.services.ReglementServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        assert !result.isEmpty();
        assertEquals(2, result.size());
    }

    @Test
    void testAddReglement() {
        // Given
        Reglement reglement = new Reglement();

        // When
        Reglement result = reglementService.addReglement(reglement);

        // Then
        assert result != null;
        Assertions.assertEquals(reglement,result);
    }

    @Test
    void testRetrieveReglement() {
        // Given
        Long reglementId = 1L;
        Reglement reglement = new Reglement();
        reglement.setIdReglement(reglementId);
        when(reglementRepository.findById(reglementId)).thenReturn(Optional.of(reglement));

        // When
        Reglement result = reglementService.retrieveReglement(reglementId);

        // Then
        assert result != null;
        Assertions.assertEquals(result,reglement);
    }

    @Test
    void testRetrieveReglementByFacture() {
        // Given
        Long factureId = 1L;
        List<Reglement> reglements = new ArrayList<>();
        Reglement reglement = new Reglement();
        reglement.setIdReglement(1L);
        Facture  facture = new Facture();
        facture.setIdFacture(factureId);
        reglement.setFacture(facture);
        reglements.add(reglement);
        when(reglementRepository.retrieveReglementByFacture(factureId)).thenReturn(reglements);

        // When
        List<Reglement> result = reglementService.retrieveReglementByFacture(factureId);

        // Then
        assert !result.isEmpty();
        assert result.size() == 1;
        Assertions.assertEquals(reglements,result);
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
        Assertions.assertEquals(result,100f);
    }
}
