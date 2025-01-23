package org.jung.groupby.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jung.groupby.domain.Stock;
import org.jung.groupby.domain.StockPriceHistory;
import org.jung.groupby.dto.StockDTO;
import org.jung.groupby.repository.StockPriceHistoryRepository;
import org.jung.groupby.repository.StockRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<Stock> searchStock(String keyword, Pageable pageable) {
        return stockRepository.search(keyword, pageable);
    }

    @Override
    public void saveStocks(List<StockDTO> stocks) {
        List<Stock> collect = stocks.stream().map(s -> modelMapper.map(s, Stock.class)).collect(Collectors.toList());
        stockRepository.saveAll(collect);
    }

    @Override
    public void removeStock(String symbol) {
        stockRepository.deleteBySymbol(symbol);
    }
}
