package org.jung.groupby.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.jung.groupby.domain.QTransaction;
import org.jung.groupby.domain.Transaction;
import org.jung.groupby.domain.TransactionType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class TransactionSearchImpl extends QuerydslRepositorySupport implements TransactionSearch {

    public TransactionSearchImpl() {
        super(Transaction.class);
    }

    @Override
    public List<Transaction> search(String username, TransactionType transactionType, Pageable pageable) {
        QTransaction transaction = QTransaction.transaction;
        JPQLQuery<Transaction> query = from(transaction);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(transactionType == TransactionType.BUY){
            booleanBuilder.and(transaction.transactionType.eq(TransactionType.BUY));
        } else if(transactionType == TransactionType.SELL){
            booleanBuilder.and(transaction.transactionType.eq(TransactionType.SELL));
        }
        booleanBuilder.and(transaction.member.username.eq(username));

        query.where(booleanBuilder);

        this.getQuerydsl().applyPagination(pageable, query);
        return query.fetch();
    }
}
