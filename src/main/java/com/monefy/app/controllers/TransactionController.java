package com.monefy.app.controllers;

import com.monefy.app.items.TransactionItem;
import com.monefy.app.services.CategoryService;
import com.monefy.app.services.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService service;
    private final CategoryService catService;

    public TransactionController(TransactionService s, CategoryService c) {
        this.service = s; this.catService = c;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("transactions", service.getAllByUser(null));
        return "transaction/list";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("txForm", new TransactionItem());
        model.addAttribute("categories", catService.getAllByUser(null));
        return "transaction/form";
    }

    @PostMapping
    public String save(TransactionItem f, BindingResult br) {
        if (br.hasErrors()) return "transaction/form";
        service.create(f);
        return "redirect:/transactions";
    }
}