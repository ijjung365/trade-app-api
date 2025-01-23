package org.jung.groupby.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.jung.groupby.domain.QStockPriceHistory;
import org.jung.groupby.domain.Stock;
import org.jung.groupby.domain.StockPriceHistory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Log4j2
public class StockPriceHistorySearchImpl extends QuerydslRepositorySupport implements StockPriceHistorySearch {
    public StockPriceHistorySearchImpl() {
        super(StockPriceHistory.class);
    }

    @Override
    public List<StockPriceHistory> search(Stock stock, String interval, LocalDateTime from, LocalDateTime to) {
        QStockPriceHistory stockPriceHistory = QStockPriceHistory.stockPriceHistory;
        JPQLQuery<StockPriceHistory> query = from(stockPriceHistory);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if(stock != null) {
            booleanBuilder.and(stockPriceHistory.stock.eq(stock));
        }

        //interval 계산
        if (interval != null && !interval.isEmpty()) {
            switch (interval) {
                case "1hr":
                    break;
                case "3hr":
                    booleanBuilder.and(
                            Expressions.numberTemplate(Integer.class, "HOUR({0})", stockPriceHistory.dateTime)
                                    .mod(3).eq(0)
                    );
                    break;
                case "6hr":
                    booleanBuilder.and(
                            Expressions.numberTemplate(Integer.class, "HOUR({0})", stockPriceHistory.dateTime)
                                    .mod(6).eq(0)
                    );
                    break;
                case "12hr":
                    booleanBuilder.and(
                            Expressions.numberTemplate(Integer.class, "HOUR({0})", stockPriceHistory.dateTime)
                                    .mod(12).eq(0)
                    );
                    break;
                case "24hr":
                    booleanBuilder.and(
                            Expressions.numberTemplate(Integer.class, "HOUR({0})", stockPriceHistory.dateTime)
                                    .eq(0)
                    );
                    break;
                default:
                    throw new IllegalArgumentException("Invalid interval: " + interval);
            }
        }
        if(from != null) {
            booleanBuilder.and(stockPriceHistory.dateTime.after(from));
        }
        if(to != null) {
            booleanBuilder.and(stockPriceHistory.dateTime.before(to));
        }
        query.where(booleanBuilder);


        return query.fetch();
    }

}
