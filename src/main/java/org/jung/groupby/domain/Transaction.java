package org.jung.groupby.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "stock_price_history_id")
    private StockPriceHistory stockPriceHistory;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType; // BUY or SELL

    private Long amount;
    private LocalDateTime dateTime;

}