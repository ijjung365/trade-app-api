package org.jung.groupby.service;

import lombok.RequiredArgsConstructor;
import org.jung.groupby.domain.Stock;
import org.jung.groupby.domain.StockPriceHistory;
import org.jung.groupby.dto.StockDTO;
import org.jung.groupby.repository.StockRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public interface StockService {

    List<Stock> searchStock(String keyword, Pageable pageable);
    void saveStocks(List<StockDTO> stocks);
    void removeStock(String symbol);
}
