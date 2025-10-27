package microbank.core.account.services;

import com.mongodb.DuplicateKeyException;
import microbank.core.account.Account;
import microbank.core.account.AccountService;
import microbank.core.account.persistence.AccountEntity;
import microbank.core.account.persistence.AccountRepository;
import microbank.exceptions.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountServiceImpl(
            AccountRepository accountRepository,
            AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public Account getAccount(int userId) {
        if (userId < 1) {
            throw new InvalidInputException("Invalid userId" + userId);
        }
        AccountEntity entity = accountRepository.findByUserId(userId);
        Account account = accountMapper.entityToApi(entity);

        return account;
    }

    @Override
    public Account createAccount(Account account) {
        try {
            AccountEntity entity = accountMapper.apiToEntity(account);
            AccountEntity newEntity = accountRepository.save(entity);
            return accountMapper.entityToApi(newEntity);
        } catch (DuplicateKeyException e) {
            throw new InvalidInputException(
                    "Duplicate key, "
                    + account.getUserId()
                    + " "
                    + account.getAccountId()
            );
        }
    }

    @Override
    public void deleteAccount(int userId) {
        accountRepository.delete(accountRepository.findByUserId(userId));
    }
}
