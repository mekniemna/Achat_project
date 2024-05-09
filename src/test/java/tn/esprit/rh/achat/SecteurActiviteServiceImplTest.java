package tn.esprit.rh.achat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.SecteurActiviteServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class SecteurActiviteServiceImplTest {

    @Mock
    SecteurActiviteRepository secteurActiviteRepository;

    @InjectMocks
    SecteurActiviteServiceImpl secteurActiviteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveAllSecteurActivite() {
        // Given
        List<SecteurActivite> secteursActivite = new ArrayList<>();
        secteursActivite.add(new SecteurActivite());
        secteursActivite.add(new SecteurActivite());
        when(secteurActiviteRepository.findAll()).thenReturn(secteursActivite);

        // When
        List<SecteurActivite> result = secteurActiviteService.retrieveAllSecteurActivite();

        // Then
        assert !result.isEmpty();
        assert result.size() == 2;
        Assertions.assertEquals(secteursActivite,result);
    }

    @Test
    void testAddSecteurActivite() {
        // Given
        SecteurActivite secteurActivite = new SecteurActivite();

        // When
        SecteurActivite result = secteurActiviteService.addSecteurActivite(secteurActivite);

        // Then
        assert result != null;
        Assertions.assertEquals(secteurActivite,result);
    }

    @Test
    void testDeleteSecteurActivite() {
        // Given
        Long secteurActiviteId = 1L;
        SecteurActivite secteurActivite = new SecteurActivite();
        secteurActivite.setIdSecteurActivite(secteurActiviteId);


        // When
        secteurActiviteService.deleteSecteurActivite(secteurActiviteId);

        // Then
        verify(secteurActiviteRepository, times(1)).deleteById(secteurActiviteId);
    }

    @Test
    void testUpdateSecteurActivite() {
        // Given
        SecteurActivite secteurActivite = new SecteurActivite();
        secteurActivite.setIdSecteurActivite(1L);
        secteurActivite.setCodeSecteurActivite("test_update");


        // When
        SecteurActivite result = secteurActiviteService.updateSecteurActivite(secteurActivite);

        // Then
        assert result != null;
        Assertions.assertEquals(secteurActivite,result);
    }

    @Test
    void testRetrieveSecteurActivite() {
        // Given
        Long secteurActiviteId = 1L;
        SecteurActivite secteurActivite = new SecteurActivite();
        secteurActivite.setIdSecteurActivite(secteurActiviteId);
        when(secteurActiviteRepository.findById(secteurActiviteId)).thenReturn(Optional.of(secteurActivite));

        // When
        SecteurActivite result = secteurActiviteService.retrieveSecteurActivite(1L);

        // Then
        assert result != null;
        Assertions.assertEquals(secteurActivite,result);
    }
}

