package chitkara.bank.controller;

import chitkara.bank.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/txn")
public class TransactionController {

    private final UserService service;

    public TransactionController(UserService s) {
        this.service = s;
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam String acc,
                           @RequestParam double amt) {
        return service.transfer(acc, amt);
    }
}