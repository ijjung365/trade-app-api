package org.jung.groupby.repository.search;

import org.jung.groupby.domain.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StockSearch {
    List<Stock> search(String keyword, Pageable pageable);
}
