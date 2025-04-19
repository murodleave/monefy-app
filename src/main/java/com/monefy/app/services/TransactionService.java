package com.monefy.app.services;

import com.monefy.app.items.TransactionItem;

import java.util.List;

public interface TransactionService {

    TransactionItem create(TransactionItem dto);

    List<TransactionItem> getAllByUser(Integer userId);

    void delete(Integer id);
}
