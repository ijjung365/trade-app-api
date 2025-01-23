package org.jung.groupby.service;

import org.jung.groupby.domain.StockPriceHistory;
import org.jung.groupby.dto.StockPriceHistoryDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface StockPriceHistoryService {
    List<StockPriceHistory> getStockPriceHistory(String symbol, String interval, LocalDateTime from, LocalDateTime to);
    void saveStockPriceHistory(String symbol, StockPriceHistoryDTO stockPriceHistoryDTO);
    void updateStockPriceHistory(StockPriceHistoryDTO stockPriceHistoryDTO);
    void deleteStockPriceHistory(Long id);
}
