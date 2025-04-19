package com.monefy.app.services.impl;

import com.monefy.app.entities.EdsCategory;
import com.monefy.app.entities.EdsTransaction;
import com.monefy.app.entities.EdsUser;
import com.monefy.app.items.TransactionItem;
import com.monefy.app.repos.CategoryRepo;
import com.monefy.app.repos.TransactionRepo;
import com.monefy.app.repos.UserRepo;
import com.monefy.app.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private UserRepo userRepo;

    public TransactionItem create(TransactionItem item) {
        EdsTransaction transaction = new EdsTransaction();
        transaction.setAmount(item.getAmount());
        transaction.setDescription(item.getDescription());
        transaction.setDate(item.getDate());

        EdsCategory category = categoryRepo.findById(item.getCategoryID()).orElseThrow();
        EdsUser user = userRepo.findById(item.getUserID()).orElseThrow();

        transaction.setCategory(category);
        transaction.setUser(user);

        return transactionRepo.save(transaction).toDto();
    }

    public List<TransactionItem> getAllByUser(Integer userId) {
        List<TransactionItem> transactionItems = new ArrayList<>();
        List<EdsTransaction> byUserObjectID = transactionRepo.findByUser_ObjectID(userId);
        for (EdsTransaction edsTransaction : byUserObjectID) {
            transactionItems.add(edsTransaction.toDto());
        }
        return transactionItems;
    }

    public void delete(Integer id) {
        transactionRepo.deleteById(id);
    }
}
