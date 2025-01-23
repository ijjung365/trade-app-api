package org.jung.groupby.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.jung.groupby.domain.QStock;
import org.jung.groupby.domain.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class StockSearchImpl extends QuerydslRepositorySupport implements StockSearch {
    public StockSearchImpl() {
        super(Stock.class);
    }

    @Override
    public List<Stock> search(String keyword, Pageable pageable) {
        QStock stock = QStock.stock;
        JPQLQuery<Stock> query = from(stock);

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(keyword != null) {
            booleanBuilder.or(stock.name.contains(keyword));
            booleanBuilder.or(stock.symbol.contains(keyword));
        }
        query.where(booleanBuilder);

        this.getQuerydsl().applyPagination(pageable, query);
        return query.fetch();
    }
}
