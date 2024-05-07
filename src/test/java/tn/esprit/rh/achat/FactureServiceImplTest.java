package tn.esprit.rh.achat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.services.FactureServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FactureServiceImplTest {

    @Autowired
    private FactureServiceImpl factureService;

    @Test
    public void testRetrieveAllFactures() {
        List<Facture> factures = factureService.retrieveAllFactures();
        assertNotNull(factures);
        assertFalse(factures.isEmpty());
    }

    @Test
    public void testAddFacture() {
        // Create a new Facture object
        Facture facture = new Facture();
        // Add necessary attributes to the facture object

        // Add the facture
        Facture savedFacture = factureService.addFacture(facture);
        assertNotNull(savedFacture);
        // Check if the savedFacture is the same as the facture added
        // You can assert based on IDs or other unique identifiers
        // Example: assertEquals(facture.getId(), savedFacture.getId());
    }

    // Add more test methods for other functionalities of FactureServiceImpl
}
