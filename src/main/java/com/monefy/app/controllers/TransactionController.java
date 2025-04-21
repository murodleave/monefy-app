package com.monefy.app.controllers;

import com.monefy.app.dtos.TransactionForm;
import com.monefy.app.entities.EdsUser;
import com.monefy.app.services.CategoryService;
import com.monefy.app.services.TransactionService;
import com.monefy.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final CategoryService catService;
    private final UserService userService;

    @Autowired
    public TransactionController(TransactionService transactionService, CategoryService catService, UserService userService) {
        this.transactionService = transactionService;
        this.catService = catService;
        this.userService = userService;
    }

    @GetMapping
    public String list(Model model, @AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails == null) {
            return "redirect:/login";
        }

        EdsUser user = userService.getUser(userDetails.getUsername());
        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("transactions", transactionService.getTransactionList(user));
        return "transaction/list";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("txForm", new TransactionForm());
        model.addAttribute("categories", catService.getAllByUser(null));
        return "transaction/form";
    }

    @PostMapping
    public String save(TransactionForm tr, @AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails == null) {
            return "redirect:/login";
        }

        EdsUser user = userService.getUser(userDetails.getUsername());
        if (user == null) {
            return "redirect:/login";
        }

        transactionService.create(tr, user);
        return "redirect:/transactions";
    }
}