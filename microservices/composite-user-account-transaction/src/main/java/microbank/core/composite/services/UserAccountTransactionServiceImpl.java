package microbank.core.composite.services;

import jakarta.annotation.PostConstruct;
import microbank.core.account.Account;
import microbank.core.transaction.Transaction;
import microbank.core.user.User;
import microbank.exceptions.InvalidInputException;
import microbank.mappers.UserAccountTransaction;
import microbank.mappers.UserAccountTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserAccountTransactionServiceImpl implements UserAccountTransactionService {
    private final RestTemplate restTemplate;
    @Value("${app.user-service.host}")
    private String userServiceHost;
    @Value("${app.user-service.port}")
    private String userServicePort;
    @Value("${app.account-service.host}")
    private String accountServiceHost;
    @Value("${app.account-service.port}")
    private String accountServicePort;
    @Value("${app.transaction-service.host}")
    private String transactionServiceHost;
    @Value("${app.transaction-service.port}")
    private String transactionServicePort;

    private String userServiceUrl;
    private String accountServiceUrl;
    private String transactionServiceUrl;

    public UserAccountTransactionServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    void createUrls() {
        userServiceUrl = "http://" + userServiceHost + ":" + userServicePort + "/user";
        accountServiceUrl = "http://" + accountServiceHost + ":" + accountServicePort + "/account";
        transactionServiceUrl = "http://" + transactionServiceHost + ":" + transactionServicePort + "/transaction";
    }

    /*@Override
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
    }*/

    @Override
    public UserAccountTransaction getUser(int userId) {

//        try {
            String urlUser = userServiceUrl + "/" + userId;
            User user = restTemplate.getForObject(urlUser, User.class);
//        } catch (HttpClientErrorException e) {
//            throw new InvalidInputException();

//        try {
            String urlAccount = accountServiceUrl + "?userId=" + userId;
            Account account = restTemplate
                    .exchange(urlAccount,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<Account>() {})
                    .getBody();
//        } catch (Exception e) {
//            throw new InvalidInputException();
//        }

//        try {
            String urlTransaction = transactionServiceUrl + "?userId=" + userId;
            List<Transaction> transactions = restTemplate
                    .exchange(urlTransaction,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<Transaction>>() {})
                    .getBody();
//        } catch (Exception e) {
//            List<Transaction> transactions = new ArrayList<>();
//        }

        return createUserAggregate(user,account, transactions);

    }

    private UserAccountTransaction createUserAggregate(
            User user,
            Account account,
            List<Transaction> transactions) {

        int userId = user.getUserId();
        String userName = user.getUserName();

        int accountId = account.getAccountId();
        int balance = account.getBalance();
        String accountNumber = account.getAccountNumber();

        List<Transaction> transactionSummary = transactions
                .stream()
                .map(t -> new Transaction(
                        t.getUserId(),
                        t.getTransactionId(),
                        t.getAmount()))
                .collect(Collectors.toList());

        return new UserAccountTransaction(userId, userName, account, transactionSummary);
    }

    @Override
    public void createUser(UserAccountTransaction body) {
        try {
            User user = new User(body.getUserId(), body.getUserName());
            try {
                String url = userServiceUrl;
                Logger log = LoggerFactory.getLogger(UserAccountTransactionServiceImpl.class);
                log.info(url);
                log.info("{}, {}", user.getUserId(), user.getUserName());
                restTemplate.postForObject(url, user, User.class);
            } catch (HttpClientErrorException e) {
                throw new InvalidInputException();
            }
            if (body.getAccount() != null) {
                Account account = new Account(
                        body.getUserId(),
                        body.getAccount().getAccountId(),
                        body.getAccount().getBalance(),
                        body.getAccount().getAccountNumber());
                try {
                    String url = accountServiceUrl;
                    Logger log = LoggerFactory.getLogger(UserAccountTransactionServiceImpl.class);
                    log.info(url);
                    restTemplate.postForObject(url, account, Account.class);
                } catch (HttpClientErrorException e) {
                    throw new InvalidInputException();
                }
            }

            if (body.getTransaction() != null) {
                body.getTransaction().forEach(t -> {
                    Transaction transaction = new Transaction(
                            body.getUserId(),
                            t.getTransactionId(),
                            t.getAmount()
                    );
                    try {
                        String url = transactionServiceUrl;
                        restTemplate.postForObject(url, transaction, Transaction.class);
                    } catch (HttpClientErrorException e) {
                        throw new InvalidInputException();
                    }
                });
            }
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Override
    public void deleteUser(int userId) {
        try {
            String url = userServiceUrl + "/" + userId;
            restTemplate.delete(url);
        } catch (HttpClientErrorException e) {
            throw new InvalidInputException();
        }

        try {
            String url = accountServiceUrl + "?userId=" + userId;
            restTemplate.delete(url);
        } catch (HttpClientErrorException e) {
            throw new InvalidInputException();
        }

        try {
            String url = transactionServiceUrl + "?userId=" + userId;
            restTemplate.delete(url);
        } catch (HttpClientErrorException e) {
            throw new InvalidInputException();
        }
    }
}
