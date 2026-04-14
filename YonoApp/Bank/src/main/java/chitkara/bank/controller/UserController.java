package chitkara.bank.controller;

import chitkara.bank.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService s) {
        this.service = s;
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam double amt) {
        return service.deposit(amt);
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam double amt) {
        return service.withdraw(amt);
    }

    @GetMapping("/balance")
    public double balance() {
        return service.balance();
    }
}