package org.jung.groupby.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class StockPriceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long StockPriceHistoryId;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    @ToString.Exclude
    private Stock stock;

    private LocalDateTime dateTime;
    private double price;
    private Long volume;

}
