package org.jung.groupby.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String username;
    private String password;
    private double balance;

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void addBalance(double balance) {
        this.balance += balance;
    }

    public void deductBalance(double balance) {
        this.balance -= balance;
    }

}