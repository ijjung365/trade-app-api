package org.jung.groupby.service;

import lombok.RequiredArgsConstructor;
import org.jung.groupby.domain.Stock;
import org.jung.groupby.domain.StockPriceHistory;
import org.jung.groupby.dto.StockPriceHistoryDTO;
import org.jung.groupby.repository.StockPriceHistoryRepository;
import org.jung.groupby.repository.StockRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockPriceHistoryServiceImpl implements StockPriceHistoryService {
    private final StockRepository stockRepository;
    private final StockPriceHistoryRepository stockPriceHistoryRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<StockPriceHistory> getStockPriceHistory(String symbol, String interval, LocalDateTime from, LocalDateTime to) {
        Stock stock = stockRepository.findBySymbol(symbol).orElseThrow();

        return stockPriceHistoryRepository.search(stock, interval, from, to);
    }

    @Override
    public void saveStockPriceHistory(String symbol, StockPriceHistoryDTO stockPriceHistoryDTO) {
        Stock stock = stockRepository.findBySymbol(symbol).orElseThrow();
        stockPriceHistoryDTO.setStock(stock);
        StockPriceHistory stockPriceHistory = modelMapper.map(stockPriceHistoryDTO, StockPriceHistory.class);
        stockPriceHistoryRepository.save(stockPriceHistory);
    }

    @Override
    public void updateStockPriceHistory(StockPriceHistoryDTO stockPriceHistoryDTO) {
        Stock stock = stockRepository.findById(stockPriceHistoryDTO.getStockPriceHistoryId()).orElseThrow();
        stockPriceHistoryDTO.setStock(stock);
        stockPriceHistoryRepository.save(modelMapper.map(stockPriceHistoryDTO, StockPriceHistory.class));
    }

    @Override
    public void deleteStockPriceHistory(Long id) {
        stockPriceHistoryRepository.deleteById(id);
    }
}
