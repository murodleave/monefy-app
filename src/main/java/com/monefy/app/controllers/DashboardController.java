package com.monefy.app.controllers;

import com.monefy.app.entities.EdsUser;
import com.monefy.app.services.TransactionService;
import com.monefy.app.services.UserService;
import com.monefy.app.services.impl.UserServiceImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DashboardController {

    private final TransactionService txService;
    private final UserService userService;

    public DashboardController(TransactionService txService, UserService userService) {
        this.txService = txService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails == null) {
            return "redirect:/login";
        }

        EdsUser user = userService.getUser(userDetails.getUsername());
        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("balance", txService.getBalance(user));
        model.addAttribute("income", txService.getTotalIncomeAmount(user));
        model.addAttribute("expense", txService.getTotalExpenseAmount(user));
        model.addAttribute("incomesByCategory", txService.getIncomesByCategories(user));
        model.addAttribute("expensesByCategory", txService.getExpensesByCategories(user));
        return "dashboard";
    }
}