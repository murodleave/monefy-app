package com.monefy.app.auth;

import com.monefy.app.auth.dtos.RegisterRequest;
import com.monefy.app.entities.EdsUser;
import com.monefy.app.repos.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;

    public AuthController(UserRepo userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
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
