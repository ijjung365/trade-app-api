package org.jung.groupby.repository;

import org.jung.groupby.domain.Hold;
import org.jung.groupby.domain.Stock;
import org.jung.groupby.repository.search.StockSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock,Long>, StockSearch {
    Optional<Stock> findBySymbol(String symbol);
    void deleteBySymbol(String symbol);

}
