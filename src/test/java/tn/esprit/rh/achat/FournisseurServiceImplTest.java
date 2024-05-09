package tn.esprit.rh.achat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.DetailFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.DetailFournisseurRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;

import java.util.*;

import static org.mockito.Mockito.*;

class FournisseurServiceImplTest {

    @Mock
    FournisseurRepository fournisseurRepository;

    @Mock
    DetailFournisseurRepository detailFournisseurRepository;

    @Mock
    SecteurActiviteRepository secteurActiviteRepository;

    @InjectMocks
    FournisseurServiceImpl fournisseurService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveAllFournisseurs() {
        // Given
        List<Fournisseur> fournisseurs = new ArrayList<>();
        fournisseurs.add(new Fournisseur());
        fournisseurs.add(new Fournisseur());
        when(fournisseurRepository.findAll()).thenReturn(fournisseurs);

        // When
        List<Fournisseur> result = fournisseurService.retrieveAllFournisseurs();

        // Then
        assert !result.isEmpty();
        assert result.size() == 2;
        Assertions.assertEquals(fournisseurs,result);
    }

    @Test
    void testAddFournisseur() {
        // Given
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setIdFournisseur(1L);

        // When
        fournisseurService.addFournisseur(fournisseur);

        // Then
        verify(fournisseurRepository, times(1)).save(fournisseur);
    }

    @Test
    void testUpdateFournisseur() {
        // Given
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setIdFournisseur(1L);
        fournisseur.setCode("S11");
        fournisseur.setDetailFournisseur(new DetailFournisseur());
        when(detailFournisseurRepository.save(any(DetailFournisseur.class))).thenReturn(fournisseur.getDetailFournisseur());

        // When
        Fournisseur result =fournisseurService.updateFournisseur(fournisseur);

        // Then
        verify(fournisseurRepository, times(1)).save(fournisseur);
        Assertions.assertEquals(fournisseur,result);
    }

    @Test
    void testDeleteFournisseur() {
        // Given
        Long fournisseurId = 1L;

        // When
        fournisseurService.deleteFournisseur(fournisseurId);

        // Then
        verify(fournisseurRepository, times(1)).deleteById(fournisseurId);
    }

    @Test
    void testRetrieveFournisseur() {
        // Given

        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setIdFournisseur(1L);
        when(fournisseurRepository.findById(1L)).thenReturn(Optional.of(fournisseur));

        // When
        Fournisseur result = fournisseurService.retrieveFournisseur(1L);

        // Then
        assert result != null;
        Assertions.assertEquals(fournisseur,result);
    }

    @Test
    void testAssignSecteurActiviteToFournisseur() {
        // Given
        Long idSecteurActivite = 1L;
        Long idFournisseur = 1L;
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setIdFournisseur(idFournisseur);
        SecteurActivite secteurActivite = new SecteurActivite();
        secteurActivite.setIdSecteurActivite(idSecteurActivite);
        // Initialize the secteurActivites set if it's null
        if (fournisseur.getSecteurActivites() == null) {
            fournisseur.setSecteurActivites(new HashSet<>());
        }

        fournisseur.getSecteurActivites().add(secteurActivite);
        fournisseurRepository.save(fournisseur);
        when(fournisseurRepository.findById(1L)).thenReturn(Optional.of(fournisseur));
        when(secteurActiviteRepository.findById(1L)).thenReturn(Optional.of(secteurActivite));

        // When
        fournisseurService.assignSecteurActiviteToFournisseur(idSecteurActivite, idFournisseur);

        // Then
        assert fournisseur.getSecteurActivites().contains(secteurActivite);
        Assertions.assertTrue(fournisseur.getSecteurActivites().contains(secteurActivite));
    }
}
