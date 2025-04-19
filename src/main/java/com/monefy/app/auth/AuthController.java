package com.monefy.app.auth;

import com.monefy.app.auth.dtos.AuthRequest;
import com.monefy.app.auth.dtos.AuthResponse;
import com.monefy.app.auth.dtos.RegisterRequest;
import com.monefy.app.entities.EdsPermission;
import com.monefy.app.entities.EdsUser;
import com.monefy.app.repos.PermissionRepo;
import com.monefy.app.repos.UserRepo;
import com.monefy.app.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserRepo userRepo;
    private final PermissionRepo permissionRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil, UserRepo userRepo, PermissionRepo permissionRepo, PasswordEncoder passwordEncoder) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userRepo = userRepo;
        this.permissionRepo = permissionRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequest request) {

        if (userRepo.existsByUsername(request.username()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken");

        EdsPermission readPerm = permissionRepo.findByCode("TRANSACTION_READ")
                .orElseThrow(() -> new RuntimeException("Permission not found"));
//                      .orElseGet(() -> permissionRepo.save(
//                new EdsPermission("TRANSACTION_READ",
//                        "Read transactions", "Can view transactions")));


        EdsUser user = new EdsUser();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setPermissions(Set.of(readPerm));

        userRepo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request) {

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = jwtUtil.generateToken(request.username());

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
