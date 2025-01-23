package org.jung.groupby.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jung.groupby.domain.Stock;
import org.jung.groupby.dto.StockDTO;
import org.jung.groupby.service.StockService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/stock")
public class StockController {
    private final StockService stockService;

    @GetMapping("/search")
    public ResponseEntity<List<Stock>> searchStock(@RequestParam(defaultValue = "1") int page,
                                                   @RequestParam(defaultValue = "30") int size, String keyword) {
        Pageable pageable = PageRequest.of(page-1, size);
        List<Stock> stocks = stockService.searchStock(keyword, pageable);
        return ResponseEntity.ok(stocks);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addStock(@RequestBody List<StockDTO> stocks) {
        stockService.saveStocks(stocks);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remove/{symbol}")
    public ResponseEntity<Void> removeStock(@PathVariable String symbol) {
        stockService.removeStock(symbol);
        return ResponseEntity.ok().build();
    }


}
