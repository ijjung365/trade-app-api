package org.jung.groupby.repository;

import org.jung.groupby.domain.Stock;
import org.jung.groupby.domain.Transaction;
import org.jung.groupby.repository.search.StockSearch;
import org.jung.groupby.repository.search.TransactionSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long>, TransactionSearch {
}
