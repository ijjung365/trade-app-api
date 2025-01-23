package org.jung.groupby.repository;

import org.jung.groupby.domain.Stock;
import org.jung.groupby.domain.StockPriceHistory;
import org.jung.groupby.repository.search.StockPriceHistorySearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockPriceHistoryRepository extends JpaRepository<StockPriceHistory,Long>, StockPriceHistorySearch {
    Optional<StockPriceHistory> findTopByStockOrderByDateTimeDesc(Stock stock);
}
