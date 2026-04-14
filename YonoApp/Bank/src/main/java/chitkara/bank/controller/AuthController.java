package chitkara.bank.controller;

import chitkara.bank.config.JwtUtil;
import chitkara.bank.dto.*;
import chitkara.bank.entity.*;
import chitkara.bank.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepo;
    private final AccountRepository accRepo;
    private final PasswordEncoder encoder;

    public AuthController(UserRepository u, AccountRepository a, PasswordEncoder e) {
        this.userRepo = u;
        this.accRepo = a;
        this.encoder = e;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req) {

        if (req.initialDeposit < 1000)
            throw new RuntimeException("Minimum 1000 required");

        User user = new User();
        user.setUsername(req.username);
        user.setPassword(encoder.encode(req.password));
        user.setEmail(req.email);
        user.setMobile(req.mobile);

        userRepo.save(user);

        Account acc = new Account();
        acc.setUser(user);
        acc.setBalance(req.initialDeposit);
        acc.setAccountNumber(UUID.randomUUID().toString());

        accRepo.save(acc);

        return "Account created: " + acc.getAccountNumber();
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req) {

        User user = userRepo.findByUsername(req.username);

        if (user == null || !encoder.matches(req.password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return JwtUtil.generateToken(user.getUsername());
    }
}