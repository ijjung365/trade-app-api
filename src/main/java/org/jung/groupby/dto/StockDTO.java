package org.jung.groupby.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {
    private Long stockId;

    @JsonProperty("company")
    private String name;

    @JsonProperty("tickerSymbol")
    private String symbol;

}
