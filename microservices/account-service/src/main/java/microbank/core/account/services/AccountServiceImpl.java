package microbank.core.account.services;

import microbank.core.account.Account;
import microbank.core.account.AccountService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountServiceImpl implements AccountService {
    @Override
    public Account getAccount(int userId) {
        return new Account(userId, 1, 10000, "Mock");
    }
}
