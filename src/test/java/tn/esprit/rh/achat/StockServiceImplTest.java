package tn.esprit.rh.achat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class StockServiceImplTest {

    @Mock
    StockRepository stockRepository;

    @InjectMocks
    StockServiceImpl stockService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveAllStocks() {
        // Given
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock());
        stocks.add(new Stock());
        when(stockRepository.findAll()).thenReturn(stocks);

        // When
        List<Stock> result = stockService.retrieveAllStocks();

        // Then
        assert !result.isEmpty();
        assert result.size() == 2;
    }

    @Test
    void testAddStock() {
        // Given
        Stock stock = new Stock();

        // When
        when(stockRepository.save(stock)).thenReturn(stock);
        Stock result = stockService.addStock(stock);

        // Then
        assert result != null;
    }

    @Test
    void testDeleteStock() {
        // Given
        Long stockId = 1L;

        // When
        stockService.deleteStock(stockId);

        // Then
        verify(stockRepository, times(1)).deleteById(stockId);
    }

    @Test
    void testUpdateStock() {
        // Given
        Stock stock = new Stock();

        // When
        when(stockRepository.save(stock)).thenReturn(stock);
        Stock result = stockService.updateStock(stock);

        // Then
        assert result != null;
    }

    @Test
    void testRetrieveStock() {
        // Given
        Long stockId = 1L;
        Stock stock = new Stock();
        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));

        // When
        Stock result = stockService.retrieveStock(stockId);

        // Then
        assert result != null;
    }

    @Test
    void testRetrieveStatusStock() {
        // Write appropriate test cases if possible, considering the method's behavior and dependencies.
    }
}
