package com.monefy.app.services;

import com.monefy.app.dtos.TransactionForm;
import com.monefy.app.entities.EdsUser;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface TransactionService {

    TransactionForm create(TransactionForm f, EdsUser user);

    List<TransactionForm> getTransactionList(EdsUser user);

    BigDecimal getTotalIncomeAmount(EdsUser user);

    BigDecimal getTotalExpenseAmount(EdsUser user);

    BigDecimal getIncomeBetween(EdsUser user, LocalDateTime start, LocalDateTime end);

    BigDecimal getExpenseBetween(EdsUser user, LocalDateTime start, LocalDateTime end);

    BigDecimal getBalance(EdsUser user);

    BigDecimal getBalance4ThisMonth(EdsUser user);

    BigDecimal getIncome4ThisMonth(EdsUser user);

    BigDecimal getExpense4ThisMonth(EdsUser user);

    Map<String, BigDecimal> getExpensesByCategories(EdsUser user);

    Map<String, BigDecimal> getIncomesByCategories(EdsUser user);

}
