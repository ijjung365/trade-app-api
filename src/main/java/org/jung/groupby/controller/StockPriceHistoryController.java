package org.jung.groupby.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jung.groupby.domain.StockPriceHistory;
import org.jung.groupby.dto.StockPriceHistoryDTO;
import org.jung.groupby.service.StockPriceHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stock")
public class StockPriceHistoryController {

    private final StockPriceHistoryService stockPriceHistoryService;

    @GetMapping("/{symbol}")
    public ResponseEntity<List<StockPriceHistory>> getStock(@PathVariable String symbol, String interval, LocalDateTime from, LocalDateTime to) {
        List<StockPriceHistory> stockPriceHistories = stockPriceHistoryService.getStockPriceHistory(symbol, interval, from, to);
        return ResponseEntity.ok(stockPriceHistories);
    }

    @PostMapping("/{symbol}")
    public ResponseEntity<Void> addStock(@PathVariable String symbol, @RequestBody StockPriceHistoryDTO stockPriceHistoryDTO) {
        stockPriceHistoryService.saveStockPriceHistory(symbol, stockPriceHistoryDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/stockPriceHistory")
    public ResponseEntity<Void> updateStockPriceHistory(@RequestBody StockPriceHistoryDTO stockPriceHistoryDTO) {
        stockPriceHistoryService.updateStockPriceHistory(stockPriceHistoryDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockPriceHistoryService.deleteStockPriceHistory(id);
        return ResponseEntity.ok().build();
    }


}
