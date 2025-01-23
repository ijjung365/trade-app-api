package org.jung.groupby.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDTO {
    private String username;
    private String symbol;
    private Long amount;
}
