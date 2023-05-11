package co.istad.springapi.api.account;

import co.istad.springapi.api.accounttype.AccountType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class Account {
    private Integer id;
    private String accountNo;
    private String accountName;
    private String profile;
    private Integer pin;
    private String password;
    private String phoneNumber;
    private Double TransferLimit;
    private AccountType accountType;

}
