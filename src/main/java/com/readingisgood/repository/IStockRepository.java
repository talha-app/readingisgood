package com.readingisgood.repository;

import com.readingisgood.entity.Book;
import com.readingisgood.entity.Stock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface IStockRepository extends CrudRepository<Stock, Long> {

    @Modifying
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Query("update Stock s set s.count=(s.count - 1), s.lastUpdatedUser=:lastUpdatedUser, s.lastUpdateDate=:lastUpdateDate WHERE s.book=:book")
    void updateStockAfterOrderPlacing(@Param("book") Book book,
                                      @Param("lastUpdatedUser") String lastUpdatedUser,
                                      @Param("lastUpdateDate") LocalDateTime lastUpdateDate);

    @Modifying
    @Transactional
    @Query("update Stock s set s.count=:count, s.lastUpdatedUser=:lastUpdatedUser, s.lastUpdateDate=:lastUpdateDate WHERE s.book=:book")
    void updateStock(@Param("book") Book book,
                     @Param("count") int count,
                     @Param("lastUpdatedUser") String lastUpdatedUser,
                     @Param("lastUpdateDate") LocalDateTime lastUpdateDate);

}
