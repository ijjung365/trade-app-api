package org.jung.groupby.service;

import lombok.RequiredArgsConstructor;
import org.jung.groupby.domain.*;
import org.jung.groupby.dto.TransactionDTO;
import org.jung.groupby.dto.TransactionRequestDTO;
import org.jung.groupby.exception.InvalidBalanceException;
import org.jung.groupby.exception.InvalidHoldException;
import org.jung.groupby.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final HoldRepository holdRepository;
    private final MemberRepository memberRepository;
    private final TransactionRepository repository;
    private final StockRepository stockRepository;
    private final StockPriceHistoryRepository stockPriceHistoryRepository;
    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    @Override
    public void makeBuy(TransactionRequestDTO transactionRequestDTO) {
        Member member = memberRepository.findByUsername(transactionRequestDTO.getUsername()).orElseThrow();
        Stock stock = stockRepository.findBySymbol(transactionRequestDTO.getSymbol()).orElseThrow();
        StockPriceHistory stockPriceHistory = stockPriceHistoryRepository.findTopByStockOrderByDateTimeDesc(stock).orElseThrow();

        //member 잔액있는지
        double totalAmount = stockPriceHistory.getPrice() * transactionRequestDTO.getAmount();

        if(totalAmount > member.getBalance()) {
            throw new InvalidBalanceException("Not enough balance, Total amount: " + totalAmount );
        }

        member.deductBalance(totalAmount);
        memberRepository.save(member);

        // hold 관련
        Hold hold;
        try{
            hold = holdRepository.findByMemberAndStock(member, stock).orElseThrow();
        }catch(Exception ignored){
            hold = null;
        }

        if(hold == null){
            hold = Hold.builder()
                    .stock(stock)
                    .member(member)
                    .amount(transactionRequestDTO.getAmount())
                    .build();
        }else {
            hold.addAmount(transactionRequestDTO.getAmount());
        }
        holdRepository.save(hold);

        //transaction 관련
        Transaction transaction = Transaction.builder()
                .member(member)
                .stockPriceHistory(stockPriceHistory)
                .transactionType(TransactionType.BUY)
                .amount(transactionRequestDTO.getAmount())
                .dateTime(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);
    }

    @Override
    public void makeSell(TransactionRequestDTO transactionRequestDTO) {
        Member member = memberRepository.findByUsername(transactionRequestDTO.getUsername()).orElseThrow();
        Stock stock = stockRepository.findBySymbol(transactionRequestDTO.getSymbol()).orElseThrow();
        StockPriceHistory stockPriceHistory = stockPriceHistoryRepository.findTopByStockOrderByDateTimeDesc(stock).orElseThrow();
        Hold hold = holdRepository.findByMemberAndStock(member, stock).orElseThrow();

        if(transactionRequestDTO.getAmount()>hold.getAmount()){
            throw new InvalidHoldException("Not enough hold, Total SELL: " + transactionRequestDTO.getAmount());
        }

        hold.deductAmount(transactionRequestDTO.getAmount());
        holdRepository.save(hold);



        double totalAmount = stockPriceHistory.getPrice() * transactionRequestDTO.getAmount();
        member.addBalance(totalAmount);
        memberRepository.save(member);



        Transaction transaction = Transaction.builder()
                .member(member)
                .stockPriceHistory(stockPriceHistory)
                .transactionType(TransactionType.SELL)
                .amount(transactionRequestDTO.getAmount())
                .dateTime(LocalDateTime.now())
                .build();
        transactionRepository.save(transaction);

    }

    @Override
    public List<TransactionDTO> getTransactions(String username, TransactionType transactionType, Pageable pageable){
        List<Transaction> transactionList = transactionRepository.search(username, transactionType, pageable);
        return transactionList.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDTO.class)) // ModelMapper로 변환
                .collect(Collectors.toList());
    }
}
