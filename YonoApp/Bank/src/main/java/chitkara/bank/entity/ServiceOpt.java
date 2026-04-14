package chitkara.bank.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ServiceOpt {

    @Id @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    private boolean debitCard;
    private boolean chequeBook;
    private boolean passbook;
}