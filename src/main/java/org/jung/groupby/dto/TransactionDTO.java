package org.jung.groupby.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jung.groupby.domain.Member;
import org.jung.groupby.domain.StockPriceHistory;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Long transactionId;
    private Member member;
    private StockPriceHistory stockPriceHistory;
    private String transactionType; // BUY or SELL
    private Long amount;
    private LocalDateTime dateTime;
}
