package com.monefy.app.repos;

import com.monefy.app.dtos.TransactionForm;
import com.monefy.app.entities.EdsTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface TransactionRepo extends JpaRepository<EdsTransaction, Integer> {

    @Query("SELECT SUM(t.amount) FROM transaction t WHERE t.user.objectID = :userId and t.category.type = 'INCOME'")
    BigDecimal getTotalIncomeAmount(@Param("userId") Integer userId);

    @Query("SELECT SUM(t.amount) FROM transaction t WHERE t.user.objectID = :userId and t.category.type = 'EXPENSE'")
    BigDecimal getTotalExpenseAmount(@Param("userId") Integer userId);

    @Query("SELECT SUM(t.amount) FROM transaction t WHERE t.user.objectID = :userId and t.category.type = 'INCOME' AND t.date BETWEEN :start AND :end")
    BigDecimal getIncomeBetween(@Param("userId") Integer userId, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT SUM(t.amount) FROM transaction t WHERE t.user.objectID = :userId and t.category.type = 'EXPENSE' AND t.date BETWEEN :start AND :end")
    BigDecimal getExpenseBetween(@Param("userId") Integer userId, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("""
                SELECT t.category.name, SUM(t.amount)
                FROM transaction t
                WHERE t.category.type = 'INCOME' AND t.user.objectID = :userId
                GROUP BY t.category.name
            """)
    Map<String, BigDecimal> getIncomeByCategories(@Param("userId") Integer userId);

    @Query("""
                SELECT t.category.name, SUM(t.amount)
                FROM transaction t
                WHERE t.category.type = 'EXPENSE' AND t.user.objectID = :userId
                GROUP BY t.category.name
            """)
    Map<String, BigDecimal> getExpenseByCategories(@Param("userId") Integer userId);

    @Query("""
                SELECT t
                FROM transaction t
                WHERE t.user.objectID = :userId
                order by t.date desc
            """)
    List<TransactionForm> getAllTransactionList(@Param("userId") Integer userId);

}

