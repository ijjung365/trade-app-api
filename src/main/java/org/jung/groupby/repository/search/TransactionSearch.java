package org.jung.groupby.repository.search;

import org.jung.groupby.domain.Transaction;
import org.jung.groupby.domain.TransactionType;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionSearch {
    List<Transaction> search(String username, TransactionType transactionType, Pageable pageable);
}
