package org.jung.groupby.repository;

import org.jung.groupby.domain.Hold;
import org.jung.groupby.domain.Member;
import org.jung.groupby.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HoldRepository extends JpaRepository<Hold,Long> {
    Optional<Hold> findByMemberAndStock(Member member, Stock stock);
    List<Hold> findByMember(Member member);
}
