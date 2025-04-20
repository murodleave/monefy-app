package com.monefy.app.controllers;

import com.monefy.app.services.TransactionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class DashboardController {

    private final TransactionService txService;  // ваш сервис для транзакций

    public DashboardController(TransactionService txService) {
        this.txService = txService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal UserDetails user) {
        // Здесь вы получите реальные данные из базы:
        // BigDecimal balance = txService.getTotalBalance(user.getUsername());
        // BigDecimal income = txService.getIncomeThisMonth(user.getUsername());
        // BigDecimal expense = txService.getExpenseThisMonth(user.getUsername());
        // Map<String, BigDecimal> byCategory = txService.getExpenseByCategory(user.getUsername());

        // Для демо — захардкодим
        model.addAttribute("balance", "1 234 567,89 ₸");
        model.addAttribute("income", "345 000,00 ₸");
        model.addAttribute("expense", "123 456,78 ₸");
        model.addAttribute("byCategory", Map.of(
                "Food", 40000,
                "Transport", 20000,
                "Shopping", 30000,
                "Bills", 33456.78
        ));


        return "dashboard";
    }
}