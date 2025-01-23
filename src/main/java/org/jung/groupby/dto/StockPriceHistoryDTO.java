package org.jung.groupby.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jung.groupby.domain.Stock;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockPriceHistoryDTO {

    private Long StockPriceHistoryId;
    private Stock stock;
    private LocalDateTime dateTime;
    private double price;
    private Long volume;
}
