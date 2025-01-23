package org.jung.groupby.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jung.groupby.domain.Member;
import org.jung.groupby.domain.Stock;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoldDTO {
    private Long holdId;
    private Stock stock;
    private Member member;
    private int amount;
}
