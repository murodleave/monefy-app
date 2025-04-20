package com.monefy.app.controllers;

import com.monefy.app.auth.dtos.LoginUserForm;
import com.monefy.app.auth.dtos.RegisterRequest;
import com.monefy.app.entities.EdsUser;
import com.monefy.app.repos.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;

    public AuthController(UserRepo userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @GetMapping("/login")
    public Model loginForm(Model model) {
        model.addAttribute("loginUser", new LoginUserForm());
        return model;
    }

    @PostMapping("/authenticate")
    public String loginSubmit(@ModelAttribute("loginUser") LoginUserForm loginUser, Model model) {

        Map<String, String> errors = new HashMap<>();
        if (loginUser.getUsername() == null || loginUser.getUsername().trim().isEmpty()) {
            errors.put("username", "Пожалуйста, введите имя пользователя");
        }
        if (loginUser.getPassword() == null || loginUser.getPassword().trim().isEmpty()) {
            errors.put("password", "Пожалуйста, введите пароль");
        }

        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return "login";
        }

        // TODO: здесь ваша логика аутентификации, например:
        // if (!authService.login(loginUser)) {
        //     errors.put("global", "Неверное имя пользователя или пароль");
        //     model.addAttribute("errors", errors);
        //     return "login";
        // }

        // Успешный вход
        return "redirect:/dashboard";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest r) {

        if (userRepo.existsByUsername(r.username()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username taken");

        EdsUser user = new EdsUser();
        user.setUsername(r.username());
        user.setPassword(encoder.encode(r.password()));
        userRepo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered");
    }
}
