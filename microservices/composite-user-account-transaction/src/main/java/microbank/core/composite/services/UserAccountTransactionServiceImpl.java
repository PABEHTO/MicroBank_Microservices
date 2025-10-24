package microbank.core.composite.services;

import microbank.core.account.Account;
import microbank.core.transaction.Transaction;
import microbank.mappers.UserAccountTransaction;
import microbank.mappers.UserAccountTransactionService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class UserAccountTransactionServiceImpl implements UserAccountTransactionService {
    private final RestTemplate restTemplate;

    public UserAccountTransactionServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserAccountTransaction getUserAccountTransaction(int userId) {
        Account account = restTemplate.getForObject("http://localhost:7002/account?userId="+userId, Account.class);
        List<Transaction> transactions = restTemplate.exchange(
                "http://localhost:7003/transaction?userId=" + userId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Transaction>>(){})
                .getBody();

        return new UserAccountTransaction(userId, "Testy", account, transactions);
    }
}
