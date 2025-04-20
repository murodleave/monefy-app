package com.monefy.app.services;

import com.monefy.app.entities.EdsTransaction;
import com.monefy.app.entities.EdsUser;
import com.monefy.app.items.TransactionItem;

import java.util.List;

public interface TransactionService {

    TransactionItem save(TransactionItem item);

    List<EdsTransaction> findAll();

    void delete(Integer id);
}
