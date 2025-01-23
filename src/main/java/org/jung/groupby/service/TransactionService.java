package org.jung.groupby.service;

import org.jung.groupby.domain.TransactionType;
import org.jung.groupby.dto.TransactionDTO;
import org.jung.groupby.dto.TransactionRequestDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {
    public void makeBuy(TransactionRequestDTO transactionRequestDTO);
    public void makeSell(TransactionRequestDTO transactionRequestDTO);
    public List<TransactionDTO> getTransactions(String username, TransactionType transactionType, Pageable pageable);

}
