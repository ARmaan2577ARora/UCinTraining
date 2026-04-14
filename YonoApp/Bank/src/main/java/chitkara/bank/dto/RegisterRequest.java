package chitkara.bank.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    public String username;
    public String password;
    public String email;
    public String mobile;
    public double initialDeposit;
}