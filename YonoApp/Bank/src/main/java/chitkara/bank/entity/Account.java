package chitkara.bank.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Account {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String accountNumber;

    private double balance;

    @OneToOne
    private User user;
}