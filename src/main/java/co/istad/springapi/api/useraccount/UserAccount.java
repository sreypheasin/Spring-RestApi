package co.istad.springapi.api.useraccount;

import co.istad.springapi.api.account.Account;
import co.istad.springapi.api.user.User;

import java.time.LocalDateTime;

public class UserAccount {
    private Integer id;
    private User userId;
    private Account accountId;
    private LocalDateTime createAt;
    private Boolean isDisable;
}
