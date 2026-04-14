package chitkara.bank.service;

import chitkara.bank.entity.*;
import chitkara.bank.repository.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final AccountRepository accRepo;
    private final ServiceRepository serviceRepo;

    public UserService(UserRepository u, AccountRepository a, ServiceRepository s) {
        this.userRepo = u;
        this.accRepo = a;
        this.serviceRepo = s;
    }

    public Account getAccount() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return accRepo.findByUser_Username(username);
    }

    public String deposit(double amt) {
        Account acc = getAccount();
        acc.setBalance(acc.getBalance() + amt);
        accRepo.save(acc);
        return "Deposited";
    }

    public String withdraw(double amt) {
        Account acc = getAccount();
        if (acc.getBalance() < amt) throw new RuntimeException("Insufficient funds");

        acc.setBalance(acc.getBalance() - amt);
        accRepo.save(acc);
        return "Withdraw successful";
    }

    public double balance() {
        return getAccount().getBalance();
    }

    public String transfer(String toAcc, double amt) {
        Account sender = getAccount();
        Account receiver = accRepo.findByAccountNumber(toAcc);

        if (sender.getBalance() < amt) throw new RuntimeException("Insufficient funds");

        sender.setBalance(sender.getBalance() - amt);
        receiver.setBalance(receiver.getBalance() + amt);

        accRepo.save(sender);
        accRepo.save(receiver);

        return "Transfer done";
    }
}