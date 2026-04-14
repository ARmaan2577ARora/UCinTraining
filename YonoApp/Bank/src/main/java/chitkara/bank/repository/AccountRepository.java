package chitkara.bank.repository;

import chitkara.bank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUser_Username(String username);
    Account findByAccountNumber(String accountNumber);
}