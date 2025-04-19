package com.monefy.app.controllers;

import com.monefy.app.items.CategoryItem;
import com.monefy.app.items.TransactionItem;
import com.monefy.app.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PreAuthorize("hasAuthority('CATEGORY_WRITE')")
    @PostMapping
    public ResponseEntity<TransactionItem> create(@RequestBody TransactionItem dto) {
        return ResponseEntity.ok(transactionService.create(dto));
    }

    @PreAuthorize("hasAuthority('TRANSACTION_READ')")
    @GetMapping
    public ResponseEntity<List<TransactionItem>> getAll(@RequestParam Integer userId) {
        return ResponseEntity.ok(transactionService.getAllByUser(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        transactionService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
