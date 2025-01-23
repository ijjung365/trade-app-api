package org.jung.groupby.controller;


import lombok.RequiredArgsConstructor;
import org.jung.groupby.domain.TransactionType;
import org.jung.groupby.dto.TransactionDTO;
import org.jung.groupby.dto.TransactionRequestDTO;
import org.jung.groupby.service.TransactionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/buy")
    public ResponseEntity<Void> buy(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        transactionService.makeBuy(transactionRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sell")
    public ResponseEntity<Void> sell(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        transactionService.makeSell(transactionRequestDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<TransactionDTO>> getTransaction(@PathVariable String username,
                                                         @RequestParam(defaultValue = "1") int page,
                                                         @RequestParam(defaultValue = "30") int size, TransactionType type) {
        Pageable pageable = PageRequest.of(page - 1, size);
        List<TransactionDTO> transactions = transactionService.getTransactions(username,type,pageable);
        return ResponseEntity.ok().body(transactions);
    }
}
