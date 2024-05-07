package tn.esprit.rh.achat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.ProduitServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ProduitServiceImplTest {

    @Mock
    ProduitRepository produitRepository;

    @Mock
    StockRepository stockRepository;

    @Mock
    CategorieProduitRepository categorieProduitRepository;

    @InjectMocks
    ProduitServiceImpl produitService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveAllProduits() {
        // Given
        List<Produit> produits = new ArrayList<>();
        produits.add(new Produit());
        produits.add(new Produit());
        when(produitRepository.findAll()).thenReturn(produits);

        // When
        List<Produit> result = produitService.retrieveAllProduits();

        // Then
        assert !result.isEmpty();
        assert result.size() == 2;
    }

    @Test
    void testAddProduit() {
        // Given
        Produit produit = new Produit();

        // When
        when(produitRepository.save(produit)).thenReturn(produit);
        Produit result = produitService.addProduit(produit);

        // Then
        assert result != null;
    }

    @Test
    void testDeleteProduit() {
        // Given
        Long produitId = 1L;

        // When
        produitService.deleteProduit(produitId);

        // Then
        verify(produitRepository, times(1)).deleteById(produitId);
    }

    @Test
    void testUpdateProduit() {
        // Given
        Produit produit = new Produit();

        // When
        when(produitRepository.save(produit)).thenReturn(produit);
        Produit result = produitService.updateProduit(produit);

        // Then
        assert result != null;
    }

    @Test
    void testRetrieveProduit() {
        // Given
        Long produitId = 1L;
        Produit produit = new Produit();
        when(produitRepository.findById(produitId)).thenReturn(Optional.of(produit));

        // When
        Produit result = produitService.retrieveProduit(produitId);

        // Then
        assert result != null;
    }

    @Test
    void testAssignProduitToStock() {
        // Given
        Long idProduit = 1L;
        Long idStock = 1L;
        Produit produit = new Produit();
        Stock stock = new Stock();
        when(produitRepository.findById(idProduit)).thenReturn(Optional.of(produit));
        when(stockRepository.findById(idStock)).thenReturn(Optional.of(stock));

        // When
        produitService.assignProduitToStock(idProduit, idStock);

        // Then
        assert produit.getStock() == stock;
    }
}
