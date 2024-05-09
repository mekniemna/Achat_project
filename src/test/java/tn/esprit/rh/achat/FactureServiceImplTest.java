package tn.esprit.rh.achat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.services.FactureServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.*;
import tn.esprit.rh.achat.repositories.*;
import tn.esprit.rh.achat.services.FactureServiceImpl;
import tn.esprit.rh.achat.services.ReglementServiceImpl;

import java.util.*;

import static org.mockito.Mockito.*;

class FactureServiceImplTest {

    @Mock
    FactureRepository factureRepository;

    @Mock
    OperateurRepository operateurRepository;

    @Mock
    DetailFactureRepository detailFactureRepository;

    @Mock
    FournisseurRepository fournisseurRepository;

    @Mock
    ProduitRepository produitRepository;

    @Mock
    ReglementServiceImpl reglementService;

    @InjectMocks
    FactureServiceImpl factureService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveAllFactures() {
        // Given
        List<Facture> factures = new ArrayList<>();
        factures.add(new Facture());
        factures.add(new Facture());
        when(factureRepository.findAll()).thenReturn(factures);

        // When
        List<Facture> result = factureService.retrieveAllFactures();

        // Then
        assert !result.isEmpty();
        assert result.size() == 2;
        Assertions.assertEquals(factures,result);
    }

    @Test
    void testAddFacture() {
        // Given
        Facture facture = new Facture();
        facture.setIdFacture(1L);

        // When
        when(factureRepository.save(facture)).thenReturn(facture);
        Facture f=factureService.addFacture(facture);

        // Then
        Assertions.assertTrue(f.equals(facture));
    }

}
