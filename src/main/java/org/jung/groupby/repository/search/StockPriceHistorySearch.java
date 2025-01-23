package org.jung.groupby.repository.search;

import org.jung.groupby.domain.Stock;
import org.jung.groupby.domain.StockPriceHistory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface StockPriceHistorySearch {
    List<StockPriceHistory> search(Stock stock, String interval, LocalDateTime from, LocalDateTime to);
}
