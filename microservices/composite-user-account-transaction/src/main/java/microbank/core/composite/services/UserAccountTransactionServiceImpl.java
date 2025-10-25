package microbank.core.composite.services;

import microbank.core.account.Account;
import microbank.core.transaction.Transaction;
import microbank.mappers.UserAccountTransaction;
import microbank.mappers.UserAccountTransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class UserAccountTransactionServiceImpl implements UserAccountTransactionService {
    private final RestTemplate restTemplate;
    /*@Value("${app.user-service.host}")
    private String userServiceHost;
    @Value("${app.user-service.port}")
    private String userServicePort;*/
    @Value("${app.account-service.host}")
    private String accountServiceHost;
    @Value("${app.account-service.port}")
    private String accountServicePort;
    @Value("${app.transaction-service.host}")
    private String transactionServiceHost;
    @Value("${app.transaction-service.port}")
    private String transactionServicePort;

    public UserAccountTransactionServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserAccountTransaction getUserAccountTransaction(int userId) {
        Account account = restTemplate.getForObject("http://" + accountServiceHost + ":" + accountServicePort + "/account?userId=" + userId, Account.class);
        List<Transaction> transactions = restTemplate.exchange(
                        "http://" + transactionServiceHost + ":" + transactionServicePort + "/transaction?userId=" + userId,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Transaction>>() {
                        })
                .getBody();

        return new UserAccountTransaction(userId, "Testy", account, transactions);
    }
}
