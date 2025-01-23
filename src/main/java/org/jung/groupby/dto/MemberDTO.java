package org.jung.groupby.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jung.groupby.domain.Hold;
import org.jung.groupby.domain.Transaction;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private Long memberId;

    private String username;
    private String password;
    private double money;
}
