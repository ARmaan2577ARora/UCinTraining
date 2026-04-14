package chitkara.bank.repository;

import chitkara.bank.entity.ServiceOpt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceOpt, Long> {
    ServiceOpt findByUser_Username(String username);
}