package com.monefy.app.services.impl;

import com.monefy.app.entities.EdsAccount;
import com.monefy.app.entities.EdsCategory;
import com.monefy.app.entities.EdsTransaction;
import com.monefy.app.items.TransactionItem;
import com.monefy.app.repos.AccountRepo;
import com.monefy.app.repos.CategoryRepo;
import com.monefy.app.repos.TransactionRepo;
import com.monefy.app.services.TransactionService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepo transactionRepo;
    private final CategoryRepo categoryRepo;
    private final AccountRepo accountRepo;

    public TransactionServiceImpl(TransactionRepo transactionRepo, CategoryRepo categoryRepo, AccountRepo accountRepo) {
        this.transactionRepo = transactionRepo;
        this.categoryRepo = categoryRepo;
        this.accountRepo = accountRepo;
    }

    public List<EdsTransaction> findAll() {
        return transactionRepo.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    public TransactionItem save(TransactionItem f) {
        EdsCategory cat = categoryRepo.findById(f.getCategoryID()).orElseThrow();
        EdsAccount acc = accountRepo.findById(f.getAccountID()).orElseThrow();

        EdsTransaction tx = new EdsTransaction();
        tx.setDate(f.getDate());
        tx.setAmount(f.getAmount());
        tx.setNote(f.getNote());
        tx.setCategory(cat);
        tx.setAccount(acc);

        transactionRepo.save(tx);
        return f;
    }

    public List<TransactionItem> getAllByUser(Integer accountID) {
        List<TransactionItem> transactionItems = new ArrayList<>();
        List<EdsTransaction> byUserObjectID = transactionRepo.findByAccount_User_ObjectID(accountID);
        for (EdsTransaction edsTransaction : byUserObjectID) {
            transactionItems.add(edsTransaction.toDto());
        }
        return transactionItems;
    }

    public void delete(Integer id) {
        transactionRepo.deleteById(id);
    }
}
