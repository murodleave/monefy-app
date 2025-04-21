package com.monefy.app.services.impl;

import com.monefy.app.dtos.TransactionForm;
import com.monefy.app.entities.EdsCategory;
import com.monefy.app.entities.EdsTransaction;
import com.monefy.app.entities.EdsUser;
import com.monefy.app.repos.CategoryRepo;
import com.monefy.app.repos.TransactionRepo;
import com.monefy.app.repos.UserRepo;
import com.monefy.app.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepo transactionRepo;
    private final CategoryRepo categoryRepo;
    private final UserRepo userRepo;

    @Autowired
    public TransactionServiceImpl(TransactionRepo transactionRepo, CategoryRepo categoryRepo, UserRepo userRepo) {
        this.transactionRepo = transactionRepo;
        this.categoryRepo = categoryRepo;
        this.userRepo = userRepo;
    }

    public BigDecimal getBalance4ThisMonth(EdsUser user) {
        return getIncome4ThisMonth(user).subtract(getExpense4ThisMonth(user));
    }

    public BigDecimal getIncome4ThisMonth(EdsUser user) {
        LocalDateTime start = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime end = LocalDateTime.now().withDayOfMonth(LocalDateTime.now().getMonth().length(LocalDateTime.now().toLocalDate().isLeapYear())).withHour(23).withMinute(59).withSecond(59);
        return getIncomeBetween(user, start, end);
    }

    public BigDecimal getExpense4ThisMonth(EdsUser user) {
        LocalDateTime start = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime end = LocalDateTime.now().withDayOfMonth(LocalDateTime.now().getMonth().length(LocalDateTime.now().toLocalDate().isLeapYear())).withHour(23).withMinute(59).withSecond(59);
        return getExpenseBetween(user, start, end);
    }

    public Map<String, BigDecimal> getExpensesByCategories(EdsUser user) {
        return transactionRepo.getExpenseByCategories(user.getObjectID());
    }

    public Map<String, BigDecimal> getIncomesByCategories(EdsUser user) {
        return transactionRepo.getIncomeByCategories(user.getObjectID());
    }

    @Override
    @Transactional
    public TransactionForm create(TransactionForm f, EdsUser user) {
        EdsCategory category = categoryRepo.findById(f.getCategoryId()).orElseThrow();

        EdsTransaction tx = new EdsTransaction();
        tx.setAmount(f.getAmount());
        tx.setDescription(f.getDescription());
        tx.setUser(user);
        tx.setCategory(category);
        transactionRepo.save(tx);
        return f;
    }

    @Override
    public List<TransactionForm> getTransactionList(EdsUser user) {
        return transactionRepo.getAllTransactionList(user.getObjectID());
    }

    @Override
    public BigDecimal getBalance(EdsUser user) {
        return getTotalIncomeAmount(user).subtract(getTotalExpenseAmount(user));
    }

    @Override
    public BigDecimal getTotalIncomeAmount(EdsUser user) {
        return transactionRepo.getTotalIncomeAmount(user.getObjectID());
    }

    @Override
    public BigDecimal getTotalExpenseAmount(EdsUser user) {
        return transactionRepo.getTotalExpenseAmount(user.getObjectID());
    }

    @Override
    public BigDecimal getIncomeBetween(EdsUser user, LocalDateTime start, LocalDateTime end) {
        return transactionRepo.getIncomeBetween(user.getObjectID(), start, end);
    }

    @Override
    public BigDecimal getExpenseBetween(EdsUser user, LocalDateTime start, LocalDateTime end) {
        return transactionRepo.getExpenseBetween(user.getObjectID(), start, end);
    }

}
