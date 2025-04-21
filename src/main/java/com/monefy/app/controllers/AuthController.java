package com.monefy.app.controllers;

import com.monefy.app.dtos.LoginUserForm;
import com.monefy.app.dtos.RegisterRequest;
import com.monefy.app.dtos.RegisterUserForm;
import com.monefy.app.entities.EdsUser;
import com.monefy.app.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Controller
public class AuthController {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;

    @Autowired
    public AuthController(UserRepo userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @GetMapping({"/login", "/"})
    public String loginForm(Model model) {
        model.addAttribute("loginUserForm", new LoginUserForm());
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("registerUserForm", new RegisterUserForm());
        return "register";
    }

    @ModelAttribute("errors")
    public Map<String, String> errors() {
        return new HashMap<>();
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute("registerUserForm") RegisterUserForm form, Model model) {

        Map<String, String> errors = new HashMap<>();
        if (form.getUsername() == null || form.getUsername().trim().isEmpty())
            errors.put("username", "Введите имя пользователя");
        else if (userRepo.getByUsername(form.getUsername()) != null)
            errors.put("username", "Это имя уже занято");

        if (form.getPassword() == null || form.getPassword().isEmpty())
            errors.put("password", "Введите пароль");

        if (!Objects.equals(form.getPassword(), form.getConfirmPassword()))
            errors.put("confirmPassword", "Пароли не совпадают");

        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return "register";
        }

        EdsUser user = new EdsUser();
        user.setUsername(form.getUsername());
        user.setPassword(encoder.encode(form.getPassword()));
        userRepo.save(user);

        return "redirect:/login";
    }

    @PostMapping("/registerFromApi")
    public ResponseEntity<String> register(@RequestBody RegisterRequest r) {

        if (userRepo.getByUsername(r.username()) != null)
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username taken");

        EdsUser user = new EdsUser();
        user.setUsername(r.username());
        user.setPassword(encoder.encode(r.password()));
        userRepo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered");
    }
}
