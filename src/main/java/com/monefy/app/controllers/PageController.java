package com.monefy.app.controllers;

import com.monefy.app.items.TransactionItem;
import com.monefy.app.services.CategoryService;
import com.monefy.app.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {

    private final TransactionService txService;
    private final CategoryService catService;

    @Autowired
    public PageController(TransactionService txService, CategoryService catService) {
        this.txService = txService;
        this.catService = catService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/transactions")
    public String trxList(Model model) {
        model.addAttribute("transactions", txService.getAllByUser(null));
        return "transaction/list";
    }

    @GetMapping("/transactions/new")
    public String newTrx(Model model) {
        model.addAttribute("txForm", new TransactionItem());
        model.addAttribute("categories", catService.getAllByUser(null));
        return "transaction/form";
    }

    @PostMapping("/transactions")
    public String saveTrx(@ModelAttribute @Valid TransactionItem form,
                          BindingResult br) {
        if (br.hasErrors()) return "transaction/form";
        txService.create(form);
        return "redirect:/transactions";
    }

}
