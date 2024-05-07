package tn.esprit.rh.achat;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategorieProduitServiceImplTest {

    @InjectMocks
    private CategorieProduitServiceImpl categorieProduitService;

    @Test
    public void testRetrieveAllCategorieProduits() {
        List<CategorieProduit> categorieProduits = categorieProduitService.retrieveAllCategorieProduits();
        assertNotNull(categorieProduits);
        assertFalse(categorieProduits.isEmpty());
    }

    @Test
    public void testAddCategorieProduit() {
        // Create a new CategorieProduit object
        CategorieProduit categorieProduit = new CategorieProduit();
        categorieProduit.setIdCategorieProduit(1L);
        // Add necessary attributes to the categorieProduit object

        // Add the categorieProduit
        CategorieProduit savedCategorieProduit = categorieProduitService.addCategorieProduit(categorieProduit);
        assertNotNull(savedCategorieProduit);
        assertEquals(categorieProduit.getIdCategorieProduit(), savedCategorieProduit.getIdCategorieProduit());
    }

    // Add more test methods for other functionalities of CategorieProduitServiceImpl
}

